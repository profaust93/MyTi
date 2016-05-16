package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoListModel;

import java.util.List;

/**
 * Created by german on 5/16/16 for MyTi project.
 */
public interface ToDoListService {

    List<ToDoListModel> getListOfToDo(Integer page) throws ToDoException;

}
