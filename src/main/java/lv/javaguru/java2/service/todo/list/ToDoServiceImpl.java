package lv.javaguru.java2.service.todo.list;

import lv.javaguru.java2.database.ToDoListDAO;
import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.model.exceptions.ToDoException;
import lv.javaguru.java2.web.form.model.ToDoListModel;
import lv.javaguru.java2.web.form.model.ToDoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
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
            List<ToDo> toDoList = toDoListDAO.getAllToDoListByUserId(userId);
            return toDoList.stream().map(ToDoListModel::new).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ToDoException(e.getMessage());
        }
    }

    @Override
    public void upsertToDo(ToDoModel toDoModel) throws ToDoException {

        try {
            toDoListDAO.createOrUpdate(makeToDoFromWebModel(toDoModel));
        } catch (Exception e ) {
            throw new ToDoException(e.getMessage());
        }
    }

    @Override
    public void removeToDo(ToDoModel toDoModel) throws ToDoException {
        try {
            toDoListDAO.delete(makeToDoFromWebModel(toDoModel));
        } catch (Exception e ) {
            throw new ToDoException(e.getMessage());
        }
    }

    @Override
    public void makeToDoDone(ToDoModel toDoModel) throws ToDoException {
        try {
            ToDo toDoEntity = makeToDoFromWebModel(toDoModel);
            toDoEntity.getToDoTasks().stream().forEach(e -> e.setCompletedGoals(e.getGoalsCount()));
            toDoEntity.setComplete(true);
            toDoListDAO.createOrUpdate(toDoEntity);
        } catch (Exception e ) {
            throw new ToDoException(e.getMessage());
        }

    }


    private ToDo makeToDoFromWebModel(ToDoModel toDoModel) {
        return new ToDo().setId(toDoModel.getId())
                .setDeadLineTime(toDoModel.getDeadLine())
                .setListName(toDoModel.getTodoName())
                .setNotes(toDoModel.getNotes())
                .setToDoTasks(toDoModel.convertTaskModelToEntity());
    }
}
