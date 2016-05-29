package lv.javaguru.java2.todo.controller;

import lv.javaguru.java2.security.SecurityService;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class ToDoListControllerImplTest {

    @Mock
    private ToDoListService toDoListService;

    @Mock
    private SecurityService securityService;

    @InjectMocks
    private ToDoListController toDoListController = new ToDoListControllerImpl();

    private MockMvc mockMvc;

    private static Long USER_ID = 1L;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(toDoListController)
                .setSingleView(new InternalResourceView("toDoList.jsp"))
                .build();

        when(securityService.getCurrentUserId()).thenReturn(USER_ID);
    }

    @Test
    public void shouldGetFirstPageOfToDoIfGetWithoutParameter() throws Exception {
        mockMvc.perform(get("/todoList"))
                .andExpect(model().attribute("page",1));
        verify(toDoListService).getListOfToDoForUser(1,USER_ID);
    }

    @Test
    public void shouldGetPageOfToDoFromParameter() throws Exception {
        mockMvc.perform(get("/todoList?page=2"))
                .andExpect(model().attribute("page",2));
        verify(toDoListService).getListOfToDoForUser(2,USER_ID);
    }

    @Test
    public void shouldShowEmptyContentErrorIfPageDoesNotExist() throws Exception {
        when(toDoListService.getListOfToDoForUser(1,USER_ID)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/todoList"))
                .andExpect(view().name("toDoList"))
                .andExpect(model().attributeExists("todoList"))
                .andExpect(model().attribute("todoList",hasItems(Collections.emptyList().toArray())));
    }

    @Test
    public void shouldShowErrorIfServiceThrowException() throws Exception {
        when(toDoListService.getListOfToDoForUser(anyInt(),anyLong())).thenThrow(new ToDoException());
        mockMvc.perform(get("/todoList"))
                .andExpect(model().attributeExists("error"));
    }

    @Test
    public void shouldShowToDoListReturnedFromService() throws Exception {
        List<ToDoListModel> toDoList = createToDoList(10);
        when(toDoListService.getListOfToDoForUser(1,USER_ID)).thenReturn(toDoList);
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