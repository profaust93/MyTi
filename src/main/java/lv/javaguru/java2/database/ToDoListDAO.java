package lv.javaguru.java2.database;


import lv.javaguru.java2.domain.ToDo;

import java.util.List;

public interface ToDoListDAO {

    void createOrUpdate (ToDo toDo);

    ToDo getById(Long id);

    void delete(ToDo toDo);

    List<ToDo> getAllToDoList();

    List<ToDo> getAllToDoListByUserId(Long userId);
}
