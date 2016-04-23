package lv.javaguru.java2.service.todo.list;

import lv.javaguru.java2.database.ToDoListDAO;
import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.model.exceptions.ToDoException;
import lv.javaguru.java2.web.form.model.ToDoListModel;
import lv.javaguru.java2.web.form.model.ToDoModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by german on 4/23/16 for MyTi project.
 */
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    ToDoListDAO toDoListDAO;

    @Override
    public ToDoModel getToDoList(Long id) throws ToDoException {
        try{
            ToDo todoEntity =  toDoListDAO.getById(id);
            return new ToDoModel(todoEntity);
        } catch (Exception e ){
            throw new ToDoException(e.getMessage());
        }

    }

    @Override
    public ToDoListModel getAllToDoForUser(Long userId) {
        return null;
    }

    @Override
    public void upsertToDo(ToDoModel toDoModel) {

    }

    @Override
    public void removeToDo() {

    }

    @Override
    public void makeToDoDone() {

    }
}