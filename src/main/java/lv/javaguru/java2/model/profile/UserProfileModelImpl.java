package lv.javaguru.java2.model.profile;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserProfileDAO;
import lv.javaguru.java2.domain.UserProfile;
import lv.javaguru.java2.model.exceptions.UserProfileException;

/**
 * Created by Camille on 07.04.2016.
 */
public class UserProfileModelImpl implements UserProfileModel {

    UserProfileDAO userProfileDAO;

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
    public void updateUserProfile() {

    }

    @Override
    public void addFoto() {

    }
}
