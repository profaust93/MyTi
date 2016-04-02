package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.database.ToDoDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.ToDo;
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
    private ToDoDAO toDoDAO = new ToDoDAOImpl();

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
        ToDo todo = new ToDo();
        todo.setToDoName("TestName");
        todo.setPriority(1);
        todo.setDone(true);
        todo.setDeadLineTime(LocalDateTime.now());
        toDoDAO.create(todo);

        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setShortDescription("ShortDescription");
        timeLaps.setLongDescription("LongDescription");
        timeLaps.setCategory(1);
        timeLaps.setUserId(todo.getToDoId());
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
        timeLaps.setCategory(2);
        timeLaps.setUserId(2L);
        timeLapsDAO.create(timeLaps);

        timeLaps.setCompleteTime(LocalDateTime.of(2014, 12, 1, 10, 10, 30));
        timeLaps.setShortDescription("UpdatedShortDescription");
        timeLaps.setLongDescription("UpdatedLongDescription");
        timeLaps.setCategory(3);
        timeLapsDAO.update(timeLaps);
        TimeLaps timeLapsFromDb = timeLapsDAO.getById(timeLaps.getTimeLapsId());
        assertNotNull(timeLapsFromDb);
        assertEquals(timeLaps.getTimeLapsId(),timeLapsFromDb.getTimeLapsId());
        assertEquals(LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30),timeLapsFromDb.getCompleteTime());
        assertEquals("UpdatedShortDescription",timeLapsFromDb.getShortDescription());
        assertEquals("UpdatedLongDescription",timeLapsFromDb.getLongDescription());
        assertEquals(new Integer(3),timeLapsFromDb.getCategory());
        assertEquals(new Long(2),timeLapsFromDb.getUserId());
    }

    @Test
    public void testDelete() throws DBException{
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setShortDescription("ShortDescription");
        timeLaps.setLongDescription("LongDescription");
        timeLaps.setCategory(1);
        timeLaps.setUserId(3L);
        timeLapsDAO.create(timeLaps);

        TimeLaps timeLaps1 = new TimeLaps();
        timeLaps1.setCompleteTime(LocalDateTime.now());
        timeLaps1.setShortDescription("ShortDescription");
        timeLaps1.setLongDescription("LongDescription");
        timeLaps1.setCategory(1);
        timeLaps1.setUserId(3L);
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
        timeLaps.setCategory(1);
        timeLaps.setUserId(3L);
        timeLapsDAO.create(timeLaps);
        timeLapsDAO.create(timeLaps);
        timeLapsDAO.create(timeLaps);


        List<TimeLaps> timeLapsList = timeLapsDAO.getAllTimeLaps();

        assertEquals(3,timeLapsList.size());

    }
}
