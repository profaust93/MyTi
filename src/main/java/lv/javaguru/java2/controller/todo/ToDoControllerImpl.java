package lv.javaguru.java2.controller.todo;

import lv.javaguru.java2.service.todo.ToDoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


public class ToDoControllerImpl implements ToDoController {

    private ToDoService toDoService;

    @Override
    @RequestMapping(value = "/todoList", method = RequestMethod.GET)
    public ModelAndView getToDoListForUser(Integer Page) {

        return null;
    }
}
