package lv.javaguru.java2.web.form.model;

import lv.javaguru.java2.domain.ToDoTask;

public class ToDoTaskModel {
    private Long id;
    private String taskName;
    private String description;
    private Integer taskGoal;
    private Integer completedGoal;

    public Long getId() {
        return id;
    }

    public ToDoTaskModel setId(Long id) {
        this.id = id;
        return this;
    }

    public ToDoTaskModel() {
    }

    public String getTaskName() {
        return taskName;
    }

    public ToDoTaskModel setTaskName(String taskName) {
        this.taskName = taskName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ToDoTaskModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getTaskGoal() {
        return taskGoal;
    }

    public ToDoTaskModel setTaskGoal(Integer taskGoal) {
        this.taskGoal = taskGoal;
        return this;
    }

    public Integer getCompletedGoal() {
        return completedGoal;
    }

    public ToDoTaskModel setCompletedGoal(Integer completedGoal) {
        this.completedGoal = completedGoal;
        return this;
    }

    public Boolean getComplete() {
        return !(taskGoal == null || completedGoal == null) && taskGoal.equals(completedGoal);
    }
}
