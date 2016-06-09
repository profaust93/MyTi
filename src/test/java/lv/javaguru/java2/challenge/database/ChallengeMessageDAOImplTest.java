package lv.javaguru.java2.challenge.database;


import lv.javaguru.java2.challenge.domain.Challenge;
import lv.javaguru.java2.challenge.domain.ChallengeMessage;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class)
@Rollback(true)
public class ChallengeMessageDAOImplTest  {

    private ChallengeMessage firstChallengeMessage;
    private ChallengeMessage secondChallengeMessage;


    private final Long FIRST_SENDER_ID = 12L;
    private final Long SECOND_SENDER_ID = 15L;

    private final Long FIRST_RECIPIENT_ID = 13L;
    private final Long SECOND_RECIPIENT_ID = 19L;

    private final String MESSAGE = "Description";


    @Before
    @Transactional
    public void setUp(){
        firstChallengeMessage = new ChallengeMessage()
                .setMessage(MESSAGE)
                .setChallengeId(new Challenge().getChallengeId())
                .setSenderId(FIRST_SENDER_ID)
                .setRecipientId(FIRST_RECIPIENT_ID);

        secondChallengeMessage = new ChallengeMessage()
                .setMessage(MESSAGE)
                .setChallengeId(new Challenge().getChallengeId())
                .setSenderId(SECOND_SENDER_ID)
                .setRecipientId(SECOND_RECIPIENT_ID);
    }




    @Autowired
    @Qualifier("ORM_ChallengeMessageDAO")
    ChallengeMessageDAO challengeMessageDAO;

    @Test
    @Transactional
    public void testCreate() throws Exception {

        challengeMessageDAO.create(firstChallengeMessage);

        ChallengeMessage challengeMessageFromDB = challengeMessageDAO.getById(firstChallengeMessage.getMessageId());
        assertNotNull(challengeMessageFromDB);
        assertEquals(firstChallengeMessage.getMessageId(),challengeMessageFromDB.getMessageId());

    }

    @Test
    @Transactional
    public void testDelete() throws Exception {

        challengeMessageDAO.create(firstChallengeMessage);


        challengeMessageDAO.create(secondChallengeMessage);

        challengeMessageDAO.delete(secondChallengeMessage);

        assertNull(challengeMessageDAO.getById(secondChallengeMessage.getMessageId()));
        assertNotNull(challengeMessageDAO.getById(firstChallengeMessage.getMessageId()));
    }

    @Test
    @Transactional
    public void testGetAllMessagesByUserId() throws Exception {

        challengeMessageDAO.create(firstChallengeMessage);


        challengeMessageDAO.create(secondChallengeMessage);

        List<ChallengeMessage> list = challengeMessageDAO.getAllByRecipientId(FIRST_RECIPIENT_ID);

        assertEquals(1,list.size());

    }

}
