package lv.javaguru.java2.controller;

import lv.javaguru.java2.controller.profile.ViewUserProfileController;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.UserProfile;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.UserProfileException;
import lv.javaguru.java2.service.userProfile.UserProfileService;
import lv.javaguru.java2.service.userProfile.ProfileServices;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Camille on 12.04.2016.
 *
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class UserProfileControllerTest {

    @Mock
    HttpServletRequest mockRequest;

    @Mock
    HttpSession mockSession;

    @Mock
    UserDTO mockUserDTO;

    @Mock
    ProfileServices mockProfileServices;

    @Mock
    UserProfileService mockUserProfileService;

    @Mock
    UserProfile mockUserProfile;

    @InjectMocks
    ViewUserProfileController userProfileController = new ViewUserProfileController();


    @Before
    public void setUp () throws UserProfileException {

        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockSession.getAttribute("user")).thenReturn(mockUserDTO);
        when(mockUserDTO.getUserId()).thenReturn(1L);
        when(mockUserProfileService.getUserProfile(1L)).thenReturn(mockUserProfile);

        //when(mockUserDTO.)
    }

    @Test
    //Test processGet when isLoggedIn = true and profileExists = true
    public void processGetTest1() throws DBException, RedirectException {

           when(mockProfileServices.profileExist(mockUserDTO.getUserId())).thenReturn(true);
           when(mockSession.getAttribute("IsLoggedIn")).thenReturn(true);

           MVCModel mvcModelTest = userProfileController.processGet(mockRequest);
                 assertEquals("/viewUserProfile.jsp", mvcModelTest.getJspName());
           assertEquals(mockUserProfile, mvcModelTest.getData());

    }

    @Test
    //Test processGet when isLoggedIn = true and profileExists = false
    public void processGetTest2(){

    }

    @Test
    //Test processGet when isLoggedIn = false
    public void processGetTest3(){

    }


}
