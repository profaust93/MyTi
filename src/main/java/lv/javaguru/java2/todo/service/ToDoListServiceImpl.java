package lv.javaguru.java2.todo.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.todo.database.ToDoDAO;
import lv.javaguru.java2.todo.domain.ToDo;
import lv.javaguru.java2.todo.exception.ToDoError;
import lv.javaguru.java2.todo.exception.ToDoException;
import lv.javaguru.java2.todo.form.ToDoListModel;
import lv.javaguru.java2.todo.util.ToDoModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ToDoListServiceImpl implements ToDoListService {

    private final static Integer RECORDS_PER_PAGE = 20;

    @Autowired
    private ToDoDAO toDoDAO;

    @Autowired
    private ToDoModelConverter toDoModelConverter;

    @Override
    public List<ToDoListModel> getListOfToDoForUser(Integer page, Long userId) throws ToDoException {

        Integer offset = (page - 1) * RECORDS_PER_PAGE;
        try {
            List<ToDo> toDoList = toDoDAO.getToDoForUser(userId,RECORDS_PER_PAGE,offset);
            return toDoList.stream()
                    .map(toDoModelConverter::convertDomainToListModel)
                    .collect(Collectors.toList());
        } catch (DBException e) {
            throw new ToDoException(ToDoError.TO_DO_ERROR);
        }
    }

    @Override
    public Integer getNumberOfPage(Long userId) throws ToDoException {
        try {
            Long toDoCount = toDoDAO.getTotalToDoCount(userId);
            return (int)Math.ceil(toDoCount * 1.0 / RECORDS_PER_PAGE);
        } catch (DBException e) {
            throw new ToDoException(ToDoError.TO_DO_ERROR);
        }
    }
}
