package lv.javaguru.java2.controller.todo;

import org.springframework.web.servlet.ModelAndView;

public interface ToDoController {
    ModelAndView getToDoListForUser(Integer Page);
}
