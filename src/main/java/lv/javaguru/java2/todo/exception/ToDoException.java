package lv.javaguru.java2.todo.exception;

public class ToDoException extends Exception{

    private ToDoError toDoError;

    public ToDoException() {
        super();
        this.toDoError = ToDoError.TO_DO_ERROR;
    }

    public ToDoException(ToDoError toDoError) {
        super();
        this.toDoError = toDoError;
    }

    public ToDoError getToDoError() {
        return toDoError;
    }
}
