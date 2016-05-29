package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoListModel;

import java.util.List;

public interface ToDoListService {

    List<ToDoListModel> getListOfToDoForUser(Integer page, Long UserId) throws ToDoException;

    Integer getNumberOfPage(Long UserId) throws ToDoException;

}
