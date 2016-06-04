package lv.javaguru.java2.controller;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Kemran on 02/04/2016.
 */
@Controller
public class RegisterController {

    @Qualifier("ORM_UserDAO")
    @Autowired
    UserDAO userDAO;

   /* @RequestMapping(value = "register", method = {RequestMethod.GET})
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        HttpSession session = req.getSession();
        Boolean isLogIn = (Boolean) session.getAttribute("IsLoggedIn");
        if(isLogIn != null && isLogIn){
            return  new MVCModel("/redirect.jsp","index");
        }
        String contextURI = req.getRequestURL().toString();
        session.setAttribute("comeFrom",contextURI);
        return new MVCModel("/register.jsp", null);
    }*/
   @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() throws RedirectException {
        return  "register";
    }
    @RequestMapping(value = "register", method = {RequestMethod.POST})
    public ModelAndView processPost(HttpServletRequest req) {

        //мвц мвц
        //тут всю каниьель в сервисс надо бы вынести
        ModelAndView modelAndView = new ModelAndView("register");
        User user = createUser(req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("email"));
        if(checkFields(user)) return modelAndView.addObject("model", "Fill All Fields");

        if (checkIfUserExists(user)) {
            try {
                userDAO.create(user);
            } catch (DBException e) {
                e.printStackTrace();
                return modelAndView.addObject("model","Something gone wrong with DB.");
            }

        } else {
            return modelAndView.addObject("model", "User already exists");
        }

        modelAndView.addObject("model", null);
        return modelAndView;
    }

    private User createUser(String login,String password, String firstName, String lastName,
                            String email) {

        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        return user;
    }

    private boolean checkFields(User user) {
        return (user.getUsername().isEmpty()
                || user.getPassword().isEmpty()
                || user.getLastName().isEmpty()
                || user.getFirstName().isEmpty()
                || user.getEmail().isEmpty());
    }
    private boolean checkIfUserExists(User user) {

        User testUser = null;
        testUser = userDAO.findByUserName(user.getUsername());

        if (testUser == null) return true; else return false;
    }

        /*Map<String,String> resultMap = new HashMap<>();

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

    }*/
}