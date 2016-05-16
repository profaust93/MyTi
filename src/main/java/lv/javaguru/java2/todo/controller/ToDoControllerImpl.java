package lv.javaguru.java2.todo.controller;

import lv.javaguru.java2.todo.controller.ToDoController;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.service.ToDoListService;
import lv.javaguru.java2.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ToDoControllerImpl implements ToDoController {

    @Autowired
    private ToDoListService toDoListService;

    @Override
    @RequestMapping(value = "/todoList", method = RequestMethod.GET)
    public ModelAndView getToDoListForUser(@RequestParam(value="page", defaultValue="1") Integer page) {

        ModelAndView modelAndView = new ModelAndView("toDoList");
        try {
            modelAndView.addObject("todoList",toDoListService.getListOfToDo(page));
        } catch (ToDoException e) {
            switch (e.getToDoError()) {
                case NO_SUCH_PAGE:
                    modelAndView.addObject("error",e.getToDoError());
                    break;
                case TO_DO_ERROR:
                    modelAndView.addObject("error",e.getToDoError());
                    break;
                default:
                    modelAndView.addObject("error",e.getMessage());
            }
        }
        modelAndView.addObject("page",page);
        return modelAndView;
    }
}
