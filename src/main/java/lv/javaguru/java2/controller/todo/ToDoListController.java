package lv.javaguru.java2.controller.todo;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.ToDoException;
import lv.javaguru.java2.service.todo.list.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ToDoListController{

    @Autowired
    ToDoService toDoService;

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public ModelAndView processGet(HttpServletRequest req) throws RedirectException {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        try {
            return new ModelAndView("toDoList","data",toDoService.getAllToDoForUser(1L));
        } catch (ToDoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
