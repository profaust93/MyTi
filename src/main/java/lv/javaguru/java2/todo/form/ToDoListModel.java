package lv.javaguru.java2.todo.form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ToDoListModel {

    private Long id;
    private String name;
    private LocalDateTime deadLineTime;
    private Boolean isComplete;
    private Integer taskCount;

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

    public Integer getTaskCount() {
        return taskCount;
    }

    public ToDoListModel setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ToDoListModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFormatedDeadLineTime() {
        if(deadLineTime != null) {
            return deadLineTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        return null;
    }
}
