package lv.javaguru.java2.challenge.service;

import lv.javaguru.java2.challenge.domain.ChallengeMessage;
import lv.javaguru.java2.challenge.domain.ChallengeMessageList;
import lv.javaguru.java2.model.exceptions.UserMessageException;

import java.util.List;
import java.util.Map;

public interface ChallengeMessageService {

    Map<String,Object> sendMessage(ChallengeMessage challengeMessage);

    void acceptMessage(Long messageId);

    List<ChallengeMessageList> getAllMessageForUser(Long userId) throws UserMessageException;

    void rejectMessage(Long messageId);


}