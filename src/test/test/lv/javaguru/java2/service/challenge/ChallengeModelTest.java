package lv.javaguru.java2.service.challenge;

import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.domain.Challenge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
/**
 * Created by Ruslan on 2016.04.23..
 */
@RunWith(MockitoJUnitRunner.class)
public class ChallengeModelTest {

    @Mock
    private ChallengeDAO challengeDAO;

    @InjectMocks
    ChallengeModel challengeModel = new ChallengeModelImpl();

    @Test
    public void testAddNewChallenge() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setChallengeState("Pending");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setEndTime(LocalDateTime.now());
        challenge.setDescription("Hibernate");
        challengeModel.addChallenge(challenge);

        verify(challengeDAO).create(anyObject());

    }

    @Test
    public void testGetChallengeById() throws Exception {
        challengeModel.getChallengeById(666L);
        verify(challengeDAO).getById(666L);

    }

    @Test
    public void testGetAllChallengeFromUserId() throws Exception {
        challengeModel.getAllChallengeFromUserId(666L);
        verify(challengeDAO).getAllChallengeFromUserId(666L);
    }

    @Test
    public void testGetAllChallengeToUserId() throws Exception {
        challengeModel.getAllChallengeToUserId(666L);
        verify(challengeDAO).getAllChallengeToUserId(666L);
    }

    @Test
    public void testDelete() throws Exception {
        Challenge challenge = new Challenge();
        challengeModel.deleteChallenge(challenge);
        verify(challengeDAO).delete(challenge);

    }
}
