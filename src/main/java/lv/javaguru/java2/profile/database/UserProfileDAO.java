package lv.javaguru.java2.profile.database;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.profile.domain.UserProfile;

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

    List<UserProfile> getAllUserProfile() throws DBException;

}
