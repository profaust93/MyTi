package lv.javaguru.java2.todo.util;


import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import lv.javaguru.java2.todo.form.ToDoListModel;

public interface ToDoModelConverter {
    ToDoListModel convertDomainToListModel(ToDo todoDomain);
    ToDo convertFormModelToDomain(ToDoFormModel toDoFormModel);
}
