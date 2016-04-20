package lv.javaguru.java2.database;


import lv.javaguru.java2.domain.ToDoTask;

import java.util.List;

public interface ToDoTaskDAO {

    void createOrUpdate(ToDoTask toDoTask);

    ToDoTask getTaskById(Long id);

    void delete(ToDoTask toDoTask);

    List<ToDoTask> getAllTasks();
}
