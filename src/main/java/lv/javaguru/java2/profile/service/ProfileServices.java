package lv.javaguru.java2.profile.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.profile.domain.UserProfile;

import lv.javaguru.java2.profile.database.UserProfileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Camille on 07.04.2016.
 */
@Component
public class ProfileServices {
    @Autowired
    UserProfileDAO userProfileDAO;
    public Boolean profileExist (Long userid) throws DBException {
        Boolean exists = false;
        UserProfile userProfile = userProfileDAO.getById(userid);
        if (userProfile != null){
            exists=true;
            System.out.println("exists = true");
        }
        return exists;
    }
}
