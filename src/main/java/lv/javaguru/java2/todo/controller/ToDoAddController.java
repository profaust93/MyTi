package lv.javaguru.java2.todo.controller;

import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ToDoAddController {

    ModelAndView showAddToDoForm(Model model);

    String addToDo(ToDoFormModel toDoFormModel, RedirectAttributes redirectAttributes);

}
