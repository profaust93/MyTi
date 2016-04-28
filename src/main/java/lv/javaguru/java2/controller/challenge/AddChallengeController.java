package lv.javaguru.java2.controller.challenge;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.service.user.UserMessageService;
import lv.javaguru.java2.service.validators.ModelChecks;
import lv.javaguru.java2.service.challenge.ChallengeService;
import lv.javaguru.java2.web.form.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    UserMessageService messageService;
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


        UserMessage userMessage = new UserMessage();
        userMessage.setChallengeId(challenge.getChallengeId());
        userMessage.setMessage(challenge.getDescription());
        userMessage.setSenderId(challenge.getFromUserId());
        userMessage.setRecipientId(challenge.getToUserId());

        messageService.sendMessage(userMessage);



        return new MVCModel("/redirect.jsp","index");
    }
}
