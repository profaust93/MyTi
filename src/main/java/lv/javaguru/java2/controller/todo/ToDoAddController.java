package lv.javaguru.java2.controller.todo;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.ToDoException;
import lv.javaguru.java2.service.todo.list.ToDoService;
import lv.javaguru.java2.web.form.model.ToDoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by germans.kuzmins on 2016.04.24..
 */
@Component
public class ToDoAddController implements MVCController {

    @Autowired
    ToDoService toDoService;

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        return new MVCModel("/addToDo.jsp",new ToDoModel());
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) throws RedirectException {
        return null;
    }
}
