package lv.javaguru.java2.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;

/**
 * Created by ruslan on 16.30.3.
 */
public class TimeLapsServices {

    String ok = "OK";
    public String isNotNull(String data){
        if(data == null || data == "") try {
            throw new DBException("This field must be not null");
        } catch (DBException e) {
            return e.getMessage();
        }
        return ok;
    }

    public String isDefinedUserId(String data){
        UserDAO userDAO = new UserDAOImpl();
        try {
            userDAO.getById(Long.parseLong(data));
        } catch (DBException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return ok;
    }

}
