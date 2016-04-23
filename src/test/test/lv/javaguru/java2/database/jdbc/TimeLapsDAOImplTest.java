package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by ruslan on 16.22.3.
 */
public class TimeLapsDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private TimeLapsDAO timeLapsDAO = new TimeLapsDAOImpl();


    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @After
    public void tearDown() throws DBException {
        databaseCleaner.cleanDatabase();

    }
    @Test
    public void testCreate() throws DBException{
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setShortDescription("ShortDescription");
        timeLaps.setLongDescription("LongDescription");
        timeLaps.setCategory("sport");
        timeLaps.setUserId(2L);
        timeLaps.setTimeLapsName("timelaps");
        timeLapsDAO.create(timeLaps);

        TimeLaps timeLapsFromDb = timeLapsDAO.getById(timeLaps.getTimeLapsId());
        assertNotNull(timeLapsFromDb);
        assertEquals(timeLaps.getTimeLapsId(),timeLapsFromDb.getTimeLapsId());

    }

    @Test
    public void testUpdate() throws DBException{
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setShortDescription("ShortDescription");
        timeLaps.setLongDescription("LongDescription");
        timeLaps.setCategory("sport");
        timeLaps.setUserId(2L);
        timeLaps.setTimeLapsName("timelaps");
        timeLapsDAO.create(timeLaps);

        timeLaps.setCompleteTime(LocalDateTime.of(2014, 12, 1, 10, 10, 30));
        timeLaps.setShortDescription("UpdatedShortDescription");
        timeLaps.setLongDescription("UpdatedLongDescription");
        timeLaps.setCategory("sport");
        timeLaps.setTimeLapsName("timelaps");
        timeLapsDAO.update(timeLaps);
        TimeLaps timeLapsFromDb = timeLapsDAO.getById(timeLaps.getTimeLapsId());

        assertNotNull(timeLapsFromDb);
        assertEquals(timeLaps.getTimeLapsId(),timeLapsFromDb.getTimeLapsId());
        assertEquals(LocalDateTime.of(2014, Month.DECEMBER, 1, 10, 10, 30),timeLapsFromDb.getCompleteTime());
        assertEquals("UpdatedShortDescription",timeLapsFromDb.getShortDescription());
        assertEquals("UpdatedLongDescription",timeLapsFromDb.getLongDescription());
        assertEquals(new String("sport"),timeLapsFromDb.getCategory());
        assertEquals(new Long(2),timeLapsFromDb.getUserId());
    }

    @Test
    public void testDelete() throws DBException{
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setShortDescription("ShortDescription");
        timeLaps.setLongDescription("LongDescription");
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
    public void getAllTimeLapsTest() throws DBException{
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setShortDescription("ShortDescription");
        timeLaps.setLongDescription("LongDescription");
        timeLaps.setCategory("sport");
        timeLaps.setTimeLapsName("timelaps");
        timeLaps.setUserId(3L);
        timeLapsDAO.create(timeLaps);
        timeLapsDAO.create(timeLaps);
        timeLaps.setUserId(2L);
        timeLapsDAO.create(timeLaps);


        List<TimeLaps> timeLapsList = timeLapsDAO.getAllTimeLaps();

        assertEquals(3,timeLapsList.size());

    }

    @Test
    public void deleteAllTimeLapsTest() throws Exception {
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setShortDescription("ShortDescription");
        timeLaps.setLongDescription("LongDescription");
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
