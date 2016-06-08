package lv.javaguru.java2.profile.controller;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.profile.database.UserProfileDAO;
import lv.javaguru.java2.profile.domain.UserProfile;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.profile.exception.UserProfileException;
import lv.javaguru.java2.profile.service.UserProfileService;
import lv.javaguru.java2.profile.service.UserProfileServiceImpl;
import lv.javaguru.java2.profile.service.ProfileServices;
import lv.javaguru.java2.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Camille on 07.04.2016.
 *
 * processGet:
 * if profile exists =true - fill the fields with editable existing data.
 *  return user
 *  =false - return empty form
 *  processPost:
 * if profile exists =true - updateUserProfile
 *   =false - create user from req.paramteters
 */
@Component
public class EditUserProfileController implements MVCController {
    @Autowired
    ProfileServices profileServices;

    @Autowired
    @Qualifier("ORM_UserProfileDAO")
    UserProfileDAO userProfileDao;

    @Autowired
    private SecurityService securityService;

    UserProfileService userProfileService = new UserProfileServiceImpl();


    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {

        HttpSession session = req.getSession();

        UserProfile userProfile = new UserProfile();
        userProfileService.setUserProfileDAO(userProfileDao);
        try {
            if (profileServices.profileExist(securityService.getCurrentUserId())){
                   userProfile = userProfileService.getUserProfile(securityService.getCurrentUserId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MVCModel("/editUserProfile.jsp",userProfile);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {

        /**
         * check if profile exists true - update existing, return view with new data,
         * false - create profile, return view with new profile data
         */
        HttpSession session = req.getSession();
        Map <String, Object> profileData = new HashMap<>();
        profileData.put("firstName",req.getParameter("firstName"));
        profileData.put("lastName",req.getParameter("lastName"));
        profileData.put("email",req.getParameter("email"));
        try {
            profileData.put("userId",securityService.getCurrentUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserProfile userProfile = null;
        userProfileService.setUserProfileDAO(userProfileDao);
      try {
           if (profileServices.profileExist(securityService.getCurrentUserId())){

               userProfileService.updateUserProfile(profileData);
               userProfile = userProfileService.getUserProfile(securityService.getCurrentUserId());
               return new MVCModel("/viewUserProfile.jsp",userProfile);
           }

       userProfileService.createUserProfile(profileData);
        userProfile = userProfileService.getUserProfile(securityService.getCurrentUserId());

       } catch (Exception e) {
          e.printStackTrace();
      }

        return new MVCModel("/viewUserProfile.jsp",userProfile);
    }

}
