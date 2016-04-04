package lv.javaguru.java2.model.user;


import com.sun.deploy.net.HttpRequest;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.model.exceptions.LoginException;

import java.util.Map;


public interface UserModel {

    /**
     * <p>Login User into system</p>
     * @param userCred user login or email
     * @param password user password
     * @param rememberMe should we remember user in system
     * @return user info Map with userId, userName, and additional info
     * @throws LoginException if wrong password, or login, or other exception in database, system etc.
     */

    Map<String,String> logInUser(String userCred, String password, Boolean rememberMe) throws LoginException;

    Boolean registerUser(HttpRequest request);

    void setUserDAO(UserDAO userDAO);
}
