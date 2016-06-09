package lv.javaguru.java2.challenge.controller;

import lv.javaguru.java2.challenge.domain.ChallengeList;
import lv.javaguru.java2.challenge.service.ChallengeService;
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

@Controller
public class ViewChallengeController {
    @Autowired
    ChallengeService challengeService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/viewChallenge", method = RequestMethod.GET)
    public ModelAndView processGet(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView("viewChallenge");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSecurityEntity user =(UserSecurityEntity)authentication.getPrincipal();
        List<ChallengeList> challenges;

        try {
            challenges = challengeService.getAllChallengeToUserId(securityService.getCurrentUserId());
            modelAndView.addObject("data",challenges);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @RequestMapping(value = "/goToMsg", method = RequestMethod.GET)
    public String processPost() {
        return "redirect:/viewMessage";
    }
}
