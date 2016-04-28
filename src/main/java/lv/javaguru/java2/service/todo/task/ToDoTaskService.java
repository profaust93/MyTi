package lv.javaguru.java2.service.todo.task;

import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.domain.ToDoTask;
import lv.javaguru.java2.web.form.model.ToDoTaskModel;

import java.util.List;
import java.util.Set;


public interface ToDoTaskService {
    Set<ToDoTask> getUnDoneTasks(ToDo toDo);

    Set<ToDoTask> makeTaskSetFromList(List<ToDoTaskModel> toDoTaskModels);
}
