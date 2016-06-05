package lv.javaguru.java2.todo.form;

/**
 * Created by germans.kuzmins on 2016.05.29..
 */
public class ToDoFormTask {

    private String name;

    private Boolean isDone;

    public String getName() {
        return name;
    }

    public ToDoFormTask setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getDone() {
        return isDone;
    }

    public ToDoFormTask setDone(Boolean done) {
        isDone = done;
        return this;
    }
}
