package lv.javaguru.java2.todo.validator;

import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoFormModel;

public interface ToDoValidator {
    void validateToDo(ToDoFormModel toDoFormModel, Long userId) throws ToDoException;
    void validateToDoForDelete(Long toDoId, Long userId) throws ToDoException;
}
