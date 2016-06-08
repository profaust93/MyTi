package lv.javaguru.java2.todo.validator;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.todo.database.ToDoDAO;
import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.exception.ToDoError;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ToDoValidatorImpl implements ToDoValidator {

    @Autowired
    private ToDoDAO toDoDAO;

    @Override
    public void validateToDo(ToDoFormModel toDoFormModel, Long userId) throws ToDoException {
        if(!Optional.ofNullable(toDoFormModel.getToDoName()).isPresent() ||
                StringUtils.isBlank(toDoFormModel.getToDoName())) {

            throw new ToDoException(ToDoError.VALIDATION_FAILS);
        }

        if(toDoFormModel.getToDoId() != null) {
            checkIfToDoBelongToUser(toDoFormModel.getToDoId(), userId);
        }

        if (toDoFormModel.getToDoFormTaskList() == null || toDoFormModel.getToDoFormTaskList().size() == 0) {
            throw new ToDoException(ToDoError.VALIDATION_FAILS);
        }
    }

    @Override
    public void validateToDoForDelete(Long toDoId, Long userId) throws ToDoException {
        if(toDoId != null) {
            checkIfToDoBelongToUser(toDoId, userId);
        }
    }

    private void checkIfToDoBelongToUser(Long toDoId, Long userId) throws ToDoException {
        try {
            if (!toDoDAO.checkIfBelongToUser(toDoId, userId)) {
                throw new ToDoException();
            }
        } catch (DBException e) {
            throw new ToDoException();
        }
    }
}
