package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.database.ChallengeMessageDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Challenge;
import lv.javaguru.java2.domain.ChallengeMessage;
import org.junit.Before;
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
 * Created by Ruslan on 2016.04.28..
 */
public class ChallengeMessageDAOImplTest extends SpringIntegration {

    private Challenge createdChallenge;

    @Autowired
    @Qualifier("ORM_ChallengeMessageDAO")
    ChallengeMessageDAO challengeMessageDAO;

    @Autowired
    @Qualifier("ORM_ChallengeDAO")
    ChallengeDAO challengeDAO;

    @Before
    public void setUp(){
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Name");
        challenge.setFromUserId(1L);
        challenge.setToUserId(2L);
        challenge.setChallengeState("pending");
        challenge.setDescription("Hibernate");
        challenge.setEndTime(LocalDateTime.now());
        try {
            challengeDAO.create(challenge);
        } catch (DBException e) {
            e.printStackTrace();
        }
        createdChallenge = challenge;
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        ChallengeMessage challengeMessage = new ChallengeMessage();
        challengeMessage.setChallengeId(createdChallenge.getChallengeId());
        challengeMessage.setMessage("ASD");
        challengeMessage.setSenderId(1L);
        challengeMessage.setRecipientId(2L);
        challengeMessageDAO.create(challengeMessage);

        ChallengeMessage challengeMessageFromDB = challengeMessageDAO.getById(challengeMessage.getMessageId());
        assertNotNull(challengeMessageFromDB);
        assertEquals(challengeMessage.getMessageId(),challengeMessageFromDB.getMessageId());

    }

    @Test
    public void testDelete() throws Exception {
        ChallengeMessage challengeMessage = new ChallengeMessage();
        challengeMessage.setChallengeId(createdChallenge.getChallengeId());
        challengeMessage.setMessage("ASD");
        challengeMessage.setSenderId(1L);
        challengeMessage.setRecipientId(3L);
        challengeMessageDAO.create(challengeMessage);

        ChallengeMessage challengeMessage1 = new ChallengeMessage();
        challengeMessage1.setChallengeId(createdChallenge.getChallengeId());
        challengeMessage1.setMessage("ASD");
        challengeMessage1.setSenderId(1L);
        challengeMessage1.setRecipientId(2L);
        challengeMessageDAO.create(challengeMessage1);

        challengeMessageDAO.delete(challengeMessage1);

        assertNull(challengeMessageDAO.getById(challengeMessage1.getMessageId()));
        assertNotNull(challengeMessageDAO.getById(challengeMessage.getMessageId()));
    }

    @Test
    public void testGetAllMessagesByUserId() throws Exception {
        ChallengeMessage challengeMessage = new ChallengeMessage();
        challengeMessage.setChallengeId(createdChallenge.getChallengeId());
        challengeMessage.setMessage("ASD");
        challengeMessage.setSenderId(1L);
        challengeMessage.setRecipientId(5L);
        challengeMessageDAO.create(challengeMessage);

        ChallengeMessage challengeMessage1 = new ChallengeMessage();
        challengeMessage1.setChallengeId(createdChallenge.getChallengeId());
        challengeMessage1.setMessage("ASD");
        challengeMessage1.setSenderId(1L);
        challengeMessage1.setRecipientId(2L);
        challengeMessageDAO.create(challengeMessage1);

        List<ChallengeMessage> list = challengeMessageDAO.getAllByRecipientId(5L);

        assertEquals(1,list.size());

    }

}
