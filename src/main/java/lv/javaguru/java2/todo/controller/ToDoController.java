package lv.javaguru.java2.todo.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ToDoController {
    ModelAndView getToDoListForUser(Integer Page, RedirectAttributes redirectAttributes);
}
