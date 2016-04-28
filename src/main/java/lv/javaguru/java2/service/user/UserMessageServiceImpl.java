package lv.javaguru.java2.service.user;

import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.domain.UserMessageList;
import lv.javaguru.java2.model.exceptions.ChallengeException;
import lv.javaguru.java2.model.exceptions.UserMessageException;
import lv.javaguru.java2.service.challenge.ChallengeService;
import lv.javaguru.java2.web.form.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ruslan on 2016.04.24..
 */
@Component
public class UserMessageServiceImpl implements UserMessageService {
    @Autowired
    @Qualifier("ORM_UserMessageDAO")
    UserMessageDAO userMessageDAO;

    @Autowired
    ChallengeService challengeService;

    @Override
    public void sendMessage(UserMessage userMessage) {
        try{
            userMessageDAO.create(userMessage);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void acceptMessage(Long messageId) {
        try {
            UserMessage userMessage = userMessageDAO.getById(messageId);
            Challenge challenge = challengeService.getChallengeById(userMessage.getChallengeId());
            challengeService.changeChallengeState(challenge,"Accept");
            userMessageDAO.delete(userMessage);
        } catch (DBException e) {
            e.printStackTrace();
        } catch (ChallengeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserMessageList> getAllMessageForUser(Long userId) throws UserMessageException {
        try {
            List<UserMessage> allMessageForUser = userMessageDAO.getAllByRecipientId(userId);
            return allMessageForUser.stream().map(userMessage -> new UserMessageList(
                    userMessage.getMessageId(),userMessage.getChallengeId(),userMessage.getMessage(),
                    userMessage.getSenderId(),userMessage.getRecipientId()
            )).collect(Collectors.toList());
        } catch (DBException e) {
            throw new UserMessageException(e.getMessage());
        }
    }

    @Override
    public void rejectMessage(Long messageId) {
        try {
            UserMessage userMessage = userMessageDAO.getById(messageId);
            Challenge challenge = challengeService.getChallengeById(userMessage.getChallengeId());
            userMessageDAO.delete(userMessage);
            challengeService.changeChallengeState(challenge,"Reject");

        } catch (DBException e) {
            e.printStackTrace();
        } catch (ChallengeException e) {
            e.printStackTrace();
        }
    }
}
