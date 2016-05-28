package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.todo.database.ToDoDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ToDoListServiceImplPageTest {

    @Mock
    private ToDoDAO toDoDAO;

    @InjectMocks
    private ToDoListService  toDoListService = new ToDoListServiceImpl();

    private final static Long USER_ID = 1L;

    @Test
    public void shouldReturnZeroIfDaoReturnZero() throws Exception {
        when(toDoDAO.getTotalToDoCount(USER_ID)).thenReturn(0L);
        Integer pageCount = toDoListService.getNumberOfPage(USER_ID);
        assertEquals(0, pageCount.intValue());
    }

    @Test
    public void shouldReturn4IfDAOReturnBetween60And80() throws Exception {
        when(toDoDAO.getTotalToDoCount(USER_ID)).thenReturn(65L);
        Integer pageCount = toDoListService.getNumberOfPage(USER_ID);
        assertEquals(4, pageCount.intValue());

    }
}