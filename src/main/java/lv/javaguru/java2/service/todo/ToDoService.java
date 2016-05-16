package lv.javaguru.java2.service.todo;

import lv.javaguru.java2.web.form.model.ToDoListModel;

import java.util.List;

/**
 * Created by german on 5/16/16 for MyTi project.
 */
public interface ToDoService {

    List<ToDoListModel> getListOfToDo(Integer page);

}
