package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.todo.form.ToDoListModel;

import java.util.List;

public interface ToDoService {

    List<ToDoListModel> getListOfToDo(Integer page);


}
