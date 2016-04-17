package lv.javaguru.java2.controller.profile;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserProfileDAO;
import lv.javaguru.java2.database.jdbc.UserProfileDAOImpl;
import lv.javaguru.java2.domain.UserProfile;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.UserProfileException;
import lv.javaguru.java2.model.profile.UserProfileModel;
import lv.javaguru.java2.model.profile.UserProfileModelImpl;
import lv.javaguru.java2.service.userProfile.ProfileServices;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    ProfileServices profileServices = new ProfileServices();
    UserProfileModel userProfileModel = new UserProfileModelImpl();
    UserProfileDAO userProfileDaoImpl = new UserProfileDAOImpl();

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {

        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
        UserProfile userProfile = null;
        userProfileModel.setUserProfileDAO(userProfileDaoImpl);
        try {
            if (profileServices.profileExist(userDTO.getUserId())){

                //userProfileModel.setUserProfileDAO(); + create userProfileDaoImpl object
                userProfile = userProfileModel.getUserProfile(userDTO.getUserId());

            }
        } catch (DBException e) {
            e.printStackTrace();
        }catch (UserProfileException e){
            e.printStackTrace();
        }
         return new MVCModel("/editUserProfile.jsp",null);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {

        /**
         * check if profile exists true - update existing, return view with new data,
         * false - create profile, return view with new profile data
         */
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        Map profileData =  req.getParameterMap();
        profileData.put("userId",userDTO.getUserId());
        UserProfile userProfile = null;
        userProfileModel.setUserProfileDAO(userProfileDaoImpl);
       try {
           if (profileServices.profileExist(userDTO.getUserId())){

               userProfileModel.updateUserProfile(profileData);
               userProfile = userProfileModel.getUserProfile(userDTO.getUserId());
               return new MVCModel("/viewUserProfile.jsp",userProfile);
           }

        userProfileModel.createUserProfile(profileData);
        userProfile = userProfileModel.getUserProfile(userDTO.getUserId());

       } catch (DBException e){
           e.printStackTrace();
       } catch (UserProfileException e){
           e.printStackTrace();
       }

        return new MVCModel("/viewUserProfile.jsp",userProfile);
    }

}
