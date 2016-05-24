package lv.javaguru.java2.todo.database;

import lv.javaguru.java2.todo.domain.ToDo;

import java.util.List;

public interface ToDoDAO {

    List<ToDo> getToDoForUser(Long userId, Integer limit, Integer offset);

    Long getTotalToDoCount(Long userId);

    ToDo getToDoByUserId(Long UserId);

}
