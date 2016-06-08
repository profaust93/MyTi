package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.config.TestSpringConfig;
import lv.javaguru.java2.todo.database.ToDoDAO;
import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import lv.javaguru.java2.todo.form.ToDoFormTask;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class)
@Transactional
@Rollback
@Ignore
public class ToDoServiceTestIntegration {


    @InjectMocks
    @Autowired
    private ToDoService toDoService;

    @Autowired
    ToDoDAO toDoDAO;

    private final static Long USER_ID = 2L;

    @Test
    public void shouldSaveToDoToDataBase() throws Exception {
        ToDoFormModel toDoFormModel = new ToDoFormModel()
                .setToDoName("Test")
                .setDeadLineTime(LocalDateTime.now())
                .setNotes("Notes")
                .setToDoFormTaskList(Arrays.asList(new ToDoFormTask().setName("test"),
                        new ToDoFormTask().setName("test")));

        toDoService.upsertToDo(toDoFormModel, USER_ID);

        ToDo testToDo = toDoDAO.getAllToDoByUserId(USER_ID).stream()
                .filter(e -> e.getName().equals("Test"))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        assertEquals(testToDo.getName(), toDoFormModel.getToDoName());

    }
}