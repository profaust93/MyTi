package lv.javaguru.java2.service.todo.list;

import lv.javaguru.java2.model.exceptions.ToDoException;
import lv.javaguru.java2.web.form.model.ToDoListModel;
import lv.javaguru.java2.web.form.model.ToDoModel;


public interface ToDoService {

    ToDoModel getToDoList(Long Id) throws ToDoException;

    ToDoListModel getAllToDoForUser(Long userId);

    void upsertToDo(ToDoModel toDoModel);

    void removeToDo();

    void makeToDoDone();

}
