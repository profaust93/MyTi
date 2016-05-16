package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.todo.form.ToDoListModel;

import java.util.List;

/**
 * Created by german on 5/16/16 for MyTi project.
 */
public interface ToDoService {

    List<ToDoListModel> getListOfToDo(Integer page);

}
