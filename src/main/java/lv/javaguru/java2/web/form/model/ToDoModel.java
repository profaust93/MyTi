package lv.javaguru.java2.web.form.model;

import lv.javaguru.java2.domain.ToDoTask;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ToDoModel {
    private Long id;
    private String todoName;
    private String notes;
    private LocalDateTime deadLine;
    private Boolean isComplete;
    private List<ToDoTaskModel> toDoTaskModels;

    public ToDoModel() {
    }



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



    public Boolean getComeplete() {
        return isComplete;
    }

    public ToDoModel setComeplete(Boolean comeplete) {
        isComplete = comeplete;
        return this;
    }
}
