package lv.javaguru.java2.domain;

import javax.persistence.*;

@Entity
@Table(name = "ToDoTask")
public class ToDoTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "TaskName", nullable = false)
    private String taskName;



    @Column(name = "Goals")
    private Integer goalsCount;

    @Column(name = "CompletedGoals")
    private Integer completedGoals;

    public Long getId() {
        return id;
    }

    public ToDoTask setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTaskName() {
        return taskName;
    }

    public ToDoTask setTaskName(String taskName) {
        this.taskName = taskName;
        return this;
    }


    public Integer getGoalsCount() {
        return goalsCount;
    }

    public ToDoTask setGoalsCount(Integer goalsCount) {
        this.goalsCount = goalsCount;
        return this;
    }

    public Integer getCompletedGoals() {
        return completedGoals;
    }

    public ToDoTask setCompletedGoals(Integer completedGoals) {
        this.completedGoals = completedGoals;
        return this;
    }
}
