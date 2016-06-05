package lv.javaguru.java2.todo.form;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ToDoFormModel {
    private Long toDoId;
    private String toDoName;
    private String notes;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime deadLineTime;

    private List<ToDoFormTask> toDoFormTaskList = new ArrayList<>();

    public Long getToDoId() {
        return toDoId;
    }

    public ToDoFormModel setToDoId(Long toDoId) {
        this.toDoId = toDoId;
        return this;
    }

    public String getToDoName() {
        return toDoName;
    }

    public ToDoFormModel setToDoName(String toDoName) {
        this.toDoName = toDoName;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public ToDoFormModel setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public LocalDateTime getDeadLineTime() {
        return deadLineTime;
    }

    public ToDoFormModel setDeadLineTime(LocalDateTime deadLineTime) {
        this.deadLineTime = deadLineTime;
        return this;
    }

    public List<ToDoFormTask> getToDoFormTaskList() {
        return toDoFormTaskList;
    }

    public ToDoFormModel setToDoFormTaskList(List<ToDoFormTask> toDoFormTaskList) {
        this.toDoFormTaskList = toDoFormTaskList;
        return this;
    }

    public String getFormatedDeadLineTime() {
        if(deadLineTime == null) {
            return  null;
        }
        return deadLineTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public ToDoFormModel setFormatedDeadLineTime(String deadLineTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.deadLineTime = LocalDateTime.parse(deadLineTime, formatter);
        return this;
    }
}
