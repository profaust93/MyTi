package lv.javaguru.java2.todo.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "ToDoTask")
public class ToDoTask {
    public ToDoTask() {
    }

    public ToDoTask(String name) {
        this.name = name;
    }

    @Column(name = "Name")
    private String name;


    @Column(name = "Done")
    private Boolean isDone;


    public String getName() {
        return name;
    }

    public ToDoTask setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getDone() {
        return isDone;
    }

    public ToDoTask setDone(Boolean done) {
        isDone = done;
        return this;
    }
}
