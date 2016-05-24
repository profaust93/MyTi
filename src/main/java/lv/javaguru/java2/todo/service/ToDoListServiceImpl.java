package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoListModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListServiceImpl implements ToDoListService {
    @Override
    public List<ToDoListModel> getListOfToDo(Integer page) throws ToDoException {
        return null;
    }
}
