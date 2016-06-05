package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.todo.database.ToDoDAO;
import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.exception.ToDoError;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import lv.javaguru.java2.todo.util.ToDoModelConverter;
import lv.javaguru.java2.todo.validator.ToDoValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ToDoServiceImplTest {

    @Mock
    private ToDoValidator toDoValidator;

    @Mock
    private ToDoDAO toDoDAO;

    @Mock
    private ToDoModelConverter toDoModelConverter;

    @InjectMocks
    private ToDoService toDoService = new ToDoServiceImpl();

    private final static Long USER_ID  = 2L;

    @Test
    public void shouldValidateToDoFormModel() throws Exception {
        when(toDoModelConverter.convertFormModelToDomain(any())).thenReturn(new ToDo());
        ToDoFormModel toDoFormModel = new ToDoFormModel();
        toDoService.upsertToDo(toDoFormModel, USER_ID);
        verify(toDoValidator).validateToDo(toDoFormModel);

    }

    @Test
    public void shouldThrowExceptionIfValidationFails() throws Exception {
        doThrow(new ToDoException(ToDoError.VALIDATION_FAILS)).when(toDoValidator).validateToDo(any());
        try{
            toDoService.upsertToDo(new ToDoFormModel(), USER_ID);
            fail("No Exception thrown");
        } catch (ToDoException e ){
            assertEquals(ToDoError.VALIDATION_FAILS, e.getToDoError());
        }
    }

    @Test
    public void shouldSaveFormModelIfValidationPass() throws Exception {
        ToDo toDo = new ToDo();
        when(toDoModelConverter.convertFormModelToDomain(any())).thenReturn(toDo);
        toDoService.upsertToDo(new ToDoFormModel(), USER_ID);
        verify(toDoDAO).createOrUpdate(toDo);
    }

    @Test
    public void shouldThrowExceptionIfDbError() throws Exception {
        when(toDoModelConverter.convertFormModelToDomain(any())).thenReturn(new ToDo());
        doThrow(new DBException("Db Error")).when(toDoDAO).createOrUpdate(any());
        try{
            toDoService.upsertToDo(new ToDoFormModel(), USER_ID);
            fail("No Exception thrown");
        } catch (ToDoException e ){
            assertEquals(ToDoError.DB_ERROR,e.getToDoError());
        }
    }
}