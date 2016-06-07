package lv.javaguru.java2.todo.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ToDoList")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "Name")
    private String name;

    @Column(name = "CreateTime", nullable = false)
    @Type(type = "lv.javaguru.java2.domain.types.LocalDateTimeUserType")
    private LocalDateTime createTime;

    @Column(name = "DeadLineTime")
    @Type(type = "lv.javaguru.java2.domain.types.LocalDateTimeUserType")
    private LocalDateTime deadLineTime;

    @Column(name = "Notes")
    @Type(type="text")
    private String notes;

    @Column(name = "Done")
    private Boolean isComplete;

    @ElementCollection
    @CollectionTable(name="ToDoTask", joinColumns=@JoinColumn(name="toDoId"))
    private List<ToDoTask> toDoTaskList;

    @Column(name = "UserId")
    private Long userId;

    public Long getId() {
        return Id;
    }

    public ToDo setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ToDo setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ToDo setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getDeadLineTime() {
        return deadLineTime;
    }

    public ToDo setDeadLineTime(LocalDateTime deadLineTime) {
        this.deadLineTime = deadLineTime;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public ToDo setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public ToDo setComplete(Boolean complete) {
        isComplete = complete;
        return this;
    }

    public List<ToDoTask> getToDoTaskList() {
        return toDoTaskList;
    }

    public ToDo setToDoTaskList(List<ToDoTask> toDoTaskList) {
        this.toDoTaskList = toDoTaskList;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public ToDo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @PrePersist
    public void prePersist() {
        if(isComplete == null) //We set default value in case if the value is not set yet.
            isComplete = false;
    }
}
