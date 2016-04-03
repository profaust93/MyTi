package lv.javaguru.java2.domain;


public class ToDoList {
    private Long toDoId;
    private Integer priority;
    private Boolean isDone;
    private String toDoName;

    public ToDoList(Long toDoId, Integer priority, Boolean isDone, String toDoName) {
        this.toDoId = toDoId;
        this.priority = priority;
        this.isDone = isDone;
        this.toDoName = toDoName;
    }

    public Long getToDoId() {
        return toDoId;
    }

    public void setToDoId(Long toDoId) {
        this.toDoId = toDoId;
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
