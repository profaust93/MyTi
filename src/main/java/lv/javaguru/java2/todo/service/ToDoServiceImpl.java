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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoValidator validator;

    @Autowired
    private ToDoModelConverter toDoModelConverter;

    @Autowired
    private ToDoDAO toDoDAO;

    @Override
    public void upsertToDo(ToDoFormModel toDoFormModel, Long userId) throws ToDoException {
        validator.validateToDo(toDoFormModel, userId);
        try {
            ToDo toDo = toDoModelConverter.convertFormModelToDomain(toDoFormModel);
            toDo.setUserId(userId);
            toDoDAO.createOrUpdate(toDo);
        } catch (DBException e) {
            throw new ToDoException(ToDoError.DB_ERROR);
        }
    }

    @Override
    public ToDoFormModel getToDoById(Long toDoId) throws ToDoException {
        try {
            ToDo toDo = Optional.ofNullable(toDoDAO.getToDoById(toDoId))
                    .orElseThrow( () -> new ToDoException(ToDoError.NO_TODO_FOUND) );
            return toDoModelConverter.convertDomainToFormModel(toDo);

        } catch (DBException e ) {
            throw new ToDoException();
        }
    }

    @Override
    public void deleteToDo(Long toDoId, Long userId) throws ToDoException {
        validator.validateToDoForDelete(toDoId, userId);
        try {
            toDoDAO.deleteToD(toDoId);
        } catch (DBException e) {
            throw new ToDoException();
        }
    }
}
