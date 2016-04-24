package lv.javaguru.java2.controller.profile;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.domain.UserProfileList;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.UserProfileException;
import lv.javaguru.java2.service.userProfile.UserProfileListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan on 2016.04.23..
 */
@Component
public class ViewUserProfileListController implements MVCController{

    @Autowired
    UserProfileListService userProfileListService;


    @Override
    public MVCModel processGet(HttpServletRequest req){
        List<UserProfileList> list = new ArrayList<>();
        try{
            list = userProfileListService.getAllUserProfile();

        } catch (UserProfileException e){
            e.printStackTrace();
        }
        return new MVCModel("/viewUserProfileList",list);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
