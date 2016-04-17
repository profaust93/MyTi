package lv.javaguru.java2.model.profile;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserProfileDAO;
import lv.javaguru.java2.domain.UserProfile;
import lv.javaguru.java2.model.exceptions.UserProfileException;

import java.util.Map;

/**
 * Created by Camille on 07.04.2016.
 */
public class UserProfileModelImpl implements UserProfileModel {

    UserProfileDAO userProfileDAO;
    UserProfile userProfile;
    @Override
    public void setUserProfileDAO(UserProfileDAO userProfileDAO) {
    this.userProfileDAO = userProfileDAO;
    }

    @Override
    public UserProfile getUserProfile(Long userId) throws UserProfileException{
    try {
        UserProfile userProfile = userProfileDAO.getById(userId);
        return userProfile;
    }catch (DBException e){
        throw new UserProfileException(e.getMessage());
    }

    }

    @Override
    public void createUserProfile(Map profileData) {
        userProfile = new UserProfile();
        userProfile.setUserId((Long)profileData.get("userId"));
        userProfile.setFirstName((String)profileData.get("firstName"));
        userProfile.setFirstName((String)profileData.get("lastName"));
        userProfile.setFirstName((String)profileData.get("email"));
        try {
            userProfileDAO.create(userProfile);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateUserProfile(Map profileData) {
        userProfile = new UserProfile();
        userProfile.setUserId((Long)profileData.get("userId"));
        userProfile.setFirstName((String)profileData.get("firstName"));
        userProfile.setFirstName((String)profileData.get("lastName"));
        userProfile.setFirstName((String)profileData.get("email"));
        try {
            userProfileDAO.update(userProfile);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFoto() {

    }
}
