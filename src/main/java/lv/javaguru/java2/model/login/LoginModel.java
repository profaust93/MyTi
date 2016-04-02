package lv.javaguru.java2.model.login;


import lv.javaguru.java2.model.exceptions.LoginException;

import java.util.Map;

public interface LoginModel {
    Map<String,String> logInUser(String userCred, String password, Boolean rememberMe) throws LoginException;
}
