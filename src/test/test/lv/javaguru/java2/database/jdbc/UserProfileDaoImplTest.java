package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.UserProfileDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserProfile;
import static org.junit.Assert.*;

import org.junit.*;

/**
 * Created by Camille on 03.04.2016.
 */
public class UserProfileDaoImplTest {

    private DatabaseCleaner dbCleaner = new DatabaseCleaner();
    private UserProfileDAO userProfileDAO = new UserProfileDAOImpl();
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
       dbCleaner.cleanDatabase();
        UserDAO userDAO = new UserDAOImpl();
        System.out.println("before create "+user.getUserId());
        userDAO.create(user);
        System.out.println("after create "+user.getUserId()+ "create user executed");
        System.out.println("@Before executed");


    }

    @After
    public void tearDown() throws Exception {
        dbCleaner.cleanDatabase();
        System.out.println("@After executed");

    }

    @Test
    public void testCreate ()throws Exception {
        UserProfile userProfile = new UserProfile();
        System.out.println(user.getUserId());
        userProfile.setUserId(user.getUserId());
        userProfile.setFirstName("nameName");
        userProfile.setLastName("lastNameLastName");
        userProfile.setEmail("emailEmail");
        userProfileDAO.create(userProfile);
        UserProfile userProfileFromDB = userProfileDAO.getById(user.getUserId());
        assertEquals(userProfile.getFirstName(),userProfileFromDB.getFirstName());
        assertEquals(userProfile.getLastName(),userProfileFromDB.getLastName());
        assertEquals(userProfile.getEmail(),userProfileFromDB.getEmail());
        assertEquals(userProfile.getProfileId(),userProfileFromDB.getProfileId());
        assertEquals(userProfile.getUserId(),userProfileFromDB.getUserId());
    }

    @Test
    public void testUpdate() throws Exception{


        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(user.getUserId());
        userProfile.setFirstName("nameName");
        userProfile.setLastName("lastNameLastName");
        userProfile.setEmail("emailEmail");
        userProfile.setUserId(user.getUserId());
        userProfileDAO.create(userProfile);


        userProfile.setFirstName("nameName22");
        userProfile.setLastName("lastNameLastName2");
        userProfile.setEmail("emailEmail2");
        userProfileDAO.update(userProfile);

        UserProfile userProfileFromDB = userProfileDAO.getById(user.getUserId());
        assertEquals(userProfile.getFirstName(),userProfileFromDB.getFirstName());
        assertEquals(userProfile.getLastName(),userProfileFromDB.getLastName());
        assertEquals(userProfile.getEmail(),userProfileFromDB.getEmail());
        assertEquals(userProfile.getProfileId(),userProfileFromDB.getProfileId());
        assertEquals(userProfile.getUserId(),userProfileFromDB.getUserId());
    }

    @Test
    public void testDelete() throws Exception{
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(user.getUserId());
        userProfile.setFirstName("nameName");
        userProfile.setLastName("lastNameLastName");
        userProfile.setEmail("emailEmail");
        userProfile.setUserId(user.getUserId());
        userProfileDAO.create(userProfile);
        UserProfile userProfile1FromDB = userProfileDAO.getById(user.getUserId());
        assertNotNull(userProfile1FromDB);

        userProfileDAO.delete(user.getUserId());
        userProfileDAO.getById(user.getUserId());
        userProfile1FromDB = userProfileDAO.getById(user.getUserId());
        assertNull(userProfile1FromDB);
    }

}
