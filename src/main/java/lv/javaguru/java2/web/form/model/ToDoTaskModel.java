package lv.javaguru.java2.web.form.model;

public class ToDoTaskModel {
    private String taskName;
    private String description;
    private Integer taskGoal;
    private Integer completedGoal;

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
}
