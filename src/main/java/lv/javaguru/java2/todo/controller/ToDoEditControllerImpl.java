package lv.javaguru.java2.todo.controller;

import lv.javaguru.java2.security.SecurityService;
import lv.javaguru.java2.todo.exception.ToDoError;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import lv.javaguru.java2.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static lv.javaguru.java2.todo.util.ErrorMessageHandler.handleError;

@Controller
public class ToDoEditControllerImpl implements ToDoEditController {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private SecurityService securityService;

    @Override
    @RequestMapping(value = "/toDo/{id}/edit", method = RequestMethod.GET)
    public ModelAndView showEditToDoForm(@PathVariable("id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView("addToDo");
        if(model.asMap().get("toDo") != null) {
            modelAndView.addObject("toDo",model.asMap().get("toDo"));
        } else {
            try {
                ToDoFormModel formModel = toDoService.getToDoById(id);
                modelAndView.addObject("toDo",formModel);
            } catch (ToDoException e) {
                e.printStackTrace();
            }
        }
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/toDo/{id}/edit", method = RequestMethod.POST)
    public String editToDo(@PathVariable("id") Long id, ToDoFormModel toDoFormModel, RedirectAttributes redirectAttributes) {
        try {
            toDoService.upsertToDo(toDoFormModel.setToDoId(id), securityService.getCurrentUserId());
        } catch (ToDoException e) {
            redirectAttributes.addFlashAttribute("toDo", toDoFormModel);
            handleError(redirectAttributes,e.getToDoError());
            return "redirect:/toDo/"+id+"/edit";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("toDo", toDoFormModel);
            redirectAttributes.addFlashAttribute("error", "Service error");
            return "redirect:/toDo/"+id+"/edit";
        }
        return "redirect:/todoList";
    }

    @Override
    @RequestMapping(value = "/toDo/{id}/delete", method = RequestMethod.GET)
    public String removeToDo(@PathVariable("id") Long id, ToDoFormModel toDoFormModel, RedirectAttributes redirectAttributes) {
        try {
            toDoService.deleteToDo(id, securityService.getCurrentUserId());
        } catch (ToDoException e) {
            handleError(redirectAttributes,e.getToDoError());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Service error");
        }
        return "redirect:/todoList";
    }
}
