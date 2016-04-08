package lv.javaguru.java2.controller;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.model.MVCModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Kemran on 02/04/2016.
 */
public class RegisterController implements MVCController {


    @Override
    public MVCModel processGet(HttpServletRequest req) {
        return new MVCModel("/register.jsp", "Registration");
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        User user = new User(
                req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("firstname"),
                req.getParameter("lastname"),
                req.getParameter("email")
        );
        HttpSession ses = req.getSession();
        UserDAO userDAO = new UserDAOImpl();
        User checkUser = null;

        try {
            checkUser = userDAO.getUserByEmailOrLogin(user.getLogin());

        } catch (DBException e) {
            return new MVCModel("/register", e.getMessage());
        }

            if (checkUser == null) {
                try {
                    userDAO.create(user);
                    req.getSession().setAttribute("userId", user.getUserId());
                    req.getSession().setAttribute("isLoggedIn",true);
                } catch (DBException e) {
                    return new MVCModel("/register.jsp", "Error with DB.");
                }
            } else {
                return new MVCModel("/register.jsp", "This login is already taken.");
            }

        return new MVCModel("/register.jsp", null);
    }
}
