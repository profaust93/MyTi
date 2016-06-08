package lv.javaguru.java2.todo.controller;

import lv.javaguru.java2.security.SecurityService;
import lv.javaguru.java2.todo.exception.ToDoError;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import lv.javaguru.java2.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static lv.javaguru.java2.todo.util.ErrorMessageHandler.handleError;

@Controller
public class ToDoAddControllerImpl implements ToDoAddController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ToDoService toDoService;

    @Override
    @RequestMapping(value = "/addToDo", method = RequestMethod.GET)
    public ModelAndView showAddToDoForm(Model model) {
        ModelAndView modelAndView = new ModelAndView("addToDo");
        if(model.asMap().get("toDo") != null) {
            modelAndView.addObject("toDo",model.asMap().get("toDo"));
        } else {
            modelAndView.addObject("toDo",new ToDoFormModel());
        }
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/addToDo", method = RequestMethod.POST)
    public String addToDo(@ModelAttribute("toDo") ToDoFormModel toDoFormModel, RedirectAttributes redirectAttributes) {
        try {
            toDoService.upsertToDo(toDoFormModel, securityService.getCurrentUserId());
        } catch (ToDoException e) {
            redirectAttributes.addFlashAttribute("toDo", toDoFormModel);
            handleError(redirectAttributes,e.getToDoError());
            return "redirect:/addToDo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("toDo", toDoFormModel);
            redirectAttributes.addFlashAttribute("error", "Service error");
            return "redirect:/addToDo";
        }
        return "redirect:/todoList";
    }
}
