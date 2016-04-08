package lv.javaguru.java2.service.user;


import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.model.exceptions.LoginException;
import lv.javaguru.java2.model.exceptions.RegisterException;


public interface UserModel {

    /**
     * <p>Login User into system</p>
     * @param userCred user login or email
     * @param password user password
     * @param rememberMe should we remember user in system
     * @return User
     * @throws LoginException if wrong password, or login, or other exception in database, system etc.
     */

    User logInUser(String userCred, String password, Boolean rememberMe) throws LoginException;

    Boolean registerUser(User user) throws RegisterException;

}
