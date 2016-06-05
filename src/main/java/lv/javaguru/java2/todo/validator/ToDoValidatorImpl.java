package lv.javaguru.java2.todo.validator;

import lv.javaguru.java2.todo.exception.ToDoError;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoFormModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ToDoValidatorImpl implements ToDoValidator {
    @Override
    public void validateToDo(ToDoFormModel toDoFormModel) throws ToDoException {
        if(!Optional.ofNullable(toDoFormModel.getToDoName()).isPresent() ||
                StringUtils.isBlank(toDoFormModel.getToDoName())) {

            throw new ToDoException(ToDoError.VALIDATION_FAILS);
        }
    }
}
