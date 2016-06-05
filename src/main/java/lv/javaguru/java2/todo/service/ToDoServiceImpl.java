package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.todo.database.ToDoDAO;
import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.exception.ToDoError;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import lv.javaguru.java2.todo.util.ToDoModelConverter;
import lv.javaguru.java2.todo.validator.ToDoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoValidator validator;

    @Autowired
    private ToDoModelConverter toDoModelConverter;

    @Autowired
    private ToDoDAO toDoDAO;

    @Override
    public void upsertToDo(ToDoFormModel toDoFormModel, Long userId) throws ToDoException {
        validator.validateToDo(toDoFormModel);
        try {
            ToDo toDo = toDoModelConverter.convertFormModelToDomain(toDoFormModel);
            toDo.setUserId(userId);
            toDoDAO.createOrUpdate(toDo);
        } catch (DBException e) {
            throw new ToDoException(ToDoError.DB_ERROR);
        }
    }
}
