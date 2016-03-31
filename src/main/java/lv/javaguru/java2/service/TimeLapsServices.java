package lv.javaguru.java2.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruslan on 16.30.3.
 */
public class TimeLapsServices {

    String ok = "OK";

    public String isNotEmpty(String data){
        if(StringUtils.isEmpty(data)) try {
            throw new DBException("This field must be not empty");
        } catch (DBException e) {
            return e.getMessage();
        }
        return ok;
    }

    public String isDefinedUserId(String data){
        UserDAO userDAO = new UserDAOImpl();
        try {
            List<User> list = userDAO.getAll();
            if(list.contains(userDAO.getById(Long.parseLong(data)))){
                return ok;
            } else throw new DBException("Not defined Id");
        } catch (DBException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String isNumber(String data) {
        if (StringUtils.isNumeric(data) == false) {
            try {
                throw new DBException("Must be number");
            } catch (DBException e) {
                return e.getMessage();
            }
        }
        return ok;
    }

    public String userIdCheck(String data){
        List<String> checkResultList = new ArrayList<>();
        try {
            checkResultList.add(isNotEmpty(data));
            checkResultList.add(isNumber(data));
            checkResultList.add(isDefinedUserId(data));
            for (int i = 0; i < checkResultList.size(); i++) {
                if(checkResultList.get(i).equalsIgnoreCase(ok)== false){
                    throw new DBException(checkResultList.get(i));
                }
            }
        } catch (DBException e){
            return e.getMessage();
        }
        return ok;
    }
}
