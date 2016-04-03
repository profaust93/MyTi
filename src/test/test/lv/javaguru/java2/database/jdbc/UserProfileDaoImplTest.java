package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.UserProfileDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserProfile;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Camille on 03.04.2016.
 */
public class UserProfileDaoImplTest {

    private DatabaseCleaner dbCleaner = new DatabaseCleaner();
    private UserProfileDAO userProfileDAO = new UserProfileDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();
    private User user = createUser("login","password", "Name", "SecName", "explorer@int.com");
    public User createUser(String login, String password, String firstName, String lastName, String email) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }


    @Before

    public void setUp () throws DBException {
       // dbCleaner.cleanDatabase();
        UserDAO userDAO = new UserDAOImpl();
        userDAO.create(user);

    }

    @After
    public void tearDown() throws Exception {
        dbCleaner.cleanDatabase();

    }

    @Test
    public void testCreate ()throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId((long) 1);
        userProfile.setFirstName("nameName");
        userProfile.setLastName("lastNameLastName");
        userProfile.setEmail("emailEmail");
        userProfileDAO.create(userProfile);
        UserProfile userProfileFromDB = userProfileDAO.getById((long) 1);
        assertEquals(userProfile.getFirstName(),userProfileFromDB.getFirstName());
        assertEquals(userProfile.getLastName(),userProfileFromDB.getLastName());
        assertEquals(userProfile.getEmail(),userProfileFromDB.getEmail());
        assertEquals(userProfile.getProfileId(),userProfileFromDB.getProfileId());
        assertEquals(userProfile.getUserId(),userProfileFromDB.getUserId());
    }


}
