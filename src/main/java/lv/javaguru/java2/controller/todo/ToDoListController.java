package lv.javaguru.java2.controller.todo;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.dto.UserDTO;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.ToDoException;
import lv.javaguru.java2.service.todo.list.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Component
public class ToDoListController  implements MVCController{

    @Autowired
    ToDoService toDoService;

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        try {
            return new MVCModel("/toDoList.jsp",toDoService.getAllToDoForUser(userDTO.getUserId()));
        } catch (ToDoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) throws RedirectException {
        return null;
    }
}
