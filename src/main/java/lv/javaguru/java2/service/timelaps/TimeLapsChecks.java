package lv.javaguru.java2.service.timelaps;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruslan on 16.30.3.
 */
@Component
public class TimeLapsChecks {

    String ok = "OK";
    Integer count;

    public String isNotEmpty(String data) {
        if (StringUtils.isEmpty(data)) try {
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
            if (userList.size() == 0) {
                throw new DBException("Empty DB");
            } else for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserId().equals(Long.parseLong(data))) {
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

    public LocalDateTime dateConvert(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        if(!isNotEmpty(data).equalsIgnoreCase(ok)) try {
            throw new DBException("Empty field, LDT - now");
        } catch (DBException e) {
            return LocalDateTime.parse(LocalDateTime.now().toString(),formatter);
        }
        LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
        return dateTime;
    }

    public String dateConvert(LocalDateTime time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String convertedDate = time.format(formatter);
        return convertedDate;
    }

    public String userIdCheck(String data) {
        try {
            if (isNotEmpty(data) != ok) throw new DBException(isNotEmpty(data));
            if (isNumber(data) != ok) throw new DBException(isNumber(data));
            if (isNotEmpty(data) == ok) {
                if (isDefinedUserId(data) != ok) throw new DBException(isDefinedUserId(data));
            }
        } catch (DBException e) {
            return e.getMessage();
        }
        return ok;
    }

    public String dateCheck(String data) {
        try {
            if (isNotEmpty(data) != ok) throw new DBException(isNotEmpty(data));
        } catch (DBException e) {
            return e.getMessage();
        }
        return ok;
    }

    public String categoryCheck(String data) {
        try {
            if (isNotEmpty(data) != ok)
                throw new DBException(isNotEmpty(data));
            if (isNumber(data) == ok)
                throw new DBException("This field must be string");
        } catch (DBException e) {
            return e.getMessage();
        }
        return ok;
    }

    public String nameCheck(String data) {
        try {
            if (isNotEmpty(data) != ok)
                throw new DBException(isNotEmpty(data));
        } catch (DBException e) {
            return e.getMessage();
        }
        return ok;
    }

    public String descriptionCheck(String data, Integer count) {
        try {

            if (StringUtils.length(data) > count) {
                throw new DBException("Too long description, must be shorter(not more than " +
                        count + " symbols)");
            }
        } catch (DBException e) {
            return e.getMessage();
        }
        return ok;
    }
}
