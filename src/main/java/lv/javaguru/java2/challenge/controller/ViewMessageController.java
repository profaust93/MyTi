package lv.javaguru.java2.challenge.controller;

import lv.javaguru.java2.challenge.domain.ChallengeMessageList;
import lv.javaguru.java2.challenge.service.ChallengeMessageService;
import lv.javaguru.java2.controller.MVCController;

import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.UserMessageException;
import lv.javaguru.java2.security.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ViewMessageController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    ChallengeMessageService challengeMessageService;

    @RequestMapping(value = "/viewMessage", method = RequestMethod.GET)
    public ModelAndView processGet(HttpServletRequest req) throws RedirectException {
        List<ChallengeMessageList> list;
        ModelAndView modelAndView = new ModelAndView("viewMessage");


        try {
            list = challengeMessageService.getAllMessageForUser(securityService.getCurrentUserId());
            modelAndView.addObject("data",list);
        } catch (UserMessageException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @RequestMapping(value = "/acceptMessage", method = RequestMethod.GET)
    public String acceptMessage(HttpServletRequest req) throws RedirectException {

            challengeMessageService.acceptMessage(Long.parseLong(req.getParameter("AcceptMessageId")));

        return "redirect:/viewMessage";
    }
    @RequestMapping(value = "/rejectMessage", method = RequestMethod.GET)
    public String rejectMessage(HttpServletRequest req){

        challengeMessageService.rejectMessage(Long.parseLong(req.getParameter("RejectMessageId")));

        return "redirect:/viewMessage";
    }
}
