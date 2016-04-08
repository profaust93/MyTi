package lv.javaguru.java2.controller.todo;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.jdbc.ToDoDAOImpl;
import lv.javaguru.java2.domain.ToDoList;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.ToDoException;
import lv.javaguru.java2.service.todo.ToDoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Component
public class ToDoListController implements MVCController{

    @Autowired
    private ToDoModel toDoModel;

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        HttpSession session = req.getSession();
        try {
            List<ToDoList> todoList = toDoModel.getAllToDoForUser((String)session.getAttribute("userId"));
            return new MVCModel("/toDoList.jsp",todoList);
        } catch (ToDoException e) {
            return new MVCModel("/error.jsp",e.getMessage());
        }
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
