package lv.javaguru.java2.service.challenge;


import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.database.ChallengeMessageDAO;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.ChallengeMessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

/**
 * Created by Ruslan on 2016.04.28..
 */
@RunWith(MockitoJUnitRunner.class)
public class ChallengeMessageServiceTest {

    private Challenge createdChallenge;
    private ChallengeMessage createdMessage;

    @Mock
    private ChallengeMessageDAO challengeMessageDAO;
    @Mock
    private ChallengeDAO challengeDAO;

    @InjectMocks
    ChallengeMessageService challengeMessageService = new ChallengeMessageServiceImpl();
    @InjectMocks
    ChallengeService challengeService = new ChallengeServiceImpl();

    @Before
    public void setUp() throws DBException {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("ABC");
        challenge.setEndTime(LocalDateTime.now());

        challengeDAO.create(challenge);

        createdChallenge = challenge;

        ChallengeMessage challengeMessage = new ChallengeMessage();
        challengeMessage.setChallengeId(challenge.getChallengeId());
        challengeMessage.setRecipientId(1L);
        challengeMessage.setSenderId(2L);
        challengeMessage.setMessage("ASD");
        challengeMessageDAO.create(challengeMessage);

        createdMessage = challengeMessage;

        System.out.println(challenge.getChallengeId());
        System.out.println(challengeMessage.getChallengeId());


    }


    @Test
    public void testSendMessage() throws Exception {
        ChallengeMessage challengeMessage = new ChallengeMessage();
        challengeMessage.setChallengeId(1L);
        challengeMessage.setRecipientId(1L);
        challengeMessage.setSenderId(2L);
        challengeMessage.setMessage("ASD");
        challengeMessageService.sendMessage(challengeMessage);
        verify(challengeMessageDAO).create(challengeMessage);
    }

    @Test
    public void testAcceptMessage() throws Exception {

    }

    @Test
    public void testGetAllMessages() throws Exception {
        challengeMessageService.getAllMessageForUser(666L);
        verify(challengeMessageDAO).getAllByRecipientId(666L);
    }

}
