package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ToDoDAO;
import lv.javaguru.java2.domain.ToDo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class ToDoDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private ToDoDAO toDoDAO = new ToDoDAOImpl();


    @Before
    public void setUp() throws Exception {
        databaseCleaner.cleanDatabase();
    }

    @After
    public void tearDown() throws Exception {
        databaseCleaner.cleanDatabase();

    }

    @Test
    public void testCreate() throws Exception {
        ToDo todo = new ToDo();
        todo.setToDoName("TestName");
        todo.setPriority(1);
        todo.setDone(false);
        todo.setDeadLineTime(LocalDateTime.now());
        todo.setUserId(10L);
        toDoDAO.create(todo);

        ToDo toDoFromDb = toDoDAO.getById(todo.getToDoId());
        assertNotNull(toDoFromDb);
        assertEquals(todo.getToDoId(),toDoFromDb.getToDoId());
        assertEquals(todo.getToDoName(),toDoFromDb.getToDoName());

    }

    @Test(expected = DBException.class)
    public void testCreateWithException() throws Exception {
        ToDo todo = new ToDo();
        todo.setDeadLineTime(LocalDateTime.now());
        toDoDAO.create(todo);
    }


    @Test
    public void testUpdate() throws Exception {
        ToDo todo = new ToDo();
        todo.setToDoName("TestName");
        todo.setPriority(1);
        todo.setDone(false);
        todo.setToDoTime(LocalDateTime.now());
        todo.setDeadLineTime(LocalDateTime.now());
        todo.setCategoryId(1);
        todo.setShortDescription("Before");
        todo.setLongDescription("Long Before");
        todo.setUserId(10L);
        toDoDAO.create(todo);

        todo.setToDoName("RenamedToDo");
        todo.setPriority(2);
        todo.setDone(true);
        todo.setToDoTime(LocalDateTime.of(2016,1,2,4,5));
        todo.setDeadLineTime(LocalDateTime.of(2017,1,2,4,5));
        todo.setCategoryId(3);
        todo.setShortDescription("After");
        todo.setLongDescription("Long After");
        todo.setUserId(10L);
        toDoDAO.update(todo);

        assertEquals("RenamedToDo",todo.getToDoName());
        assertEquals(new Integer(2),todo.getPriority());
        assertEquals(true,todo.getDone());
        assertEquals(LocalDateTime.of(2016,1,2,4,5),todo.getToDoTime());
        assertEquals(LocalDateTime.of(2017,1,2,4,5),todo.getDeadLineTime());
        assertEquals(new Integer(3),todo.getCategoryId().orElse(0));
        assertEquals("After",todo.getShortDescription().orElse(""));
        assertEquals("Long After",todo.getLongDescription().orElse(""));

    }

    @Test
    public void testDelete() throws Exception {
        ToDo todo = new ToDo();
        todo.setToDoName("ToDoToDelete");
        todo.setPriority(1);
        todo.setDone(false);
        todo.setDeadLineTime(LocalDateTime.now());
        todo.setUserId(10L);
        toDoDAO.create(todo);

        ToDo todo2 = new ToDo();
        todo2.setToDoName("ToDoToStay");
        todo2.setPriority(1);
        todo2.setDone(false);
        todo2.setDeadLineTime(LocalDateTime.now());
        todo2.setUserId(12L);
        toDoDAO.create(todo2);

        Long toDoId = todo.getToDoId();

        toDoDAO.delete(todo);

        assertNull(toDoDAO.getById(toDoId));
        assertNotNull(toDoDAO.getById(todo2.getToDoId()));

    }

    @Test
    public void testGetAllToDo() throws Exception {
        ToDo todo = new ToDo();
        todo.setToDoName("TestName");
        todo.setPriority(1);
        todo.setDone(false);
        todo.setDeadLineTime(LocalDateTime.now());
        todo.setUserId(10L);

        toDoDAO.create(todo);
        toDoDAO.create(todo);
        toDoDAO.create(todo);

        List<ToDo> toDoList = toDoDAO.getAllToDo();
        assertEquals(3,toDoList.size());

    }

    @Test
    public void testDeleteTodoList() throws Exception {
        ToDo todo = new ToDo();
        todo.setToDoName("TestName");
        todo.setPriority(1);
        todo.setDone(false);
        todo.setDeadLineTime(LocalDateTime.now());
        todo.setUserId(10L);
        toDoDAO.create(todo);
        toDoDAO.create(todo);
        toDoDAO.create(todo);

        List<ToDo> toDoList = toDoDAO.getAllToDo();
        toDoDAO.delete(toDoList);
        assertEquals(0,toDoDAO.getAllToDo().size());

    }

    @Test
    public void testDeleteListWithException() throws Exception {
        ToDo todo = new ToDo();
        todo.setToDoName("TestName");
        todo.setPriority(1);
        todo.setDone(false);
        todo.setUserId(10L);
        toDoDAO.create(todo);
        toDoDAO.create(todo);

        List<ToDo> toDoList = toDoDAO.getAllToDo();
        toDoList.get(1).setToDoId(null);
        try {
            toDoDAO.delete(toDoList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(2,toDoDAO.getAllToDo().size());

    }

    @Test
    public void testGetTodoByUserId() throws Exception {
        ToDo todo = new ToDo();
        todo.setToDoName("TestName");
        todo.setPriority(1);
        todo.setDone(false);
        todo.setUserId(10L);
        toDoDAO.create(todo);
        toDoDAO.create(todo);

        todo.setUserId(2L);
        toDoDAO.create(todo);

        List<ToDo> list = toDoDAO.getToDoByUserId(10L);

        assertEquals(list.size(),2);
        assertEquals(list.stream().filter(e->e.getUserId().equals(10L)).count(),2);

    }
}