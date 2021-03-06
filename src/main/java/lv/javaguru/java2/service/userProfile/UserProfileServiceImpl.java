package lv.javaguru.java2.service.userProfile;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserProfileDAO;
import lv.javaguru.java2.domain.UserProfile;
import lv.javaguru.java2.domain.UserProfileList;
import lv.javaguru.java2.model.exceptions.UserProfileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Camille on 07.04.2016.
 */
@Component
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
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
        userProfile.setLastName((String)profileData.get("lastName"));
        userProfile.setEmail((String)profileData.get("email"));
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
        userProfile.setLastName((String)profileData.get("lastName"));
        userProfile.setEmail((String)profileData.get("email"));
        try {
            userProfileDAO.update(userProfile);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFoto() {

    }

    @Override
    public List<UserProfileList> getAllUserProfile() throws UserProfileException {
        try {
            List<UserProfile> allUserProfile = userProfileDAO.getAllUserProfile();
            return allUserProfile.stream().map(userProfiles ->
                    new UserProfileList(userProfiles.getUserId(),
                            userProfiles.getFirstName(),userProfiles.getLastName(),
                            userProfiles.getEmail())).collect(Collectors.toList());
        } catch (DBException e) {
            throw new UserProfileException(e.getMessage());
        }
    }
}
