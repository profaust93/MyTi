package lv.javaguru.java2.controller;

import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.LoginException;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.user.UserModel;
import lv.javaguru.java2.model.user.UserModelImpl;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LoginController implements MVCController {
    private UserModel userModel;
    public LoginController(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        HttpSession session = req.getSession();
        Boolean isLogIn = (Boolean) session.getAttribute("IsLoggedIn");
        if(isLogIn != null && isLogIn) {
            throw new RedirectException("Don't need to login again","/java2/index");
        }
        String contextURI = req.getRequestURL().toString();
        session.setAttribute("comeFrom",contextURI);
        return new MVCModel("/login.jsp",null);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        Map<String,String> resultMap = new HashMap<>();
        userModel.setUserDAO(new UserDAOImpl());
        try{
            Map<String,String> userInfo = userModel.logInUser(req.getParameter("userCred"),req.getParameter("password"),false);
            if(userInfo == null || userInfo.get("userId") == null) {
                throw new LoginException("Login Error");
            }

            HttpSession session = req.getSession();
            session.setAttribute("IsLoggedIn",true);
            session.setAttribute("userId",userInfo.get("userId"));

            resultMap.put("status","OK");
            resultMap.put("userName",userInfo.get("userName"));
            if(!req.getServletPath().equals("/login")) {
                resultMap.put("redirectTo",(String)session.getAttribute("comeFrom"));

            } else {
                String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/index";
                resultMap.put("redirectTo",url);
            }
            return new MVCModel("/json.jsp",new JSONObject(resultMap));
        } catch (LoginException e) {
            resultMap.put("status","NOK");
            resultMap.put("ERROR",e.getMessage());
            return new MVCModel("/json.jsp",new JSONObject(resultMap));
        }
    }
}
