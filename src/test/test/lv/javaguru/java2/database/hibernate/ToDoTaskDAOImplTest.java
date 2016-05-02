package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.config.TestSpringConfig;
import lv.javaguru.java2.database.ToDoTaskDAO;
import lv.javaguru.java2.domain.ToDoTask;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class)
@Transactional
@Rollback
public class ToDoTaskDAOImplTest {

    @Autowired
    ToDoTaskDAO toDoTaskDAO;

    private ToDoTask createdTask;


    @Before
    public void setUp() throws Exception {
        ToDoTask createdTask = new ToDoTask().setTaskName("Created task");
        toDoTaskDAO.createOrUpdate(createdTask);
        this.createdTask = createdTask;
    }

    @Test
    public void testCreate() throws Exception {
        ToDoTask toDoTask = new ToDoTask()
                .setTaskName("test");

        toDoTaskDAO.createOrUpdate(toDoTask);
        assertNotNull(toDoTask.getId());
    }

    @Test
    public void testUpdate() throws Exception {
        createdTask.setTaskName("Renamed Task");
        assertEquals("Renamed Task",
                toDoTaskDAO.getTaskById(createdTask.getId()).getTaskName());

    }

    @Test
    public void testGetTaskById() throws Exception {
        assertEquals("Created task",
                toDoTaskDAO.getTaskById(createdTask.getId()).getTaskName());
    }

    @Test
    public void testDelete() throws Exception {
        toDoTaskDAO.delete(createdTask);

        ToDoTask deletedTask = toDoTaskDAO.getTaskById(createdTask.getId());

        assertNull(deletedTask);
    }

    @Test
    public void testGetAllTasks() throws Exception {
        toDoTaskDAO.createOrUpdate(new ToDoTask().setTaskName("New Task"));
        List<ToDoTask> allTask = toDoTaskDAO.getAllTasks();
        assertEquals(2,allTask.size());
        assertEquals(1,allTask.stream().filter(e->"New Task".equals(e.getTaskName())).count());
    }

}