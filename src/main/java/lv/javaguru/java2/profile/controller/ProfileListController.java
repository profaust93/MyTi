package lv.javaguru.java2.profile.controller;

import lv.javaguru.java2.profile.domain.UserProfileList;

import lv.javaguru.java2.profile.exception.UserProfileException;
import lv.javaguru.java2.profile.service.UserProfileService;
import lv.javaguru.java2.security.SecurityService;
import lv.javaguru.java2.security.UserSecurityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Ruslan on 2016.06.08..
 */
@Controller
public class ProfileListController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserProfileService userProfileService;

    @RequestMapping(value = "/profiles",method = RequestMethod.GET)
    public ModelAndView processGet(HttpServletRequest req){
        ModelAndView modelAndView = new ModelAndView("profileList");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSecurityEntity user =(UserSecurityEntity)authentication.getPrincipal();

        try {
            List<UserProfileList> profiles = userProfileService.getAllUserProfile();
            modelAndView.addObject("data",profiles);
        } catch (UserProfileException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

}
