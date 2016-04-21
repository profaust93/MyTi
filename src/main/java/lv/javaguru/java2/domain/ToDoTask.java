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

    @Column(name = "IsComplete")
    private Boolean isComplete;

    @Column(name = "Description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ToDoListId")
    private ToDoList toDoList;

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

    public Boolean getComplete() {
        return isComplete;
    }

    public ToDoTask setComplete(Boolean complete) {
        isComplete = complete;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ToDoTask setDescription(String description) {
        this.description = description;
        return this;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }

    public ToDoTask setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
        return this;
    }
}
