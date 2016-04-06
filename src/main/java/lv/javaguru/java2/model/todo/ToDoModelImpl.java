package lv.javaguru.java2.model.todo;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ToDoDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.domain.ToDoList;
import lv.javaguru.java2.model.exceptions.ToDoException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


class ToDoModelImpl implements ToDoModel {
    private ToDoDAO toDoDAO;

    @Override
    public void setToDoDAO(ToDoDAO toDoDAO) {
        this.toDoDAO = toDoDAO;
    }

    @Override
    public ToDo getToDoById(Long toDoId) throws ToDoException {
        try {
            return toDoDAO.getById(toDoId);
        } catch (DBException e) {
            throw new ToDoException(e.getMessage());
        }
    }

    @Override
    public List<ToDoList> getAllToDoForUser(String userId) throws ToDoException {
        try {
            List<ToDo> allToDo  = toDoDAO.getToDoByUserId(Long.parseLong(userId));

            List<ToDo> filteredToDo = allToDo.stream().filter(e->e.getUserId()
                    .equals(Long.parseLong(userId))).collect(Collectors.toList());

            return filteredToDo.stream().map(todo ->
                    new ToDoList(todo.getToDoId(), todo.getPriority(),
                            todo.getDone(),todo.getToDoName()))
                    .collect(Collectors.toList());
        } catch (DBException e) {
            throw new ToDoException(e.getMessage());
        }
    }

    @Override
    public void saveToDo(ToDo toDo) throws ToDoException {
        try {
            if(toDo.getToDoId() == null) {
                toDoDAO.create(toDo);
            } else {
                toDoDAO.update(toDo);
            }
        } catch (DBException e) {
            throw new ToDoException("Error while save: " + e.getMessage());
        }

    }

    @Override
    public void deleteToDoList(List<String> toDoIdList) throws ToDoException {
        try {
            toDoDAO.delete(toDoIdList);
        } catch (DBException e) {
            throw new ToDoException("Error while delete: " + e.getMessage());
        }
    }

    @Override
    public ToDo getNearestToDoForUser(Long userId) throws ToDoException {
        try {
            List<ToDo> toDoList = toDoDAO.getToDoByUserId(userId);
            LocalDateTime now = LocalDateTime.now();
            return Collections.min(toDoList, (o1, o2) -> {
                // TODO: 4/3/16 write Comparator
                LocalDateTime time1 = o1.getDeadLineTime();
                LocalDateTime time2 = o2.getDeadLineTime();

                if (time1.equals(time2)){
                    return 0;
                }
                // TODO: 4/3/16 implement comparator

                return 0;
            });
        } catch (DBException e) {
            throw new ToDoException("Error getting nearest Todo: " + e.getMessage());
        }
    }

    @Override
    public TimeLaps makeTimeLapsFromToDo(ToDo todo) {
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setTimeLapsName(todo.getToDoName());
        timeLaps.setCategory(todo.getCategory().orElse(""));
        timeLaps.setCompleteTime(todo.getToDoTime());
        timeLaps.setUserId(todo.getUserId());
        timeLaps.setShortDescription(todo.getShortDescription().orElse(""));
        timeLaps.setLongDescription(todo.getLongDescription().orElse(""));
        return timeLaps;
    }
}
