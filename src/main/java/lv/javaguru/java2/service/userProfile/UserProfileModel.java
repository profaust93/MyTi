package lv.javaguru.java2.service.userProfile;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserProfileDAO;
import lv.javaguru.java2.domain.UserProfile;
import lv.javaguru.java2.model.exceptions.UserProfileException;

import java.util.Map;

/**
 * Created by Camille on 07.04.2016.
 */
public interface UserProfileModel {

    void setUserProfileDAO (UserProfileDAO userProfileDAO);

    UserProfile getUserProfile(Long userId) throws UserProfileException;

    void createUserProfile(Map profileDatas);
    void updateUserProfile (Map profileData);

    void addFoto();



}
