package lv.javaguru.java2.todo.util;

import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.domain.ToDoTask;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import lv.javaguru.java2.todo.form.ToDoListModel;
import org.springframework.stereotype.Component;

import java.util.Collections;
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
        return new ToDo().setName(toDoFormModel.getToDoName())
                .setDeadLineTime(toDoFormModel.getDeadLineTime())
                .setId(toDoFormModel.getToDoId())
                .setNotes(toDoFormModel.getNotes())
                .setToDoTaskList(convertFormTaskToDomainTask(toDoFormModel))
                .setComplete(calculateDone(toDoFormModel));
    }

    private List<ToDoTask> convertFormTaskToDomainTask(ToDoFormModel toDoFormModel) {
        if(toDoFormModel.getToDoFormTaskList() == null) {
            return Collections.singletonList(new ToDoTask()
                    .setName(toDoFormModel.getToDoName()));
        }
        return toDoFormModel.getToDoFormTaskList().stream()
                .map(e -> new ToDoTask()
                        .setName(e.getName())).collect(Collectors.toList());
    }

    private Boolean calculateDone(ToDoFormModel toDoFormModel) {
        if(toDoFormModel.getToDoFormTaskList() == null) {
            return false;
        }
         return !toDoFormModel.getToDoFormTaskList()
                 .stream()
                 .filter(e -> !e.getDone())
                 .findAny().isPresent();
    }

}
