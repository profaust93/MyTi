package lv.javaguru.java2.challenge.database;


import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.config.TestSpringConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class)
@Rollback(true)
public class ChallengeDAOImplTest {

    private Challenge firstChallenge;
    private Challenge secondChallenge;
    private final String NAME = "Name";

    private final Long FIRST_SENDER_ID = 12L;
    private final Long SECOND_SENDER_ID = 15L;

    private final Long FIRST_RECIPIENT_ID = 13L;
    private final Long SECOND_RECIPIENT_ID = 19L;

    private final String DESCRIPTION = "Description";
    private final String PENDING_STATE = "Pending";
    @Autowired
    private ChallengeDAO challengeDAO;

    @Before
    @Transactional
    public void setUp() throws Exception{
        firstChallenge = new Challenge()
                .setChallengeName(NAME)
                .setFromUserId(FIRST_SENDER_ID)
                .setToUserId(FIRST_RECIPIENT_ID)
                .setDescription(DESCRIPTION)
                .setChallengeState(PENDING_STATE);

        secondChallenge = new Challenge()
                .setChallengeName(NAME)
                .setFromUserId(SECOND_SENDER_ID)
                .setToUserId(SECOND_RECIPIENT_ID)
                .setDescription(DESCRIPTION)
                .setChallengeState(PENDING_STATE);
    }
    @Test
    @Transactional
    public void testCreate() throws Exception {
        challengeDAO.create(firstChallenge);

        Challenge challengeFromDb = challengeDAO.getById(firstChallenge.getChallengeId());
        assertNotNull(challengeFromDb);
        assertEquals(firstChallenge.getChallengeId(),challengeFromDb.getChallengeId());

    }
    @Test
    @Transactional
    public void testDelete() throws Exception {
        challengeDAO.create(firstChallenge);
        challengeDAO.create(secondChallenge);

        Long challengeId = firstChallenge.getChallengeId();

        challengeDAO.delete(firstChallenge);
        assertNull(challengeDAO.getById(challengeId));
        assertNotNull(challengeDAO.getById(secondChallenge.getChallengeId()));
    }
    @Test
    @Transactional
    public void testUpdate() throws Exception {

        challengeDAO.create(firstChallenge);

        Challenge challengeFromDb = challengeDAO.getById(firstChallenge.getChallengeId());

        challengeFromDb.setChallengeState("Accepted");
        challengeDAO.update(challengeFromDb);



        assertNotNull(challengeFromDb);
        assertEquals(challengeFromDb.getFromUserId(),firstChallenge.getFromUserId());
        assertEquals(challengeFromDb.getToUserId(),firstChallenge.getToUserId());
        assertEquals(challengeFromDb.getDescription(),firstChallenge.getDescription());
        assertEquals(challengeFromDb.getChallengeName(),firstChallenge.getChallengeName());
        assertEquals("Accepted",challengeFromDb.getChallengeState());

    }

    @Test
    @Transactional
    public void testGetAllChallenge() throws Exception {

        challengeDAO.create(firstChallenge);
        challengeDAO.create(secondChallenge);
        List<Challenge> list = challengeDAO.getAllChallenge();
        assertEquals(2,list.size());

    }

    @Test
    @Transactional
    public void testGetAllChallengeFromUserId() throws Exception {

        challengeDAO.create(firstChallenge);

        challengeDAO.create(secondChallenge);

        List<Challenge> list = challengeDAO.getAllChallengeFromUserId(FIRST_SENDER_ID);
        assertEquals(1,list.size());

    }

    @Test
    @Transactional
    public void testGetAllChallengeToUserId() throws Exception {

        challengeDAO.create(firstChallenge);

        List<Challenge> list = challengeDAO.getAllChallengeToUserId(FIRST_RECIPIENT_ID);

        assertEquals(1,list.size());

    }
}
