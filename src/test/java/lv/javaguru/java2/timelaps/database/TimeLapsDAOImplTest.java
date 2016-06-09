package lv.javaguru.java2.timelaps.database;

import lv.javaguru.java2.config.TestSpringConfig;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.timelaps.domain.TimeLaps;
import org.junit.Before;
import org.junit.Ignore;
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

/**
 * Created by Ruslan on 2016.04.20..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class)
@Rollback(true)
@Ignore
public class TimeLapsDAOImplTest {
    @Autowired
    @Qualifier("ORM_TimeLapsDAO")
    private TimeLapsDAO timeLapsDAO;

    private TimeLaps firstTimeLaps;
    private TimeLaps secondTimeLaps;
    private final LocalDateTime DATE = LocalDateTime.now();
    private final String SHORT_DESCRIPTION = "Short Description";
    private final String LONG_DESCRIPTION = "Long Description";
    private final String CATEGORY = "Sport";
    private final Long FIRST_USER_ID = 1L;
    private final Long SECOND_USER_ID = 2L;
    private final String NAME = "TimeLaps";

    @Before
    @Transactional
    public void setUp(){
        firstTimeLaps = new TimeLaps()
                .setTimeLapsName(NAME)
                .setCompleteTime(DATE)
                .setShortDescription(SHORT_DESCRIPTION)
                .setLongDescription(LONG_DESCRIPTION)
                .setCategory(CATEGORY)
                .setUserId(FIRST_USER_ID);

        secondTimeLaps = new TimeLaps()
                .setTimeLapsName(NAME)
                .setCompleteTime(DATE)
                .setShortDescription(SHORT_DESCRIPTION)
                .setLongDescription(LONG_DESCRIPTION)
                .setCategory(CATEGORY)
                .setUserId(SECOND_USER_ID);
    }

    @Test
    @Transactional
    public void testCreate() throws DBException {

        timeLapsDAO.create(firstTimeLaps);

        TimeLaps timeLapsFromDb = timeLapsDAO.getById(firstTimeLaps.getTimeLapsId());
        assertNotNull(timeLapsFromDb);
        assertEquals(firstTimeLaps.getTimeLapsId(),timeLapsFromDb.getTimeLapsId());

    }

    @Test
    @Transactional
    public void testUpdate() throws DBException{

        timeLapsDAO.create(firstTimeLaps);
        firstTimeLaps.setTimeLapsName("Renamed");
        assertEquals("Renamed",firstTimeLaps.getTimeLapsName());
    }

    @Test
    @Transactional
    public void testDelete() throws DBException{

        timeLapsDAO.create(firstTimeLaps);

        timeLapsDAO.create(secondTimeLaps);

        Long timeLapsId = firstTimeLaps.getTimeLapsId();
        timeLapsDAO.delete(firstTimeLaps);
        assertNull(timeLapsDAO.getById(timeLapsId));
        assertNotNull(timeLapsDAO.getById(secondTimeLaps.getTimeLapsId()));
    }

    @Test
    @Transactional
    public void getAllTimeLapsTest() throws DBException{

        timeLapsDAO.create(firstTimeLaps);

        timeLapsDAO.create(secondTimeLaps);


        List<TimeLaps> timeLapsList = timeLapsDAO.getAllTimeLaps();

        assertEquals(2,timeLapsList.size());

    }

    @Test
    @Transactional
    public void deleteAllTimeLapsTest() throws Exception {

        timeLapsDAO.create(firstTimeLaps);

        timeLapsDAO.create(secondTimeLaps);

        timeLapsDAO.deleteAllTimeLaps(FIRST_USER_ID);

        assertEquals(0,timeLapsDAO.getAllTimeLapsByUserId(FIRST_USER_ID).size());
        assertEquals(1,timeLapsDAO.getAllTimeLaps().size());

    }
}
