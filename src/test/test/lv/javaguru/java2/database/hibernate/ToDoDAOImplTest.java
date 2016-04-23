package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.config.TestSpringConfig;
import lv.javaguru.java2.database.ToDoListDAO;
import lv.javaguru.java2.domain.ToDo;
import org.hibernate.PropertyValueException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import lv.javaguru.java2.domain.ToDoTask;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by german on 4/21/16 for MyTi project.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class)
@Transactional
@Rollback(true)
public class ToDoDAOImplTest {

    private ToDo createdList;

    @Autowired
    ToDoListDAO toDoListDAO;

    @Before
    public void setUp() throws Exception {
        ToDo toDo = new ToDo()
                .setToDoTasks(
                        new HashSet<>(Collections.singletonList(
                                new ToDoTask().setTaskName("One Task")
                        )))
                .setListName("Created ToDo")
                .setCreateTime(LocalDateTime.now())
                .setUserId(2L);
        toDoListDAO.createOrUpdate(toDo);
        createdList = toDo;
    }

    @Test
    public void testCreate() throws Exception {

        ToDo anotherList = new ToDo()
                .setListName("Another List")
                .setCreateTime(LocalDateTime.now());

        toDoListDAO.createOrUpdate(anotherList);
        assertNotNull(toDoListDAO.getById(anotherList.getId()));
        assertEquals(2,toDoListDAO.getAllToDoList().size());
    }

    @Test(expected = PropertyValueException.class)
    public void testCreateWithEmptyTime() throws Exception {
        toDoListDAO.createOrUpdate(new ToDo().setListName("Bad List"));
    }

    @Test
    public void testUpdate() throws Exception {
        createdList.setListName("Renamed List");
        assertEquals("Renamed List",
                toDoListDAO.getById(createdList.getId()).getListName());
    }

    @Test
    public void testGetById() throws Exception {
        ToDo toDo = toDoListDAO.getById(createdList.getId());
        assertEquals(toDo.getListName(),createdList.getListName());
    }

    @Test
    public void testDelete() throws Exception {
        toDoListDAO.delete(createdList);
        assertNull(toDoListDAO.getById(createdList.getId()));
    }

    @Test
    public void testGetAllToDoList() throws Exception {
        toDoListDAO.createOrUpdate(new ToDo()
                .setListName("One List")
                .setCreateTime(LocalDateTime.now()));

        toDoListDAO.createOrUpdate(new ToDo()
                .setListName("Another List")
                .setCreateTime(LocalDateTime.now()));

        assertEquals(3,toDoListDAO.getAllToDoList().size());
    }

    @Test
    public void testGetAllToDoListByUserId() throws Exception {
        toDoListDAO.createOrUpdate(new ToDo()
                .setListName("One List")
                .setCreateTime(LocalDateTime.now())
                .setUserId(3L));
        List<ToDo> toDo = toDoListDAO.getAllToDoListByUserId(2L);
        assertEquals(1, toDo.size());
        assertEquals("Created ToDo", toDo.get(0).getListName());
    }
}