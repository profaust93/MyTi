package lv.javaguru.java2.service.userProfile;

import lv.javaguru.java2.domain.UserProfileList;
import lv.javaguru.java2.model.exceptions.UserProfileException;

import java.util.List;

/**
 * Created by Ruslan on 2016.04.23..
 */
public interface UserProfileListService {

    List<UserProfileList> getAllUserProfile() throws UserProfileException;
}
