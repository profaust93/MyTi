package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.domain.Challenge;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Ruslan on 2016.04.20..
 */

public class ChallengeDAOImplTest extends SpringIntegration {

    @Autowired
    @Qualifier("ORM_ChallengeDAO")
    private ChallengeDAO challengeDAO;

    @Test
    @Transactional
    public void testCreate() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Hibernate");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);

        Challenge challengeFromDb = challengeDAO.getById(challenge.getChallengeId());
        assertNotNull(challengeFromDb);
        assertEquals(challenge.getChallengeId(),challengeFromDb.getChallengeId());

    }
    @Test
    @Transactional
    public void testDelete() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Hibernate");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);
        Challenge challenge1 = new Challenge();
        challenge1.setChallengeName("Name");
        challenge1.setFromUserId(1L);
        challenge1.setToUserId(2L);
        challenge1.setChallengeState("pending");
        challenge1.setDescription("Hibernate");
        challenge1.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge1);

        Long challengeId = challenge.getChallengeId();

        challengeDAO.delete(challenge);
        assertNull(challengeDAO.getById(challengeId));
        assertNotNull(challengeDAO.getById(challenge1.getChallengeId()));
    }
    @Test
    @Transactional
    public void testUpdate() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Hibernate");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);
        Challenge challengeFromDb = challengeDAO.getById(challenge.getChallengeId());

        challengeFromDb.setChallengeState("Accepted");
        challengeDAO.update(challengeFromDb);



        assertNotNull(challengeFromDb);
        assertEquals(challengeFromDb.getFromUserId(),challenge.getFromUserId());
        assertEquals(challengeFromDb.getToUserId(),challenge.getToUserId());
        assertEquals(challengeFromDb.getDescription(),challenge.getDescription());
        assertEquals(challengeFromDb.getChallengeName(),challenge.getChallengeName());
        assertEquals("Accepted",challengeFromDb.getChallengeState());

    }

    @Test
    @Transactional
    public void testGetAllChallenge() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Hibernate");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);

        Challenge challenge1 = new Challenge();
        challenge1.setChallengeName("Name");
        challenge1.setFromUserId(1L);
        challenge1.setToUserId(2L);
        challenge1.setChallengeState("pending");
        challenge1.setDescription("Hibernate");
        challenge1.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge1);



        List<Challenge> list = challengeDAO.getAllChallenge();
        assertEquals(2,list.size());

    }

    @Test
    @Transactional
    public void testGetAllChallengeFromUserId() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Hibernate");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);

        Challenge challenge1 = new Challenge();
        challenge1.setChallengeName("Name");
        challenge1.setFromUserId(1L);
        challenge1.setToUserId(2L);
        challenge1.setChallengeState("pending");
        challenge1.setDescription("Hibernate");
        challenge1.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge1);

        List<Challenge> list = challengeDAO.getAllChallengeFromUserId(1L);
        assertEquals(2,list.size());

    }

    @Test
    @Transactional
    public void testGetAllChallengeToUserId() throws Exception {
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Hibernate");
        challenge.setEndTime(LocalDateTime.now());
        challengeDAO.create(challenge);

        List<Challenge> list = challengeDAO.getAllChallengeToUserId(2L);

        assertEquals(1,list.size());

    }
}
