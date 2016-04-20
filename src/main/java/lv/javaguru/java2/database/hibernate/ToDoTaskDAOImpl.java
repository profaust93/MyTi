package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.ToDoTaskDAO;
import lv.javaguru.java2.domain.ToDoTask;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToDoTaskDAOImpl extends BaseDAO implements ToDoTaskDAO {

    @Override
    public void createOrUpdate(ToDoTask toDoTask) {
        super.saveOrUpdate(toDoTask);
    }

    @Override
    public ToDoTask getTaskById(Long id) {
        return super.get(ToDoTask.class,id);
    }

    @Override
    public void delete(ToDoTask toDoTask) {
        super.delete(toDoTask);
    }

    @Override
    public List<ToDoTask> getAllTasks() {
        return super.getAll(ToDoTask.class);
    }
}
