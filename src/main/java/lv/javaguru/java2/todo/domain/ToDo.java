package lv.javaguru.java2.todo.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ToDoTask")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "TaskName")
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
}
