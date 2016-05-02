package lv.javaguru.java2.service.challenge;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ChallengeMessageDAO;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.ChallengeMessage;
import lv.javaguru.java2.domain.ChallengeMessageList;
import lv.javaguru.java2.model.exceptions.ChallengeException;
import lv.javaguru.java2.model.exceptions.UserMessageException;
import lv.javaguru.java2.service.validators.ValidatorException;
import lv.javaguru.java2.service.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ChallengeMessageServiceImpl implements ChallengeMessageService {
    @Autowired
    @Qualifier("ORM_ChallengeMessageDAO")
    ChallengeMessageDAO challengeMessageDAO;

    @Autowired
    ChallengeService challengeService;

    @Autowired
    Validators validators;
    @Override
    public Map<String,Object> sendMessage(ChallengeMessage challengeMessage) {
        Map<String,Object> map = new HashMap<>();

        try{
            validators.challengeMessageValidator(challengeMessage);
            challengeMessageDAO.create(challengeMessage);
        } catch (DBException e) {
            e.printStackTrace();
        } catch (ValidatorException e) {
            return e.getMap();
        }
        return map;
    }

    @Override
    public void acceptMessage(Long messageId) {
        try {
            ChallengeMessage challengeMessage = challengeMessageDAO.getById(messageId);
            Challenge challenge = challengeService.getChallengeById(challengeMessage.getChallengeId());
            challengeMessageDAO.delete(challengeMessage);
            challengeService.changeChallengeState(challenge.getChallengeId(),"Accept");

        } catch (DBException | ChallengeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ChallengeMessageList> getAllMessageForUser(Long userId) throws UserMessageException {
        try {
            List<ChallengeMessage> allMessageForUser = challengeMessageDAO.getAllByRecipientId(userId);
            return allMessageForUser.stream().map(challengeMessage -> new ChallengeMessageList(challengeMessage
            )).collect(Collectors.toList());
        } catch (DBException e) {
            throw new UserMessageException(e.getMessage());
        }
    }

    @Override
    public void rejectMessage(Long messageId) {
        try {
            ChallengeMessage challengeMessage = challengeMessageDAO.getById(messageId);
            Challenge challenge = challengeService.getChallengeById(challengeMessage.getChallengeId());
            challengeMessageDAO.delete(challengeMessage);
            challengeService.changeChallengeState(challenge.getChallengeId(),"Reject");

        } catch (DBException | ChallengeException e) {
            e.printStackTrace();
        }
    }
}
