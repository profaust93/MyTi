package lv.javaguru.java2.model.login;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.model.exceptions.LoginException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginModelTest {
    LoginModel loginModel;

    @Mock
    UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        loginModel = new LoginModelImpl(userDAO);
        when(userDAO.getUserByEmailOrLogin("user"))
                .thenReturn(createUser(1L,"user","password","user","lastN","email"));

        when(userDAO.getUserByEmailOrLogin("unknown")).thenReturn(null);

    }

    @Test
    public void testLoginExistingUser() throws Exception {
        Map userInfo = loginModel.logInUser("user","password",false);
        assertEquals("1",userInfo.get("userId"));
        assertEquals("user",userInfo.get("userName"));
        assertEquals("user",userInfo.get("userLogin"));
    }

    @Test
    public void testLoginUnknownUser() throws Exception {
        try {
            loginModel.logInUser("unknown","unknownpass",false);
            fail("Exception wasn't thrown");
        } catch (LoginException e) {
            assertEquals("User Does Not Exist",e.getMessage());
        }
    }

    @Test
    public void testLoginWithWrongPassword() throws Exception {
        try {
            loginModel.logInUser("user","wrongPass",false);
            fail("Exception wasn't thrown");
        } catch (LoginException e) {
            assertEquals("Wrong Password",e.getMessage());
        }
    }
    private User createUser(Long id,String login, String password, String firstName, String lastName, String email) {
        User user = new User();
        user.setUserId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }
}