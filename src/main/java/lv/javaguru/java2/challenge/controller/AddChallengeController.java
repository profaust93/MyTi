package lv.javaguru.java2.challenge.controller;

import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.challenge.domain.ChallengeMessage;
import lv.javaguru.java2.challenge.service.ChallengeMessageService;
import lv.javaguru.java2.challenge.service.ChallengeService;
import lv.javaguru.java2.controller.MVCController;

import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.security.SecurityService;

import lv.javaguru.java2.validators.ModelChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AddChallengeController {

    @Autowired
    private SecurityService securityService;

    ModelChecks modelChecks = new ModelChecks();
    @Autowired
    ChallengeService challengeService;
    @Autowired
    ChallengeMessageService messageService;

    @RequestMapping(value = "/addChallenge", method = RequestMethod.GET)
    public ModelAndView processGet(HttpServletRequest req) throws RedirectException {
        Map<String,Object> dataMap =new HashMap<>();
        ModelAndView modelAndView = new ModelAndView("addChallenge");
        try {
            dataMap.put("senderId",String.valueOf(securityService.getCurrentUserId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataMap.put("recipientId", req.getParameter("recipientId"));
        modelAndView.addObject("data",dataMap);
        return modelAndView;
    }

    @RequestMapping(value = "/addChallenge", method = RequestMethod.POST)
    public String addChallenge(HttpServletRequest req) throws RedirectException {
        Map<String,Object> resultCheckMap;
        Challenge challenge = new Challenge();


        try {
            challenge.setFromUserId(securityService.getCurrentUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        challenge.setToUserId(Long.valueOf(req.getParameter("recipientId")));
        challenge.setChallengeName(req.getParameter("name"));
        challenge.setDescription(req.getParameter("description"));
        challenge.setEndTime(modelChecks.dateConvert(req.getParameter("date")));

        resultCheckMap = challengeService.addChallenge(challenge);

        if(resultCheckMap.size() != 0) return "redirect:/addChallenge";


        ChallengeMessage challengeMessage = new ChallengeMessage();
        challengeMessage.setChallengeId(challenge.getChallengeId());
        challengeMessage.setMessage(challenge.getDescription());
        challengeMessage.setSenderId(challenge.getFromUserId());
        challengeMessage.setRecipientId(challenge.getToUserId());

        messageService.sendMessage(challengeMessage);

        return "redirect:/viewChallenge";
    }
}
