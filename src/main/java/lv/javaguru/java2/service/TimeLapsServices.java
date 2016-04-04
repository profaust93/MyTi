package lv.javaguru.java2.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruslan on 16.30.3.
 */
public class TimeLapsServices {

    String ok = "OK";
    Integer count;

    public String isNotEmpty(String data){
        if(StringUtils.isEmpty(data)) try {
            throw new DBException("This field must be not empty");
        } catch (DBException e) {
            return e.getMessage();
        }
        return ok;
    }

    public String isDefinedUserId(String data) {
        UserDAO userDAO = new UserDAOImpl();
        count = 0;
        try {
            List<User> userList = userDAO.getAll();
            if(userList.size() == 0){
                throw new DBException("Empty DB");
            } else for (int i = 0; i < userList.size() ; i++) {
                if(userList.get(i).getUserId().equals(Long.parseLong(data))){
                    count++;
                }
            }
            if (count == 0) throw new DBException("No user in DB");
        } catch (DBException e) {
            return e.getMessage();
        }
        return ok;
    }

    public String isNumber(String data) {
        if (StringUtils.isNumeric(data) == false) {
            try {
                throw new DBException("This field must be number");
            } catch (DBException e) {
                return e.getMessage();
            }
        }
        return ok;
    }

    public LocalDateTime dateConvert(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(data,formatter);
        System.out.println(dateTime);
        return dateTime;
    }

    public String userIdCheck(String data){
        try {
            if(isNotEmpty(data) != ok) throw new DBException(isNotEmpty(data));
            if(isNumber(data) != ok) throw new DBException(isNumber(data));
            if(isNotEmpty(data)==ok){
                if(isDefinedUserId(data) != ok) throw new DBException(isDefinedUserId(data));
            }
        } catch (DBException e){
            return e.getMessage();
        }
        return ok;
    }

    public String dateCheck(String data){
        try {
            if(isNotEmpty(data) != ok) throw new DBException(isNotEmpty(data));

        } catch (DBException e) {
            return e.getMessage();
        }
        return ok;
    }

    public String categoryCheck(String data){
        try{
            if(isNotEmpty(data) != ok)
                throw new DBException(isNotEmpty(data));
            if(isNumber(data) == ok)
                throw new DBException("This field must be string");
        }catch (DBException e){
            return e.getMessage();
        }
        return ok;
    }
}
