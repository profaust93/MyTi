package lv.javaguru.java2.controller.profile;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserProfileDAOImpl;
import lv.javaguru.java2.domain.UserProfile;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.UserProfileException;
import lv.javaguru.java2.service.userProfile.UserProfileModel;
import lv.javaguru.java2.service.userProfile.UserProfileModelImpl;
import lv.javaguru.java2.service.userProfile.ProfileServices;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Camille on 07.04.2016.
 * processGet:
 * Check if user logged in
 * yes:
 * Check if profile exists if:
 * - true getProfile
 * - false create empty Profile
 * no: pshel v zopu
 */
@Component
public class ViewUserProfileController implements MVCController{
    ProfileServices profileServices = new ProfileServices();
    UserProfileModel userProfileModel = new UserProfileModelImpl();
    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {

        userProfileModel.setUserProfileDAO(new UserProfileDAOImpl());

        UserDTO userDTO;
        HttpSession session = req.getSession();
        userDTO = (UserDTO)session.getAttribute("user");
        if ((Boolean) session.getAttribute("IsLoggedIn")){
            //check if profile already exists
            try {
                if (profileServices.profileExist(userDTO.getUserId())){
                    UserProfile userProfile = null;
                    try {
                        userProfile = userProfileModel.getUserProfile(userDTO.getUserId());
                    } catch (UserProfileException e) {
                        e.printStackTrace();
                    }
                    return new MVCModel("/viewUserProfile.jsp",userProfile);
                            //http://localhost:8080/java2/viewUserProfile
                 }else {
                    return new MVCModel("/redirect.jsp","editUserProfile");
                }
            } catch (DBException e) {
                e.printStackTrace();
            }

        }

        return null;//??
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
