package lv.javaguru.java2.todo.controller;

import lv.javaguru.java2.security.SecurityService;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoListModel;
import lv.javaguru.java2.todo.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ToDoListControllerImpl implements ToDoListController {

    @Autowired
    private ToDoListService toDoListService;

    @Autowired
    SecurityService securityService;

    @Override
    @RequestMapping(value = "/todoList", method = RequestMethod.GET)
    public ModelAndView getToDoListForUser(@RequestParam(value="page", defaultValue="1") Integer page,
                                           RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView("toDoList");
        List<ToDoListModel> toDoListModels = new ArrayList<>();
        Integer totalPageCount = 0;
        try {
            Long userId  = securityService.getCurrentUserId();
            toDoListModels = toDoListService.getListOfToDoForUser(page, userId);
            totalPageCount = toDoListService.getNumberOfPage(userId);
        } catch (ToDoException e ) {
            handleException(e, modelAndView);
        } catch (Exception e ) {
            modelAndView.addObject("error", e.getMessage());
        }
        modelAndView.addObject("pageCount",totalPageCount);
        modelAndView.addObject("todoList",toDoListModels);
        modelAndView.addObject("page",page);
        return modelAndView;
    }

    private void handleException(ToDoException todoException, ModelAndView modelAndView) {
        switch (todoException.getToDoError()) {
            case TO_DO_ERROR:
                modelAndView.addObject("error","Service Error");
                break;
            case NO_SUCH_PAGE:
                modelAndView.addObject("error","Service Error");
                break;
            default:
                modelAndView.addObject("error", todoException.getMessage());
                break;
        }
    }
}
