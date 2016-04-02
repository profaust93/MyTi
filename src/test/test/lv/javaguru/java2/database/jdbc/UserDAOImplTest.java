package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;


public class UserDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private UserDAOImpl userDAO = new UserDAOImpl();


    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {
        User user = createUser("unnamed","qwe123", "Name", "SecName", "explorer@int.com");

        userDAO.create(user);

        User userFromDB = userDAO.getById(user.getUserId());
        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getLogin(), userFromDB.getLogin());
        assertEquals(user.getPassword(), userFromDB.getPassword());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
        assertEquals(user.getEmail(), userFromDB.getEmail());
    }

    @Test
    public void testMultipleUserCreation() throws DBException {
        User user1 = createUser("unnamed","qwe123", "Name", "SecName", "explorer@int.com");
        User user2 = createUser("unnamed1","123ewq", "Usee", "Seccc", "expld@invbf.com");
        userDAO.create(user1);
        userDAO.create(user2);
        List<User> users = userDAO.getAll();
        assertEquals(2, users.size());
    }

    @Test
    public void testDelete() throws DBException{
        User user = createUser("unnamed","qwe123", "Name", "SecName", "explorer@int.com");
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());

        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getLogin(), userFromDB.getLogin());
        assertEquals(user.getPassword(), userFromDB.getPassword());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
        assertEquals(user.getEmail(), userFromDB.getEmail());

        userDAO.delete(user.getUserId());
        userFromDB = userDAO.getById(user.getUserId());
        assertNull(userFromDB);
    }

    @Test
    public void testUpdate() throws DBException{
        User user = createUser("Log","Pass", "FirstN", "LastN", "Email");
        userDAO.create(user);
        User userFromDBBefore = userDAO.getById(user.getUserId());

        userFromDBBefore.setPassword("updated");
        userFromDBBefore.setFirstName("updated");
        userFromDBBefore.setLastName("updated");
        userFromDBBefore.setEmail("updated");
        userDAO.update(userFromDBBefore);

        User userFromDBAfter = userDAO.getById(userFromDBBefore.getUserId());

        assertNotNull(userFromDBBefore);
        assertNotNull(userFromDBAfter);
        assertEquals(userFromDBBefore.getUserId(), userFromDBAfter.getUserId());
        assertEquals(userFromDBBefore.getLogin(), userFromDBAfter.getLogin());
        assertEquals(userFromDBBefore.getPassword(), userFromDBAfter.getPassword());
        assertEquals(userFromDBBefore.getFirstName(), userFromDBAfter.getFirstName());
        assertEquals(userFromDBBefore.getLastName(), userFromDBAfter.getLastName());
        assertEquals(userFromDBBefore.getEmail(), userFromDBAfter.getEmail());
    }

    private User createUser(String login, String password, String firstName, String lastName, String email) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }

    @After
    public void tearDown() throws Exception {
        databaseCleaner.cleanDatabase();

    }
}