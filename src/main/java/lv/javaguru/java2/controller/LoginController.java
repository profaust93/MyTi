package lv.javaguru.java2.controller;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.LoginException;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.service.user.UserModel;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserModel userModel;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(HttpServletRequest req) throws RedirectException {
        HttpSession session = req.getSession();
        Boolean isLogIn = (Boolean) session.getAttribute("IsLoggedIn");
        if(isLogIn != null && isLogIn) {
            return  new ModelAndView("index","blaa",null);
        }
        String contextURI = req.getRequestURL().toString();
        session.setAttribute("comeFrom",contextURI);
        return  new ModelAndView("login","blaa",null);
    }


    public MVCModel processPost(HttpServletRequest req) {
        Map<String,String> resultMap = new HashMap<>();
        try{
            User user = userModel.logInUser(req.getParameter("userCred"),req.getParameter("password"),false);
            if(user == null || user.getUserId() == null) {
                throw new LoginException("Login Error");
            }

            HttpSession session = req.getSession();
            session.setAttribute("IsLoggedIn",true);
            UserDTO userDTO = new UserDTO(user.getFirstName(),user.getLogin(),user.getUserId());
            session.setAttribute("user",userDTO);

            resultMap.put("status","OK");
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
