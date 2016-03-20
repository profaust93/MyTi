package lv.javaguru.java2.domain;

import java.time.LocalDateTime;
import java.util.Optional;


public class ToDo {
    private Long toDoId;
    private LocalDateTime toDoTime;
    private LocalDateTime deadLineTime;
    private Integer categoryId;
    private String shortDescription;
    private String longDescription;
    private Integer priority;
    private Boolean isDone;
    private String toDoName;

    public Long getToDoId() {
        return toDoId;
    }

    public void setToDoId(Long toDoId) {
        this.toDoId = toDoId;
    }

    public LocalDateTime getToDoTime() {
        return toDoTime;
    }

    public void setToDoTime(LocalDateTime toDoTime) {
        this.toDoTime = toDoTime;
    }

    public LocalDateTime getDeadLineTime() {
        return deadLineTime;
    }

    public void setDeadLineTime(LocalDateTime deadLineTime) {
        this.deadLineTime = deadLineTime;
    }

    public Optional<Integer> getCategoryId() {
        return Optional.ofNullable(categoryId);
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Optional<String> getShortDescription() {
        return Optional.ofNullable(shortDescription);
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Optional <String> getLongDescription() {
        return Optional.ofNullable(longDescription);
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getToDoName() {
        return toDoName;
    }

    public void setToDoName(String toDoName) {
        this.toDoName = toDoName;
    }
}
