package lv.javaguru.java2.service.userProfile;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserProfileDAO;
import lv.javaguru.java2.database.jdbc.UserProfileDAOImpl;
import lv.javaguru.java2.domain.UserProfile;

import org.springframework.stereotype.Component;

/**
 * Created by Camille on 07.04.2016.
 */
//@Component
public class ProfileServices {
    //UserProfileDAO userDAO;
    public Boolean profileExist (Long userid) throws DBException {
        UserProfileDAO userDAO = new UserProfileDAOImpl();
        Boolean exists = false;
        UserProfile userProfile = userDAO.getById(userid);
        if (userProfile != null){
            exists=true;
            System.out.println("exists = true");
        }
        return exists;
    }
}
