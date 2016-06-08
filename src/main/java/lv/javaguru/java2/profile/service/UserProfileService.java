package lv.javaguru.java2.profile.service;


import lv.javaguru.java2.profile.domain.UserProfile;
import lv.javaguru.java2.profile.domain.UserProfileList;
import lv.javaguru.java2.profile.exception.UserProfileException;
import lv.javaguru.java2.profile.database.UserProfileDAO;

import java.util.List;
import java.util.Map;

/**
 * Created by Camille on 07.04.2016.
 */
public interface UserProfileService {

    void setUserProfileDAO (UserProfileDAO userProfileDAO);

    UserProfile getUserProfile(Long userId) throws UserProfileException;

    void createUserProfile(Map profileDatas);
    void updateUserProfile (Map profileData);

    void addFoto();

    List<UserProfileList> getAllUserProfile() throws UserProfileException;


}
