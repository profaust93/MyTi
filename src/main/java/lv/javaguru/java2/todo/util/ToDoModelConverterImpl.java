package lv.javaguru.java2.todo.util;

import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.domain.ToDoTask;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import lv.javaguru.java2.todo.form.ToDoFormTask;
import lv.javaguru.java2.todo.form.ToDoListModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ToDo convertFormModelToDomain(ToDoFormModel toDoFormModel) {
        ToDo todo = new ToDo().setName(toDoFormModel.getToDoName())
                .setDeadLineTime(toDoFormModel.getDeadLineTime())
                .setId(toDoFormModel.getToDoId())
                .setNotes(toDoFormModel.getNotes());
        if(toDoFormModel.getToDoFormTaskList() != null) {
            todo.setToDoTaskList(convertFormTaskToDomainTask(toDoFormModel.getToDoFormTaskList()));
        }
        return todo;
    }

    private List<ToDoTask> convertFormTaskToDomainTask(List<ToDoFormTask> toDoFormTasks) {
        return toDoFormTasks.stream()
                .map(e -> new ToDoTask()
                        .setName(e.getName())).collect(Collectors.toList());
    }
}
