package lv.javaguru.java2.database;


import lv.javaguru.java2.domain.ToDo;

import java.util.List;

public interface ToDoDAO {

    void create(ToDo toDo) throws DBException;

    ToDo getById (Long id) throws DBException;

    void update (ToDo toDo) throws DBException;

    void delete (ToDo toDo) throws DBException;

    List<ToDo> getAllToDo () throws DBException;

    void delete (List<ToDo> todoList) throws DBException;

    List<ToDo> getToDoByUserId(Long userId) throws DBException;


}
