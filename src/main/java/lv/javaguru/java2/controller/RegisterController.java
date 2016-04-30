package lv.javaguru.java2.controller;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.RegisterException;
import lv.javaguru.java2.service.user.UserModelImpl;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kemran on 02/04/2016.
 */
@Component
public class RegisterController implements MVCController {

    @Qualifier("ORM_UserDAO")
    @Autowired
    UserDAO userDAO;

    @Autowired
    UserModelImpl userModel;

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        HttpSession session = req.getSession();
        Boolean isLogIn = (Boolean) session.getAttribute("IsLoggedIn");
        if(isLogIn != null && isLogIn){
            return  new MVCModel("/redirect.jsp","index");
        }
        String contextURI = req.getRequestURL().toString();
        session.setAttribute("comeFrom",contextURI);
        return new MVCModel("/register.jsp", null);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        Map<String,String> resultMap = new HashMap<>();

        User user = new User(
                req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("firstname"),
                req.getParameter("lastname"),
                req.getParameter("email")
        );

        try {
            userModel.registerUser(user);
            userDAO.create(user);
            HttpSession session = req.getSession();
            UserDTO userDTO = new UserDTO(user.getFirstName(),user.getLogin(),user.getUserId());
            session.setAttribute("user", userDTO);
            session.setAttribute("IsLoggedIn",true);
            resultMap.put("status","OK");
            if(!req.getServletPath().equals("/register")) {
                resultMap.put("redirectTo",(String)session.getAttribute("comeFrom"));
            } else {
                String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/java2/index";
                resultMap.put("redirectTo",url);
            }
            return new MVCModel("/json.jsp",new JSONObject(resultMap));

        } catch (DBException e) {
            resultMap.put("status","NOK");
            resultMap.put("ERROR",e.getMessage());
            return new MVCModel("/json.jsp", new JSONObject(resultMap));

        } catch ( RegisterException e){
            resultMap.put("status","NOK");
            resultMap.put("ERROR",e.getMessage());
            return new MVCModel("/json.jsp", new JSONObject(resultMap));
        }

    }
}
