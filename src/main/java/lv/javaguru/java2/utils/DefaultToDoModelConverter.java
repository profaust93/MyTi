package lv.javaguru.java2.utils;

import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.domain.ToDoTask;
import lv.javaguru.java2.web.form.model.ToDoModel;
import lv.javaguru.java2.web.form.model.ToDoTaskModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DefaultToDoModelConverter  implements ToDoModelConverter{
    @Override
    public ToDo convertModelToDomain(ToDoModel toDoModel) {
        return new ToDo().setId(toDoModel.getId())
                .setDeadLineTime(toDoModel.getDeadLine())
                .setListName(toDoModel.getTodoName())
                .setNotes(toDoModel.getNotes())
                .setToDoTasks(convertTaskModelToEntity(toDoModel.getToDoTaskModels()));
    }

    @Override
    public ToDoModel convertDomainToModel(ToDo toDo) {
        if(toDo == null) {
            return null;
        }
        return new ToDoModel().setId(toDo.getId())
                .setTodoName(toDo.getListName())
                .setDeadLine(toDo.getDeadLineTime())
                .setNotes(toDo.getNotes())
                .setComeplete(toDo.getComplete())
                .setToDoTaskModels(convertTasksToWebModel(toDo.getToDoTasks()));
    }

    private List<ToDoTaskModel> convertTasksToWebModel(Set<ToDoTask> toDoTaskSet) {
        if(toDoTaskSet == null) {
            return new ArrayList<>();
        }
        return toDoTaskSet.stream().map(task -> new ToDoTaskModel()
                .setTaskName(task.getTaskName())
                .setCompletedGoal(task.getCompletedGoals())
                .setTaskGoal(task.getGoalsCount())
                .setId(task.getId())
        ).collect(Collectors.toList());
    }

    private  Set<ToDoTask> convertTaskModelToEntity(List<ToDoTaskModel> toDoTaskModels) {
        if (toDoTaskModels != null) {
            return toDoTaskModels.stream().map(task -> new ToDoTask().setId(task.getId())
                            .setCompletedGoals(task.getCompletedGoal())
                            .setGoalsCount(task.getTaskGoal())
                            .setTaskName(task.getTaskName())
            ).collect(Collectors.toSet());
        } else {
            return null;
        }
    }
}
