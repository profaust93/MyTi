package lv.javaguru.java2.service.todo.list;

import lv.javaguru.java2.database.ToDoListDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by german on 4/23/16 for MyTi project.
 */
public class ToDoServiceTest {
    @Mock
    ToDoListDAO toDoListDAO;

    @InjectMocks
    private ToDoService toDoService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetToDoList() throws Exception {
        toDoService.getToDoList(2L);
        verify(verify(toDoListDAO.getById(2L)));
    }
}