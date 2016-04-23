package lv.javaguru.java2.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "ToDoList")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "ListName")
    private String listName;

    @Column(name = "CreateTime", nullable = false)
    @Type(type = "lv.javaguru.java2.domain.types.LocalDateTimeUserType")
    private LocalDateTime createTime;

    @Column(name = "DeadLineTime")
    @Type(type = "lv.javaguru.java2.domain.types.LocalDateTimeUserType")
    private LocalDateTime deadLineTime;

    @Column(name = "Notes")
    @Type(type="text")
    private String notes;

    @OneToMany(mappedBy = "toDo")
    private Set<ToDoTask> toDoTasks;

    @Column(name = "UserId")
    private Long userId;

    public Long getId() {
        return Id;
    }

    public ToDo setId(Long id) {
        Id = id;
        return this;
    }

    public String getListName() {
        return listName;
    }

    public ToDo setListName(String listName) {
        this.listName = listName;
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

    public Set<ToDoTask> getToDoTasks() {
        return toDoTasks;
    }

    public ToDo setToDoTasks(Set<ToDoTask> toDoTasks) {
        this.toDoTasks = toDoTasks;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public ToDo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
