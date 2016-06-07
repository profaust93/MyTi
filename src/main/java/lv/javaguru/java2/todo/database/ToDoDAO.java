package lv.javaguru.java2.todo.database;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.todo.domain.ToDo;

import java.util.List;

public interface ToDoDAO {

    List<ToDo> getToDoForUser(Long userId, Integer limit, Integer offset) throws DBException;

    Long getTotalToDoCount(Long userId) throws DBException;

    List<ToDo> getAllToDoByUserId(Long userId) throws DBException;

    void createOrUpdate(ToDo toDo) throws DBException;


    void deleteToD(Long toDoId) throws DBException;

    ToDo getToDoById(Long id) throws DBException;

    Boolean checkIfBelongToUser(Long toDoId, Long userId) throws DBException;
}
