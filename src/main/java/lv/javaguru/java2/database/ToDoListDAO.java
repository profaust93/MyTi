package lv.javaguru.java2.database;


import lv.javaguru.java2.domain.ToDoList;

import java.util.List;

public interface ToDoListDAO {

    void createOrUpdate (ToDoList toDoList);

    ToDoList getById(Long id);

    void delete(ToDoList toDoList);

    List<ToDoList> getAllToDoList();

    List<ToDoList> getAllToDoListByUserId(Long userId);
}
