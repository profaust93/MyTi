package lv.javaguru.java2.todo.database;

import lv.javaguru.java2.config.TestSpringConfig;
import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.domain.ToDoTask;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class)
@Transactional
@Rollback
public class ToDoDAOImplTest {

    private static final Long USER_ID = 2L;
    private static final Long ANOTHER_USER_ID = 3L;

    @Autowired
    ToDoDAO toDoDAO;

    @Test
    public void testCreateToDo() throws Exception {
        List<ToDo> toDoList = createToDo(5, USER_ID);
        for (ToDo todo : toDoList) {
            toDoDAO.createOrUpdate(todo);
        }

        assertEquals(toDoDAO.getAllToDoByUserId(USER_ID).size(),5);
    }

    @Test
    public void testDeleteFromToDoListOneToDoTask() throws Exception {

        ToDo toDo = new ToDo()
                .setComplete(false)
                .setCreateTime(LocalDateTime.now())
                .setName("test")
                .setUserId(USER_ID)
                .setToDoTaskList(Arrays.asList(new ToDoTask("task1"),
                        new ToDoTask("task2"))
                );
        toDoDAO.createOrUpdate(toDo);
        toDo.setToDoTaskList(Collections.singletonList(new ToDoTask("task1")));
        toDoDAO.createOrUpdate(toDo);

        ToDo todo2 = toDoDAO.getToDoById(toDo.getId());

        assertEquals(todo2.getToDoTaskList().size(),1);
        assertEquals(todo2.getToDoTaskList().get(0).getName(),"task1");
    }

    @Test
    public void TestDeleteToDo() throws Exception {
        List<ToDo> toDoList = createToDo(5, USER_ID);
        for (ToDo todo : toDoList) {
            toDoDAO.createOrUpdate(todo);
        }
        toDoDAO.deleteToD(toDoList.get(0).getId());

        List<ToDo> updatedList = toDoDAO.getAllToDoByUserId(USER_ID);
        assertEquals(updatedList.size(),4);

    }

    @Test
    public void testUpdateTodo() throws Exception {
        ToDo toDo = new ToDo().setName("test").setCreateTime(LocalDateTime.now()).setUserId(USER_ID);
        toDoDAO.createOrUpdate(toDo);

        toDoDAO.createOrUpdate(toDo.setName("test Renamed"));

        assertEquals(toDoDAO.getToDoById(toDo.getId()).getName(),toDo.getName());

    }

    @Test
    public void testUpdateToDoTask() throws Exception {
        ToDo toDo = new ToDo().setName("test")
                .setCreateTime(LocalDateTime.now())
                .setUserId(USER_ID)
                .setToDoTaskList(Collections.singletonList(new ToDoTask("task")));
        toDoDAO.createOrUpdate(toDo);

        toDo.getToDoTaskList().get(0).setName("task renamed");
        ToDoTask toDoTask = toDoDAO.getToDoById(toDo.getId()).getToDoTaskList().get(0);

        assertEquals(toDo.getToDoTaskList().get(0).getName(),toDoTask.getName());

    }

    @Test
    public void testToDoForUserId() throws Exception {
        List<ToDo> toDoListForUser = createToDo(5,USER_ID);
        List<ToDo> toDoListForAnotherUser = createToDo(2,ANOTHER_USER_ID);

        for (ToDo todo : toDoListForUser) {
            toDoDAO.createOrUpdate(todo);
        }
        for (ToDo todo : toDoListForAnotherUser) {
            toDoDAO.createOrUpdate(todo);
        }

        assertEquals(toDoDAO.getAllToDoByUserId(ANOTHER_USER_ID).size(),2);
    }

    @Test
    public void testCountTodoForUser() throws Exception {
        List<ToDo> toDoListForUser = createToDo(5,USER_ID);
        List<ToDo> toDoListForAnotherUser = createToDo(2,ANOTHER_USER_ID);

        for (ToDo todo : toDoListForUser) {
            toDoDAO.createOrUpdate(todo);
        }
        for (ToDo todo : toDoListForAnotherUser) {
            toDoDAO.createOrUpdate(todo);
        }

        assertEquals(toDoDAO.getTotalToDoCount(USER_ID).longValue(),5L);

    }

    @Test
    public void testGetSecondTwentyRecords() throws Exception {
        List<ToDo> toDoListForUser = createToDo(100,USER_ID);

        for (ToDo todo : toDoListForUser) {
            toDoDAO.createOrUpdate(todo);
        }

        List<ToDo> secondTwentyRecords = toDoDAO.getToDoForUser(USER_ID,20,20);
        assertEquals(secondTwentyRecords.size(),20);
        assertEquals(secondTwentyRecords.get(0).getName(), toDoListForUser.get(19).getName());
    }

    @Ignore
    @Test
    @Rollback(false)
    public void createToDoTasks() throws Exception {

        List<ToDo> toDoListForUser = createToDo(500,1L);

        for (ToDo todo : toDoListForUser) {
            toDoDAO.createOrUpdate(todo);
        }
    }

    private List<ToDo> createToDo(Integer count, Long userId) {
        return IntStream.range(0,count)
                .mapToObj( i -> new ToDo()
                        .setName("ToDo " + i)
                        .setComplete(false)
                        .setCreateTime(LocalDateTime.now())
                        .setNotes("Notes " + i)
                        .setUserId(userId)
                        .setToDoTaskList(Collections.singletonList(new ToDoTask().setName("test")))
                ).collect(Collectors.toList());
    }
}