package lv.javaguru.java2.controller.challenge;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.ChallengeMessage;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.service.challenge.ChallengeMessageService;
import lv.javaguru.java2.service.validators.ModelChecks;
import lv.javaguru.java2.service.challenge.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruslan on 16.18.4.
 */
@Component
public class AddChallengeController implements MVCController {
    @Autowired
    ModelChecks modelChecks;
    @Autowired
    ChallengeService challengeService;
    @Autowired
    ChallengeMessageService messageService;
    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        Map<String,Object> dataMap =new HashMap<>();
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        dataMap.put("senderId",String.valueOf(userDTO.getUserId()));
        dataMap.put("recipientId", req.getParameter("recipientId"));
        return new MVCModel("/addChallenge.jsp",dataMap);
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) throws RedirectException {
        Map<String,Object> resultCheckMap;
        Challenge challenge = new Challenge();

        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        challenge.setFromUserId(userDTO.getUserId());
        challenge.setToUserId(Long.valueOf(req.getParameter("recipientId")));
        challenge.setChallengeName(req.getParameter("name"));
        challenge.setDescription(req.getParameter("description"));
        challenge.setEndTime(modelChecks.dateConvert(req.getParameter("date")));

        resultCheckMap = challengeService.addChallenge(challenge);
        if(resultCheckMap.size() != 0) return new MVCModel("/addChallenge.jsp",resultCheckMap);


        ChallengeMessage challengeMessage = new ChallengeMessage();
        challengeMessage.setChallengeId(challenge.getChallengeId());
        challengeMessage.setMessage(challenge.getDescription());
        challengeMessage.setSenderId(challenge.getFromUserId());
        challengeMessage.setRecipientId(challenge.getToUserId());

        messageService.sendMessage(challengeMessage);



        return new MVCModel("/redirect.jsp","index");
    }
}
