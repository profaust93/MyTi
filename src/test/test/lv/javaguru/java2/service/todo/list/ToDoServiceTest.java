package lv.javaguru.java2.service.todo.list;

import lv.javaguru.java2.database.ToDoListDAO;
import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.web.form.model.ToDoModel;
import lv.javaguru.java2.web.form.model.ToDoTaskModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by german on 4/23/16 for MyTi project.
 */
public class ToDoServiceTest {
    private static final Long USER_ID = 2L;
    private static final Long TODO_ID = 50L;
    private static final String TODO_NAME = "Test ToDo";

    @Mock
    ToDoListDAO toDoListDAO;

    @InjectMocks
    private ToDoService toDoService = new ToDoServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetToDoList() throws Exception {
        when(toDoListDAO.getById(TODO_ID)).thenReturn(new ToDo().setId(TODO_ID).setListName(TODO_NAME));
        ToDoModel toDoModel =  toDoService.getToDoList(TODO_ID);
        verify(toDoListDAO).getById(TODO_ID);
        assertEquals(TODO_ID,toDoModel.getId());
        assertEquals(TODO_NAME,toDoModel.getTodoName());
    }

    @Test
    public void testGetToDoListForUser() throws Exception {
        toDoService.getAllToDoForUser(USER_ID);
        verify(toDoListDAO).getAllToDoListByUserId(USER_ID);
    }

    @Test
    public void testSaveOrUpdateToDoModel() throws Exception {
        ToDoModel toDoModel = new ToDoModel()
                .setTodoName("testToDO")
                .setDeadLine(LocalDateTime.parse("2016-12-03T10:15:30"))
                .setNotes("someNotes")
                .setToDoTaskModels(Collections.singletonList(new ToDoTaskModel().setTaskName("test")));
        toDoService.upsertToDo(toDoModel);
        verify(toDoListDAO).createOrUpdate(anyObject());
    }

    @Test
    public void testDeleteToDo() throws Exception {
        toDoService.removeToDo(TODO_ID);
        verify(toDoListDAO).delete(anyObject());
    }

}