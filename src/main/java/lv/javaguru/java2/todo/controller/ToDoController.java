package lv.javaguru.java2.todo.controller;

import org.springframework.web.servlet.ModelAndView;

public interface ToDoController {
    ModelAndView getToDoListForUser(Integer Page);
}
