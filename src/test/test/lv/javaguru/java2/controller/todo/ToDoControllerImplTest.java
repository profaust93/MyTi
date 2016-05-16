package lv.javaguru.java2.controller.todo;

import lv.javaguru.java2.service.todo.ToDoService;
import lv.javaguru.java2.web.form.model.ToDoListModel;
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

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class ToDoControllerImplTest {

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private ToDoController toDoController = new ToDoControllerImpl();

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(toDoController)
                .setSingleView(new InternalResourceView("toDoList.jsp"))
                .build();
    }

    @Test
    public void shouldGetFirstPageOfToDoIfGetWithoutParameter() throws Exception {
        List toDoList = createToDoList(10);
        when(toDoService.getListOfToDo(1)).thenReturn(toDoList);
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