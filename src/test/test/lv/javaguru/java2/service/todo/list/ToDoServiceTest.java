package lv.javaguru.java2.service.todo.list;

import lv.javaguru.java2.database.ToDoListDAO;
import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.web.form.model.ToDoModel;
import lv.javaguru.java2.web.form.model.ToDoTaskModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ToDoServiceTest {
    @Mock
    ToDoListDAO toDoListDAO;

    @InjectMocks
    private ToDoService toDoService = new ToDoServiceImpl();

    @Before
    public void setUp() throws Exception {
        when(toDoListDAO.getById(2L)).thenReturn(new ToDo());
    }

    @Test
    public void testGetToDoList() throws Exception {
        toDoService.getToDoList(2L);
        verify(toDoListDAO).getById(2L);
    }

    @Test
    public void testGetAllToDo() throws Exception {
        toDoService.getAllToDoForUser(2L);
        verify(toDoListDAO).getAllToDoListByUserId(2L);

    }

    @Test
    public void testUpsertToDo() throws Exception {
        toDoService.upsertToDo(new ToDoModel().setId(1L));
        verify(toDoListDAO).createOrUpdate(anyObject());
    }

    @Test
    public void testRemoveToDO() throws Exception {
        toDoService.removeToDo(new ToDoModel().setId(2L));
        verify(toDoListDAO).delete(anyObject());

    }

    @Test
    public void testMakeToDoDone() throws Exception {
        toDoService.makeToDoDone(new ToDoModel().setToDoTaskModels(new ArrayList<>(
               )));
        ArgumentCaptor<ToDo> toDoArgumentCaptor = ArgumentCaptor.forClass(ToDo.class);
        verify(toDoListDAO).createOrUpdate(toDoArgumentCaptor.capture());
        ToDoModel toDoModel = new ToDoModel(toDoArgumentCaptor.getValue());
        assertEquals(true,toDoModel.getComeplete());
    }
}