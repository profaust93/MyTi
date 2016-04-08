package lv.javaguru.java2.service.todo;

import static org.junit.Assert.*;

import lv.javaguru.java2.database.ToDoDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.domain.ToDoList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ToDoModelTest {


    @Mock
    private ToDoDAO toDoDAO;

    @InjectMocks
    private ToDoModel toDoModel = new ToDoModelImpl();

    @Test
    public void getToDoById() throws Exception {
        toDoModel.getToDoById(23L);
        verify(toDoDAO).getById(23L);
    }

    @Test
    public void getAllToDoForUser() throws Exception {
        ToDo todo1 = new ToDo();
        todo1.setToDoId(1L);
        todo1.setUserId(1L);

        ToDo todo2 = new ToDo();
        todo2.setToDoId(2L);
        todo2.setUserId(1L);

        ToDo todo3 = new ToDo();
        todo3.setToDoId(3L);
        todo3.setUserId(54L);

        when(toDoDAO.getToDoByUserId(1L)).thenReturn(Arrays.asList(todo1,todo2,todo3));

        List<ToDoList> toDoList = toDoModel.getAllToDoForUser("1");

        verify(toDoDAO).getToDoByUserId(1L);

        assertEquals(2,toDoList.size());
    }

    @Test
    public void testSaveToDo() throws Exception {
        ToDo newToDo = new ToDo();

        toDoModel.saveToDo(newToDo);
        verify(toDoDAO).create(anyObject());

        newToDo.setToDoId(5L);

        toDoModel.saveToDo(newToDo);
        verify(toDoDAO).update(anyObject());

    }

    @Test
    public void deleteToDoList() throws Exception {
        List<String> idList = Arrays.asList("1","2","3");

        toDoModel.deleteToDoList(idList);
        verify(toDoDAO).delete(idList);
    }

    @Test
    public void getNearestToDoForUser() throws Exception {
        fail("not completed implementation");
    }

    @Test
    public void makeTimeLapsFromToDo() throws Exception {
        ToDo todo = new ToDo();
        todo.setToDoName("test");
        todo.setPriority(1);
        todo.setCategory("sport");
        todo.setShortDescription("desc");

        TimeLaps timeLaps = toDoModel.makeTimeLapsFromToDo(todo);

        assertEquals(todo.getToDoName(),timeLaps.getTimeLapsName());
        assertEquals(todo.getCategory().orElse(""),timeLaps.getCategory());
        assertEquals(todo.getShortDescription().orElse(""),timeLaps.getShortDescription());
    }
}