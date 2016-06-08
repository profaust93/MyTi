package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import lv.javaguru.java2.todo.form.ToDoListModel;

import java.util.List;

public interface ToDoService {

    void upsertToDo(ToDoFormModel toDoFormModel, Long userId) throws ToDoException;

    ToDoFormModel getToDoById(Long toDoId) throws ToDoException;

    void deleteToDo(Long toDoId, Long userId) throws ToDoException;

}
