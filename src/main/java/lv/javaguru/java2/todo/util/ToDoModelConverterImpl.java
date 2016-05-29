package lv.javaguru.java2.todo.util;

import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.domain.ToDoTask;
import lv.javaguru.java2.todo.form.ToDoListModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToDoModelConverterImpl implements ToDoModelConverter {
    @Override
    public ToDoListModel convertDomainToListModel(ToDo toDoDomain) {
        ToDoListModel toDoListModel = new ToDoListModel();
        List<ToDoTask> toDoTasks = toDoDomain.getToDoTaskList();
        toDoListModel.setComplete(toDoDomain.getComplete())
                .setName(toDoDomain.getName())
                .setDeadLineTime(toDoDomain.getDeadLineTime())
                .setId(toDoDomain.getId());
        toDoListModel.setTaskCount(toDoTasks != null ? toDoTasks.size() : 0);
        return toDoListModel;
    }
}
