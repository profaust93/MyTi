package lv.javaguru.java2.challenge.service;

import lv.javaguru.java2.challenge.database.ChallengeDAO;
import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.timelaps.validator.Validators;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.time.LocalDateTime;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChallengeServiceTest {

    private Challenge challenge;

    private final String CHALLENGE_NAME = "Test Challenge";
    private final Long SENDER_ID = 10L;
    private final Long RECIPIENT_ID = 11L;
    private final String CHALLENGE_STATE = "Pending";
    private final String ACCEPT_STATE = "Accept";
    private final String DESC = "Challenge test description";
    private final LocalDateTime END_TIME = LocalDateTime.now();

    @Mock
    private ChallengeDAO challengeDAO;

    @Mock
    private Validators validators;

    @InjectMocks
    ChallengeService challengeService = new ChallengeServiceImpl();

    @Before
    public void setUp(){
        challenge = new Challenge()
                .setToUserId(RECIPIENT_ID)
                .setFromUserId(SENDER_ID)
                .setChallengeState(CHALLENGE_STATE)
                .setDescription(DESC)
                .setChallengeName(CHALLENGE_NAME)
                .setEndTime(END_TIME);
    }

    @Test
    public void testAddNewChallenge() throws Exception {

        challengeService.addChallenge(challenge);

        verify(challengeDAO).create(anyObject());

    }

    @Test
    public void testGetChallengeById() throws Exception {
        challengeService.getChallengeById(666L);
        verify(challengeDAO).getById(666L);

    }

    @Test
    public void testGetAllChallengeFromUserId() throws Exception {
        challengeService.getAllChallengeFromUserId(666L);
        verify(challengeDAO).getAllChallengeFromUserId(666L);
    }

    @Test
    public void testGetAllChallengeToUserId() throws Exception {
        challengeService.getAllChallengeToUserId(666L);
        verify(challengeDAO).getAllChallengeToUserId(666L);
    }

    @Test
    public void testDelete() throws Exception {
        challengeService.deleteChallenge(challenge);
        verify(challengeDAO).delete(challenge);

    }

    @Test
    public void testChangeState() throws Exception {
        when(challengeDAO.getById(challenge.getChallengeId())).thenReturn(challenge);
        challengeService.changeChallengeState(challenge.getChallengeId(),ACCEPT_STATE);
        verify(challengeDAO).update(challenge);
    }
}
