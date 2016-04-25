package lv.javaguru.java2.service.todo.list;

import lv.javaguru.java2.model.exceptions.ToDoException;
import lv.javaguru.java2.web.form.model.ToDoListModel;
import lv.javaguru.java2.web.form.model.ToDoModel;

import java.util.List;


public interface ToDoService {

    ToDoModel getToDoList(Long Id) throws ToDoException;

    List<ToDoListModel> getAllToDoForUser(Long userId) throws ToDoException;

    void upsertToDo(ToDoModel toDoModel) throws ToDoException;

    void removeToDo(ToDoModel toDoModel) throws ToDoException;

    void makeToDoDone(ToDoModel toDoModel) throws ToDoException;

}
