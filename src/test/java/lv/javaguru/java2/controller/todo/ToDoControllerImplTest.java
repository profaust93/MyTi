package lv.javaguru.java2.controller.todo;

import lv.javaguru.java2.todo.controller.ToDoController;
import lv.javaguru.java2.todo.controller.ToDoControllerImpl;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.service.ToDoListService;
import lv.javaguru.java2.todo.form.ToDoListModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lv.javaguru.java2.todo.exception.ToDoError.*;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class ToDoControllerImplTest {

    @Mock
    private ToDoListService toDoListService;

    @InjectMocks
    private ToDoController toDoController = new ToDoControllerImpl();

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(toDoController)
                .setSingleView(new InternalResourceView("toDoList.jsp"))
                .build();
    }

    @Test
    public void shouldGetFirstPageOfToDoIfGetWithoutParameter() throws Exception {
        mockMvc.perform(get("/todoList"))
                .andExpect(model().attribute("page",1));
        verify(toDoListService).getListOfToDo(1);
    }

    @Test
    public void shouldGetPageOfToDoFromParameter() throws Exception {
        mockMvc.perform(get("/todoList?page=2"))
                .andExpect(model().attribute("page",2));
        verify(toDoListService).getListOfToDo(2);
    }

    @Test
    public void shouldShowIfPageDoesNotExistAndReturnFirstPage() throws Exception {
        when(toDoListService.getListOfToDo(5)).thenThrow(new ToDoException(NO_SUCH_PAGE));
        mockMvc.perform(get("/todoList?page=5"))
                .andExpect(model().attribute("page",5))
                .andExpect(model().attributeExists("error"));
    }

    @Test
    public void shouldShowErrorIfServiceThrowException() throws Exception {
        when(toDoListService.getListOfToDo(anyInt())).thenThrow(new ToDoException());
        mockMvc.perform(get("/todoList"))
                .andExpect(model().attributeExists("error"));
    }

    @Test
    public void shouldShowToDoListReturnedFromService() throws Exception {
        List<ToDoListModel> toDoList = createToDoList(10);
        when(toDoListService.getListOfToDo(1)).thenReturn(toDoList);
        mockMvc.perform(get("/todoList"))
                .andExpect(view().name("toDoList"))
                .andExpect(model().attributeExists("todoList"))
                .andExpect(model().attribute("todoList",hasItems(toDoList.toArray())));
    }

    private List<ToDoListModel> createToDoList(Integer count) {
        return IntStream.rangeClosed(1,count).boxed().map(i -> new ToDoListModel().setComplete(false)
                .setDeadLineTime(LocalDateTime.now())
                .setName("name:" + i)).collect(Collectors.toList());
    }
}