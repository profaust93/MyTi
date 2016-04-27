package lv.javaguru.java2.web.form.model;



import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.domain.ToDoTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ToDoModel {
    private Long id;
    private String todoName;
    private String notes;
    private LocalDateTime deadLine;

    public ToDoModel() {
    }

    public ToDoModel(ToDo toDo) {
        this.id = toDo.getId();
        this.todoName = toDo.getListName();
        this.notes = toDo.getNotes();
        this.deadLine = toDo.getDeadLineTime();
        this.toDoTaskModels = convertTasksToWebModel(toDo.getToDoTasks());
    }

    private List<ToDoTaskModel> toDoTaskModels;

    public List<ToDoTaskModel> getToDoTaskModels() {
        return toDoTaskModels;
    }

    public ToDoModel setToDoTaskModels(List<ToDoTaskModel> toDoTaskModels) {
        this.toDoTaskModels = toDoTaskModels;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ToDoModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTodoName() {
        return todoName;
    }

    public ToDoModel setTodoName(String todoName) {
        this.todoName = todoName;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public ToDoModel setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public ToDoModel setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
        return this;
    }

    private List<ToDoTaskModel> convertTasksToWebModel(Set<ToDoTask> toDoTaskSet) {
        if(toDoTaskSet == null) {
            return new ArrayList<>();
        }
        return toDoTaskSet.stream().map(task -> new ToDoTaskModel()
                .setTaskName(task.getTaskName())
                .setCompletedGoal(task.getCompletedGoals())
                .setDescription(task.getDescription())
                .setTaskGoal(task.getCompletedGoals())
        ).collect(Collectors.toList());
    }

    public Set<ToDoTask> convertTaskModelToTask() {
        return this.toDoTaskModels.stream().map(task -> new ToDoTask().setCompletedGoals(task.getCompletedGoal())
                .setDescription(task.getDescription())
                .setGoalsCount(task.getTaskGoal())
                .setId(task.getId())
        ).collect(Collectors.toSet());
    }
}
