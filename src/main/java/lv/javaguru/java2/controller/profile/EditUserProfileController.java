package lv.javaguru.java2.controller.profile;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserProfileDAO;
import lv.javaguru.java2.domain.UserProfile;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.UserProfileException;
import lv.javaguru.java2.service.userProfile.ProfileServices;
import lv.javaguru.java2.service.userProfile.UserProfileService;
import lv.javaguru.java2.service.userProfile.UserProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    UserProfileDAO userProfileDao;
    UserProfileService userProfileService = new UserProfileServiceImpl();


    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {

        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        UserProfile userProfile = new UserProfile();
        try {
            if (profileServices.profileExist(userDTO.getUserId())){
                   userProfile = userProfileService.getUserProfile(userDTO.getUserId());
            }
        } catch (DBException e) {
            e.printStackTrace();
        }catch (UserProfileException e){
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
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        Map <String, Object> profileData = new HashMap<>();
        profileData.put("firstName",req.getParameter("firstName"));
        profileData.put("lastName",req.getParameter("lastName"));
        profileData.put("email",req.getParameter("email"));
        profileData.put("userId",userDTO.getUserId());
        UserProfile userProfile = null;
        userProfileService.setUserProfileDAO(userProfileDao);
      try {
           if (profileServices.profileExist(userDTO.getUserId())){

               userProfileService.updateUserProfile(profileData);
               userProfile = userProfileService.getUserProfile(userDTO.getUserId());
               return new MVCModel("/viewUserProfile.jsp",userProfile);
           }

       userProfileService.createUserProfile(profileData);
        userProfile = userProfileService.getUserProfile(userDTO.getUserId());

       } catch (DBException e){
           e.printStackTrace();
       } catch (UserProfileException e){
           e.printStackTrace();
      }

        return new MVCModel("/viewUserProfile.jsp",userProfile);
    }

}
