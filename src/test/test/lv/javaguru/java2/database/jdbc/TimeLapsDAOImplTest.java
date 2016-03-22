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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void tearDown() throws Exception {
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
        timeLaps.setToDoId(todo.getToDoId());
        timeLapsDAO.create(timeLaps);

        TimeLaps timeLapsFromDb = timeLapsDAO.getById(timeLaps.getTimeLapsId());
        assertNotNull(timeLapsFromDb);
        assertEquals(timeLaps.getTimeLapsId(),timeLapsFromDb.getTimeLapsId());

    }
}
