package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Challenge;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Ruslan on 2016.04.16..
 */
public class ChallengeDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private ChallengeDAO challengeDAO = new ChallengeDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @After
    public void tearDown() throws DBException {
        databaseCleaner.cleanDatabase();

    }

    @Test
    public void testCreate() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Description");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);

        Challenge challengeFromDb = challengeDAO.getById(challenge.getChallengeId());
        assertNotNull(challengeFromDb);
        assertEquals(challenge.getChallengeId(),challengeFromDb.getChallengeId());
    }

    @Test
    public void testDelete() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Description");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);
        Challenge challenge1 = new Challenge();
        challenge1.setChallengeName("Name");
        challenge1.setFromUserId(1L);
        challenge1.setToUserId(2L);
        challenge1.setChallengeState("pending");
        challenge1.setDescription("Description");
        challenge1.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge1);

        Long challengeId = challenge.getChallengeId();

        challengeDAO.delete(challenge);
        assertNull(challengeDAO.getById(challengeId));
        assertNotNull(challengeDAO.getById(challenge1.getChallengeId()));
    }

    @Test
    public void testChangeState() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Description");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);

        challenge.setChallengeState("Accepted");

        challengeDAO.changeState(challenge);

        assertEquals(new Long(1),challenge.getFromUserId());
        assertEquals(new Long(2),challenge.getToUserId());
        assertEquals("Description",challenge.getDescription());
        assertEquals("Name",challenge.getChallengeName());
        assertEquals("Accepted",challenge.getChallengeState());

    }

    @Test
    public void testGetAllChallenge() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Description");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);
        challengeDAO.create(challenge);
        challengeDAO.create(challenge);

        List<Challenge> list = challengeDAO.getAllChallenge();
        assertEquals(3,list.size());

    }

    @Test
    public void testGetAllChallengeFromUserId() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Description");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);
        challengeDAO.create(challenge);
        challenge.setFromUserId(2L);
        challengeDAO.create(challenge);

        List<Challenge> list = challengeDAO.getAllChallengeFromUserId(1L);
        assertEquals(2,list.size());

    }

    @Test
    public void testGetAllChallengeToUserId() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Description");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);
        challengeDAO.create(challenge);
        challenge.setToUserId(1L);
        challengeDAO.create(challenge);

        List<Challenge> list = challengeDAO.getAllChallengeToUserId(2L);

        assertEquals(2,list.size());

    }
}
