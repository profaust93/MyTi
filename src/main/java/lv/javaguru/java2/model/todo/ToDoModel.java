package lv.javaguru.java2.model.todo;

import lv.javaguru.java2.database.ToDoDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.domain.ToDoList;
import lv.javaguru.java2.model.exceptions.ToDoException;

import java.util.List;

public interface ToDoModel {

    void setToDoDAO(ToDoDAO toDoDAO);

    ToDo getToDoById(Long toDoId) throws ToDoException;

    List<ToDoList> getAllToDoForUser(String userId) throws ToDoException;

    void saveToDo(ToDo toDo) throws ToDoException;

    void deleteToDoList(List<String> toDoIdList) throws ToDoException;

    ToDo getNearestToDoForUser(Long userId) throws ToDoException;

    TimeLaps makeTimeLapsFromToDo(ToDo todo);
}
