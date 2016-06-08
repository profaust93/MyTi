package lv.javaguru.java2.profile.controller;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.profile.domain.UserProfileList;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.profile.exception.UserProfileException;
import lv.javaguru.java2.profile.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class ViewUserProfileListController implements MVCController{

    @Autowired
    UserProfileService userProfileService;


    @Override
    public MVCModel processGet(HttpServletRequest req){
        List<UserProfileList> list = new ArrayList<>();
        try{
            list = userProfileService.getAllUserProfile();

        } catch (UserProfileException e){
            e.printStackTrace();
        }
        return new MVCModel("/viewUserProfileList.jsp",list);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
