package lv.javaguru.java2.security;

import lv.javaguru.java2.todo.exception.ToDoException;

/**
 * Created by germans.kuzmins on 2016.05.28..
 */
public interface SecurityService {
    Long getCurrentUserId() throws ToDoException;
}
