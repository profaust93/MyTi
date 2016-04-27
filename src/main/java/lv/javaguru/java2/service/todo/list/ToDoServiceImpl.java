package lv.javaguru.java2.service.todo.list;

import lv.javaguru.java2.database.ToDoListDAO;
import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.model.exceptions.ToDoException;
import lv.javaguru.java2.web.form.model.ToDoListModel;
import lv.javaguru.java2.web.form.model.ToDoModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ToDoListModel> getAllToDoForUser(Long userId) throws ToDoException {
        try{
            List<ToDo> todoEntity =  toDoListDAO.getAllToDoListByUserId(userId);
            return todoEntity.stream().map(ToDoListModel::new).collect(Collectors.toList());
        } catch (Exception e ){
            throw new ToDoException(e.getMessage());
        }
    }

    @Override
    public void upsertToDo(ToDoModel toDoModel) throws ToDoException {
        try {
            ToDo toDo = new ToDo().setToDoTasks(toDoModel.convertTaskModelToTask())
                    .setCreateTime(LocalDateTime.now())
                    .setDeadLineTime(toDoModel.getDeadLine())
                    .setId(toDoModel.getId())
                    .setListName(toDoModel.getTodoName())
                    .setNotes(toDoModel.getNotes());
            toDoListDAO.createOrUpdate(toDo);
        } catch (Exception e ){
            throw new ToDoException(e.getMessage());
        }
    }

    @Override
    public void removeToDo(Long toDoId) throws ToDoException {
        try {
            toDoListDAO.delete(new ToDo().setId(toDoId));
        } catch (Exception e) {
            throw new ToDoException(e.getMessage());
        }

    }

    @Override
    public void makeToDoDone() {

    }
}
