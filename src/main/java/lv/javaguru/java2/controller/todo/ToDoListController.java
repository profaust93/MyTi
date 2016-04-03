package lv.javaguru.java2.controller.todo;

import lv.javaguru.java2.controller.MVCController;
import lv.javaguru.java2.database.jdbc.ToDoDAOImpl;
import lv.javaguru.java2.model.MVCModel;
import lv.javaguru.java2.model.exceptions.RedirectException;
import lv.javaguru.java2.model.exceptions.ToDoException;
import lv.javaguru.java2.model.todo.ToDoModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToDoListController implements MVCController{
    private ToDoModel toDoModel;

    public ToDoListController(ToDoModel toDoModel) {
        this.toDoModel = toDoModel;
    }

    @Override
    public MVCModel processGet(HttpServletRequest req) throws RedirectException {
        HttpSession session = req.getSession();
        toDoModel.setToDoDAO(new ToDoDAOImpl());
        try {
            toDoModel.getAllToDoForUser((String)session.getAttribute("userId"));

        } catch (ToDoException e) {
            return new MVCModel("/error.jsp",e.getMessage());
        }
        return null;
    }

    @Override
    public MVCModel processPost(HttpServletRequest req) {
        return null;
    }
}
