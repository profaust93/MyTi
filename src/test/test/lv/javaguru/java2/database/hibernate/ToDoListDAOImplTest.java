package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.config.SpringConfig;
import lv.javaguru.java2.database.ToDoListDAO;
import lv.javaguru.java2.domain.ToDoList;
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
@ContextConfiguration(classes = SpringConfig.class)
@Transactional
@Rollback(true)
public class ToDoListDAOImplTest {

    private ToDoList createdList;

    @Autowired
    ToDoListDAO toDoListDAO;

    @Before
    public void setUp() throws Exception {
        ToDoList toDoList = new ToDoList()
                .setToDoTasks(
                        new HashSet<>(Collections.singletonList(
                                new ToDoTask().setTaskName("One Task")
                        )))
                .setListName("Created ToDoList")
                .setCreateTime(LocalDateTime.now())
                .setUserId(2L);
        toDoListDAO.createOrUpdate(toDoList);
        createdList = toDoList;
    }

    @Test
    public void testCreate() throws Exception {

        ToDoList anotherList = new ToDoList()
                .setListName("Another List")
                .setCreateTime(LocalDateTime.now());

        toDoListDAO.createOrUpdate(anotherList);
        assertNotNull(toDoListDAO.getById(anotherList.getId()));
        assertEquals(2,toDoListDAO.getAllToDoList().size());
    }

    @Test(expected = PropertyValueException.class)
    public void testCreateWithEmptyTime() throws Exception {
        toDoListDAO.createOrUpdate(new ToDoList().setListName("Bad List"));
    }

    @Test
    public void testUpdate() throws Exception {
        createdList.setListName("Renamed List");
        assertEquals("Renamed List",
                toDoListDAO.getById(createdList.getId()).getListName());
    }

    @Test
    public void testGetById() throws Exception {
        ToDoList toDoList = toDoListDAO.getById(createdList.getId());
        assertEquals(toDoList.getListName(),createdList.getListName());
    }

    @Test
    public void testDelete() throws Exception {
        toDoListDAO.delete(createdList);
        assertNull(toDoListDAO.getById(createdList.getId()));
    }

    @Test
    public void testGetAllToDoList() throws Exception {
        toDoListDAO.createOrUpdate(new ToDoList()
                .setListName("One List")
                .setCreateTime(LocalDateTime.now()));

        toDoListDAO.createOrUpdate(new ToDoList()
                .setListName("Another List")
                .setCreateTime(LocalDateTime.now()));

        assertEquals(3,toDoListDAO.getAllToDoList().size());
    }

    @Test
    public void testGetAllToDoListByUserId() throws Exception {
        toDoListDAO.createOrUpdate(new ToDoList()
                .setListName("One List")
                .setCreateTime(LocalDateTime.now())
                .setUserId(3L));
        List<ToDoList> toDoList = toDoListDAO.getAllToDoListByUserId(2L);
        assertEquals(1, toDoList.size());
        assertEquals("Created ToDoList",toDoList.get(0).getListName());
    }
}