package lv.javaguru.java2.service.challenge;

import lv.javaguru.java2.database.ChallengeMessageDAO;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.ChallengeMessage;

import lv.javaguru.java2.model.exceptions.ChallengeException;
import lv.javaguru.java2.service.validators.Validators;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.time.LocalDateTime;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ChallengeMessageServiceTest {

    private final Long CHALLENGE_ID = 1L;
    private final String CHALLENGE_NAME = "Test Challenge";
    private final Long SENDER_ID = 10L;
    private final Long RECEIVER_ID = 11L;
    private final String CHALLENGE_STATE = "Pending";
    private final String DESC = "Challenge test description";
    private final LocalDateTime END_TIME = LocalDateTime.now();
    private final String ACCEPT_STATE = "Accept";
    private final String REJECT_STATE = "Reject";
    private final Long MESSAGE_ID = 12L;

    private Challenge challenge;

    private ChallengeMessage challengeMessage;


    @Mock
    private ChallengeMessageDAO challengeMessageDAO;

    @Mock
    private ChallengeService challengeService;

    @Mock
    private Validators validators;

    @InjectMocks
    ChallengeMessageService challengeMessageService = new ChallengeMessageServiceImpl();

    @Before
    public void setUp() throws DBException, ChallengeException {
        challenge = new Challenge()
                .setChallengeName(CHALLENGE_NAME)
                .setFromUserId(SENDER_ID)
                .setToUserId(RECEIVER_ID)
                .setChallengeState(CHALLENGE_STATE)
                .setDescription(DESC)
                .setEndTime(END_TIME)
                .setChallengeId(CHALLENGE_ID);

        when(challengeService.getChallengeById(CHALLENGE_ID)).thenReturn(challenge);

        challengeMessage = new ChallengeMessage().setMessageId(MESSAGE_ID).setChallengeId(CHALLENGE_ID).setMessage(DESC)
                .setRecipientId(RECEIVER_ID).setSenderId(SENDER_ID);
        when(challengeMessageDAO.getById(MESSAGE_ID)).thenReturn(challengeMessage);


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
        challengeMessageService.acceptMessage(MESSAGE_ID);

        verify(challengeMessageDAO).getById(MESSAGE_ID);
        verify(challengeMessageDAO).delete(challengeMessage);
        verify(challengeService).changeChallengeState(CHALLENGE_ID,ACCEPT_STATE);
    }

    @Test
    public void testRejectMessage() throws Exception {
        challengeMessageService.rejectMessage(MESSAGE_ID);

        verify(challengeMessageDAO).getById(MESSAGE_ID);
        verify(challengeMessageDAO).delete(challengeMessage);
        verify(challengeService).changeChallengeState(CHALLENGE_ID,REJECT_STATE);

    }

    @Test
    public void testGetAllMessages() throws Exception {
        challengeMessageService.getAllMessageForUser(666L);
        verify(challengeMessageDAO).getAllByRecipientId(666L);
    }

}
