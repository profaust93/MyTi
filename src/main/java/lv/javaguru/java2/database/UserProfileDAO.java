package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.UserProfile;
import lv.javaguru.java2.domain.UserProfileList;

import java.util.List;

/**
 * Created by Camille on 02.04.2016.
 *
 */
public interface UserProfileDAO {

    void create (UserProfile userProfile) throws DBException;

    UserProfile getById (Long profileId) throws DBException;

    void delete (Long profileId) throws DBException;

    void update (UserProfile userProfile) throws DBException;

    List<UserProfileList> getAllUserProfile() throws DBException;

}
