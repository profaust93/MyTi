package lv.javaguru.java2.controller.profile;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.DBException;
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

/**
 * Created by Camille on 07.04.2016.
 */
@Component
public class ViewUserProfileController implements MVCController{

    /**
     * processGet:
     * Check if user logged in
     * yes:
     * Check if profile exists if:
     * - true getProfile
     * - false create empty Profile
     * no: pshel v zopu
     *
     */
    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        ProfileServices profileServices = new ProfileServices();
        UserProfileModel userProfileModel = new UserProfileModelImpl();
        userProfileModel.setUserProfileDAO(new UserProfileDAOImpl());

        UserDTO userDTO;
        HttpSession session = req.getSession();
        userDTO = (UserDTO)session.getAttribute("user");
        session.setAttribute("IsLoggedIn",true);
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
                    return new MVCModel("/redirect.jsp","EditProfile");
                }
            } catch (DBException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
