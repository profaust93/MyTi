package lv.javaguru.java2.todo.controller;

import lv.javaguru.java2.todo.form.ToDoFormModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by german on 6/7/16 for MyTi project.
 */
public interface ToDoEditController {

    ModelAndView showEditToDoForm(Long todoId, Model model);

    String editToDo(Long id , ToDoFormModel toDoFormModel, RedirectAttributes redirectAttributes);

    String removeToDo(Long id , ToDoFormModel toDoFormModel, RedirectAttributes redirectAttributes);
}