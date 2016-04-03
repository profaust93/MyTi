package lv.javaguru.java2.model.todo;

import lv.javaguru.java2.database.ToDoDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.domain.ToDoList;

import java.util.List;

public interface ToDoModel {

    void setToDoDAO(ToDoDAO toDoDAO);

    ToDo getToDoById(Long toDoId);

    List<ToDoList> getAllToDoForUser(Long userId);

    void saveToDo(ToDo toDo);

    void deleteToDoList(List<String> toDoIdList);

    ToDo getNearestToDoForUser(Long UserId);

    TimeLaps makeTimeLapsFromToDo(ToDo todo);


}
