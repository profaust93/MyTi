package lv.javaguru.java2.todo.form;

import java.time.LocalDateTime;

public class ToDoListModel {
    private String name;
    private LocalDateTime deadLineTime;
    private Boolean isComplete;

    public String getName() {
        return name;
    }

    public ToDoListModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getDeadLineTime() {
        return deadLineTime;
    }

    public ToDoListModel setDeadLineTime(LocalDateTime deadLineTime) {
        this.deadLineTime = deadLineTime;
        return this;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public ToDoListModel setComplete(Boolean complete) {
        isComplete = complete;
        return this;
    }
}
