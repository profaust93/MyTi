package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Ruslan on 2016.04.20..
 */
public class TimeLapsDAOImplTest extends SpringIntegration {
    @Autowired
    @Qualifier("ORM_TimeLapsDAO")
    private TimeLapsDAO timeLapsDAO;

   // private TimeLaps createdTimeLaps;

    /*
    @Before
    public void setUp() throws Exception{
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setTimeLapsName("Name");
        timeLaps.setUserId(666L);
        timeLaps.setCompleteTime(LocalDateTime.of(2014, Month.DECEMBER, 1, 10, 10, 30));
        timeLaps.setCategory("Fun");
        timeLapsDAO.create(timeLaps);
        this.createdTimeLaps = timeLaps;
    }*/

    @Test
    @Transactional
    public void testCreate() throws DBException {
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setShortDescription("ShortDescription");
        timeLaps.setLongDescription("Hibernate");
        timeLaps.setCategory("sport");
        timeLaps.setUserId(2L);
        timeLaps.setTimeLapsName("timelaps");
        timeLapsDAO.create(timeLaps);

        TimeLaps timeLapsFromDb = timeLapsDAO.getById(timeLaps.getTimeLapsId());
        assertNotNull(timeLapsFromDb);
        assertEquals(timeLaps.getTimeLapsId(),timeLapsFromDb.getTimeLapsId());

    }

    @Test
    @Transactional
    public void testUpdate() throws DBException{
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setTimeLapsName("Name");
        timeLaps.setUserId(666L);
        timeLaps.setCompleteTime(LocalDateTime.of(2014, Month.DECEMBER, 1, 10, 10, 30));
        timeLaps.setCategory("Fun");
        timeLapsDAO.create(timeLaps);
        timeLaps.setTimeLapsName("Renamed");
        assertEquals("Renamed",timeLaps.getTimeLapsName());
    }

    @Test
    @Transactional
    public void testDelete() throws DBException{
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setShortDescription("ShortDescription");
        timeLaps.setLongDescription("Hibernate");
        timeLaps.setCategory("sport");
        timeLaps.setUserId(3L);
        timeLaps.setTimeLapsName("timelaps");
        timeLapsDAO.create(timeLaps);

        TimeLaps timeLaps1 = new TimeLaps();
        timeLaps1.setCompleteTime(LocalDateTime.now());
        timeLaps1.setShortDescription("ShortDescription");
        timeLaps1.setLongDescription("LongDescription");
        timeLaps1.setCategory("sport");
        timeLaps1.setUserId(3L);
        timeLaps1.setTimeLapsName("timelaps");
        timeLapsDAO.create(timeLaps1);

        Long timeLapsId = timeLaps.getTimeLapsId();
        timeLapsDAO.delete(timeLaps);
        assertNull(timeLapsDAO.getById(timeLapsId));
        assertNotNull(timeLapsDAO.getById(timeLaps1.getTimeLapsId()));
    }

    @Test
    @Transactional
    public void getAllTimeLapsTest() throws DBException{
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setShortDescription("ShortDescription");
        timeLaps.setLongDescription("Hibernate");
        timeLaps.setCategory("sport");
        timeLaps.setTimeLapsName("timelaps");
        timeLaps.setUserId(3L);
        timeLapsDAO.create(timeLaps);

        TimeLaps timeLaps1 = new TimeLaps();
        timeLaps1.setCompleteTime(LocalDateTime.now());
        timeLaps1.setShortDescription("ShortDescription");
        timeLaps1.setLongDescription("Hibernate");
        timeLaps1.setCategory("sport");
        timeLaps1.setTimeLapsName("timelaps");
        timeLaps1.setUserId(2L);
        timeLapsDAO.create(timeLaps1);


        List<TimeLaps> timeLapsList = timeLapsDAO.getAllTimeLaps();

        assertEquals(2,timeLapsList.size());

    }

    @Test
    @Transactional
    public void deleteAllTimeLapsTest() throws Exception {
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setShortDescription("ShortDescription");
        timeLaps.setLongDescription("Hibernate");
        timeLaps.setCategory("sport");
        timeLaps.setTimeLapsName("timelaps");
        timeLaps.setUserId(3L);
        timeLapsDAO.create(timeLaps);

        TimeLaps timeLaps1 = new TimeLaps();
        timeLaps1.setCompleteTime(LocalDateTime.now());
        timeLaps1.setShortDescription("ShortDescription");
        timeLaps1.setLongDescription("LongDescription");
        timeLaps1.setCategory("sport");
        timeLaps1.setUserId(2L);
        timeLaps1.setTimeLapsName("timelaps");
        timeLapsDAO.create(timeLaps1);

        timeLapsDAO.deleteAllTimeLaps(3L);

        assertEquals(0,timeLapsDAO.getAllTimeLapsByUserId(3L).size());
        assertEquals(1,timeLapsDAO.getAllTimeLaps().size());

    }
}
