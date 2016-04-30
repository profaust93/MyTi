package lv.javaguru.java2.utils;

import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.web.form.model.ToDoModel;

public interface ToDoModelConverter {
    ToDo convertModelToDomain(ToDoModel toDoModel);

    ToDoModel convertDomainToModel(ToDo toDo);
}
