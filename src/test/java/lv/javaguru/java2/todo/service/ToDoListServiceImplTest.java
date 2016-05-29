package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.todo.database.ToDoDAO;
import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.domain.ToDoTask;
import lv.javaguru.java2.todo.exception.ToDoError;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoListModel;
import lv.javaguru.java2.todo.util.ToDoModelConverter;
import lv.javaguru.java2.todo.util.ToDoModelConverterImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ToDoListServiceImplTest {

    private final static Integer RECORDS_PER_PAGE = 20;
    private final static Integer NOT_EXISTING_PAGE = 10;
    private final static Long USER_ID = 1L;
    private final static Integer FIRST_PAGE = 1;
    private final static Integer NO_OFFSET = 0;


    @Mock
    private ToDoDAO toDoDAO;

    @Spy
    private ToDoModelConverter toDoModelConverter = new ToDoModelConverterImpl();

    @InjectMocks
    private ToDoListService  toDoListService = new ToDoListServiceImpl();

    @Test
    public void shouldReturnEmptyListIfPageDoesNotExist() throws Exception {
        when(toDoDAO.getToDoForUser(USER_ID,RECORDS_PER_PAGE,200)).thenReturn(Collections.emptyList());

        List<ToDoListModel> toDoListModels = toDoListService.getListOfToDoForUser(NOT_EXISTING_PAGE,USER_ID);
        assertEquals(0,toDoListModels.size());

    }

    @Test
    public void shouldThrowExceptionIfDbThrowException() throws Exception {
        when(toDoDAO.getToDoForUser(USER_ID,RECORDS_PER_PAGE,NO_OFFSET)).thenThrow(new DBException("error"));
        try {
            toDoListService.getListOfToDoForUser(FIRST_PAGE,USER_ID);
            fail("No Exception thrown");
        } catch (ToDoException e ){
            assertEquals(ToDoError.TO_DO_ERROR,e.getToDoError());
        }
    }


    @Test
    public void shouldCallDaoWithNoOffsetIfGetFirstPage() throws Exception {
        when(toDoDAO.getToDoForUser(USER_ID,RECORDS_PER_PAGE,NO_OFFSET)).thenReturn(createToDo(10,USER_ID));

        toDoListService.getListOfToDoForUser(FIRST_PAGE,USER_ID);

        verify(toDoDAO).getToDoForUser(USER_ID,RECORDS_PER_PAGE,NO_OFFSET);
    }

    @Test
    public void shouldCallOffsetDependsOnPage() throws Exception {
        when(toDoDAO.getToDoForUser(any(),any(),any())).thenReturn(createToDo(10,USER_ID));

        toDoListService.getListOfToDoForUser(2,USER_ID);
        verify(toDoDAO).getToDoForUser(USER_ID,RECORDS_PER_PAGE,20);

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