package lv.javaguru.java2.controller;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.LoginException;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.user.UserModel;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    @Mock
    HttpServletRequest req;

    @Mock
    HttpSession session;

    @Mock
    UserModel userModel;


    MVCController loginController;
    @Before
    public void setUp() throws Exception {

        loginController = new LoginController(userModel);
        //mock method to return mocked session
        when(req.getSession()).thenReturn(session);
        when(req.getRequestURL()).thenReturn(new StringBuffer("SomeWeb"));
        when(req.getParameter("userCred")).thenReturn("user");
        when(req.getParameter("password")).thenReturn("password");
        when(session.getAttribute("comeFrom")).thenReturn("/index");

        Map<String,String> userInfo = new HashMap<>();
        userInfo.put("userId","1111");
        userInfo.put("userName","Name");
        when(userModel.logInUser("user","password",false))
                .thenReturn(userInfo);
    }

    @Test
    public void testProcessGetNotLoggedInUser() throws Exception {
        when(session.getAttribute("IsLoggedIn")).thenReturn(null);

        MVCModel mvcModel = loginController.processGet(req);
        assertEquals("/login.jsp",mvcModel.getJspName());
        assertNull(mvcModel.getData());
    }

    @Test
    public void testProcessGetLoggedInUser() throws Exception {
        when(session.getAttribute("IsLoggedIn")).thenReturn(true);
        try {
            loginController.processGet(req);
            fail("No exception was thrown");
        } catch (RedirectException e) {
            assertEquals("Don't need to login again",e.getMessage());
            assertEquals("/java2/index",e.getUrlToRedirect());
        }
    }

    @Test
    public void testLoginWithException() throws Exception {
        when(userModel.logInUser(anyString(),anyString(),anyBoolean()))
                .thenThrow(new LoginException("Db don't work"));
        MVCModel mvcModel = loginController.processPost(req);
        JSONObject json = (JSONObject)mvcModel.getData();
        assertEquals("NOK",json.get("status"));
        assertEquals("Db don't work",json.get("ERROR"));
        assertEquals("/json.jsp",mvcModel.getJspName());
    }



    @Test
    public void testLoginAndRedirectToPageWhereCome() throws Exception {
        when(req.getServletPath()).thenReturn("/index");

        MVCModel mvcModel = loginController.processPost(req);
        assertEquals("/json.jsp",mvcModel.getJspName());
        JSONObject json = (JSONObject)mvcModel.getData();

        assertEquals("OK",json.get("status"));
        assertEquals("Name",json.get("userName"));
        assertEquals("/index",json.get("redirectTo"));
        verify(session).setAttribute("IsLoggedIn",true);
        verify(session).setAttribute("userId","1111");
    }

    @Test
    public void testLoginAndRedirectToMainPage() throws Exception {
        when(req.getServletPath()).thenReturn("/login");
        when(req.getScheme()).thenReturn("http");
        when(req.getServerName()).thenReturn("myTi.com");
        when(req.getServerPort()).thenReturn(80);

        MVCModel mvcModel = loginController.processPost(req);
        JSONObject json = (JSONObject)mvcModel.getData();

        assertEquals("/json.jsp",mvcModel.getJspName());
        assertEquals("OK",json.get("status"));
        assertEquals("Name",json.get("userName"));
        assertEquals("http://myTi.com:80/index",json.get("redirectTo"));

    }

    @Test
    public void testLoginWhenUserModelDoNotWork() throws Exception {
        when(userModel.logInUser(anyString(),anyString(),anyBoolean())).thenReturn(null);

        MVCModel mvcModel = loginController.processPost(req);
        JSONObject json = (JSONObject)mvcModel.getData();

        assertEquals("NOK",json.get("status"));
        assertEquals("Login Error",json.get("ERROR"));
        assertEquals("/json.jsp",mvcModel.getJspName());

    }
}