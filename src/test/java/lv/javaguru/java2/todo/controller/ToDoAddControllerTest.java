package lv.javaguru.java2.todo.controller;

import lv.javaguru.java2.security.SecurityService;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import lv.javaguru.java2.todo.form.ToDoTask;
import lv.javaguru.java2.todo.service.ToDoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.servlet.view.InternalResourceView;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(MockitoJUnitRunner.class)
public class ToDoAddControllerTest {


    @Mock
    private SecurityService securityService;

    @Mock
    ToDoService toDoService;

    @InjectMocks
    private ToDoAddController toDoAddController = new ToDoAddControllerImpl();

    private MockMvc mockMvc;

    private static Long USER_ID = 1L;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(toDoAddController)
                .setSingleView(new InternalResourceView("addToDo.jsp"))
                .build();
        when(securityService.getCurrentUserId()).thenReturn(USER_ID);
    }

    @Test
    public void shouldReturnViewAndEmptyModel() throws Exception {
        mockMvc.perform(get("/addToDo"))
                .andExpect(model().attributeExists("toDo"))
                .andExpect(model().attribute("toDo",hasProperty("toDoId",nullValue())))
                .andExpect(model().attribute("toDo",hasProperty("toDoName",nullValue())));
    }

    @Test
    public void shouldUnEmptyModelIfRedirectedWithError() throws Exception {
        ToDoFormModel toDoFormModel = new ToDoFormModel().setToDoName("test");
        mockMvc.perform(get("/addToDo")
                .flashAttr("error","Error Msg")
                .flashAttr("toDo",toDoFormModel))
                .andExpect(model().attributeExists("toDo"))
                .andExpect(model().attribute("toDo",toDoFormModel))
                .andExpect(model().attribute("toDo",hasProperty("toDoName",is("test"))))
                .andExpect(model().attributeExists("error"));
    }

    @Test
    public void shouldRedirectToListFormAfterSuccessfulAdd() throws Exception {
        ToDoFormModel toDoFormModel = new ToDoFormModel()
                .setDeadLineTime(LocalDateTime.now())
                .setToDoName("testToDo")
                .setNotes("testNotes")
                .setToDoTaskList(Arrays.asList(new ToDoTask().setName("task1"), new ToDoTask().setName("task2")));

        mockMvc.perform(makeRequestFormToFormModel(toDoFormModel))
                .andExpect(view().name("redirect:/todoList"));
        ArgumentCaptor<ToDoFormModel> argument = ArgumentCaptor.forClass(ToDoFormModel.class);
        verify(toDoService).upsertToDo(argument.capture());
        assertEquals(toDoFormModel.getToDoName(),argument.getValue().getToDoName());
    }

    @Test
    public void shouldRedirectToAddFormWithPostedModelIfExceptionThrown() throws Exception {

    }

    private RequestBuilder makeRequestFormToFormModel(ToDoFormModel toDoFormModel) {

        return post("/addToDo")
                .param("toDoName", toDoFormModel.getToDoName())
                .param("notes", toDoFormModel.getNotes())
                .param("deadLineTime",LocalDateTime.now().toString())
                .param("toDoTaskList[0].name", "task1");
    }


}