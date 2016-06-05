package lv.javaguru.java2.controller;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserProfile;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

   @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() throws RedirectException {
        return  "register";
    }
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public ModelAndView processPost(HttpServletRequest req) {

        ModelAndView modelAndView = new ModelAndView("register");
        HttpSession session = req.getSession();
        User user = new User(req.getParameter("login"),
                req.getParameter("password"));
        UserProfile userProfile = new UserProfile(req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("email"));

        if(checkFields(user, userProfile)) return modelAndView.addObject("model", "All fields must be filled");

        if (checkIfUserExists(user)) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            try {
                userDAO.create(user);
            } catch (DBException e) {
                e.printStackTrace();
                return modelAndView.addObject("model","Something gone wrong with DB.");
            }
            session.setAttribute("login", req.getParameter("login"));
            session.setAttribute("firstName", req.getParameter("firstName"));
            session.setAttribute("lastName", req.getParameter("lastName"));

        } else {
            return modelAndView.addObject("model", "User already exists");
        }

        modelAndView.addObject("model", null);
        return modelAndView;
    }


    private boolean checkFields(User user, UserProfile userProfile) {
        return (user.getUsername().isEmpty()
                || user.getPassword().isEmpty()
                || userProfile.getLastName().isEmpty()
                || userProfile.getFirstName().isEmpty()
                || userProfile.getEmail().isEmpty());
    }
    private boolean checkIfUserExists(User user) {

        User testUser = null;
        testUser = userDAO.findByUserName(user.getUsername());

        if (testUser == null) return true;
        else return false;
    }
}