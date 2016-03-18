package lv.javaguru.java2.domain;

import java.time.LocalDateTime;

/**
 * Created by ruslan on 16.17.3.
 */
public class ToDo {
    private Long toDoId;
    private LocalDateTime toDoTime;
    private LocalDateTime deadLineTime;
    private Integer cathegoryId;
    private String shortDescription;
    private String longDescription;
    private Integer priority;
    private Boolean isDone;

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

    public Integer getCathegoryId() {
        return cathegoryId;
    }

    public void setCathegoryId(Integer cathegoryId) {
        this.cathegoryId = cathegoryId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
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
}
