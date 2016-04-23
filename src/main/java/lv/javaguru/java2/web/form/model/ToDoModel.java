package lv.javaguru.java2.web.form.model;



import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.domain.ToDoTask;

import java.time.LocalDateTime;
import java.util.Set;

public class ToDoModel {
    private Long id;
    private String todoName;
    private String notes;
    private LocalDateTime deadLine;

    public ToDoModel(ToDo toDo) {
        this.id = toDo.getId();
        this.todoName = toDo.getListName();
        this.notes = toDo.getNotes();
        this.deadLine = toDo.getDeadLineTime();
        this.toDoTaskModels = convertTasksToWebModel(toDo.getToDoTasks());
    }

    private Set<ToDoTaskModel> toDoTaskModels;

    public Set<ToDoTaskModel> getToDoTaskModels() {
        return toDoTaskModels;
    }

    public ToDoModel setToDoTaskModels(Set<ToDoTaskModel> toDoTaskModels) {
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

    private Set<ToDoTaskModel> convertTasksToWebModel(Set<ToDoTask> toDoTaskSet) {
        return null;
    }
}
