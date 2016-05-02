package lv.javaguru.java2.utils;

import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.domain.ToDoTask;
import lv.javaguru.java2.web.form.model.ToDoModel;
import lv.javaguru.java2.web.form.model.ToDoTaskModel;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;


public class ToDoModelConverterTest {

    private ToDoModelConverter toDoModelConverter = new DefaultToDoModelConverter();

    private final Long TODO_ID = 23L;
    private final String TODO_NOTES = "todo test notes";
    private final String TODO_NAME = "todo test name";
    private final LocalDateTime TODO_DEADlINE_TIME = LocalDateTime.now();
    private final LocalDateTime TODO_CREATE_TIME = LocalDateTime.now();
    private final Boolean IS_TODO_COMPLETE = false;
    private final Long TASK_ID = 10L;
    private final String TASK_NAME = "Test task";
    private final String TASK_DESC = "Test task Description";
    private final Integer TASK_GOALS_COUNT = 15;
    private final Integer TASK_COMPLETED_GOALS_COUNT = 8;

    @Test
    public void convertEntityToWebModel() throws Exception {

        ToDoTask toDoTask = new ToDoTask().setId(TASK_ID).setTaskName(TASK_NAME)
                .setGoalsCount(TASK_GOALS_COUNT)
                .setCompletedGoals(TASK_COMPLETED_GOALS_COUNT);

        ToDo todoEntity = new ToDo().setId(TODO_ID)
                .setNotes(TODO_NOTES)
                .setListName(TODO_NAME)
                .setDeadLineTime(TODO_DEADlINE_TIME)
                .setComplete(IS_TODO_COMPLETE)
                .setCreateTime(TODO_CREATE_TIME)
                .setToDoTasks(new HashSet<>(Collections.singletonList(toDoTask)));

        ToDoModel toDoModel = toDoModelConverter.convertDomainToModel(todoEntity);

        //check if all fields equals
        testToDoFields(toDoModel,todoEntity);

        testToDoTaskFields(toDoModel.getToDoTaskModels().get(0),
                todoEntity.getToDoTasks().iterator().next());

    }

    @Test
    public void convertWebModelToEntity() throws Exception {
        ToDoTaskModel toDoTaskModel = new ToDoTaskModel()
                .setTaskGoal(TASK_GOALS_COUNT)
                .setTaskName(TASK_NAME)
                .setId(TASK_ID)
                .setCompletedGoal(TASK_COMPLETED_GOALS_COUNT)
                .setDescription(TASK_DESC);

        ToDoModel toDoModel = new ToDoModel()
                .setId(TODO_ID)
                .setToDoTaskModels(Collections.singletonList(toDoTaskModel))
                .setComeplete(IS_TODO_COMPLETE)
                .setDeadLine(TODO_DEADlINE_TIME)
                .setNotes(TODO_NOTES)
                .setTodoName(TODO_NAME);

        ToDo todoEntity = toDoModelConverter.convertModelToDomain(toDoModel);

        //check if all fields equals
        testToDoFields(toDoModel,todoEntity);

        testToDoTaskFields(toDoModel.getToDoTaskModels().get(0),
                todoEntity.getToDoTasks().iterator().next());

    }

    @Test
    public void tryConvertWebModelWithNullFields() throws Exception {
        ToDoTask toDoTask = new ToDoTask();
        ToDo todoEntity = new ToDo().setToDoTasks(new HashSet<>(Collections.singleton(toDoTask)));
        ToDoModel toDoModel = toDoModelConverter.convertDomainToModel(todoEntity);

        testToDoFields(toDoModel,todoEntity);

        testToDoTaskFields(toDoModel.getToDoTaskModels().get(0),
                todoEntity.getToDoTasks().iterator().next());

    }

    @Test
    public void tryConvertEntityModelWithNullFields() throws Exception {
        ToDoTaskModel toDoTaskModel = new ToDoTaskModel();
        ToDoModel toDoModel = new ToDoModel().setToDoTaskModels(Collections.singletonList(toDoTaskModel));

        ToDo todoEntity = toDoModelConverter.convertModelToDomain(toDoModel);

        testToDoFields(toDoModel,todoEntity);

        testToDoTaskFields(toDoModel.getToDoTaskModels().get(0),
                todoEntity.getToDoTasks().iterator().next());

    }

    private void testToDoFields(ToDoModel toDoModel, ToDo todoEntity) {

        assertEquals(toDoModel.getId(),todoEntity.getId());
        assertEquals(toDoModel.getDeadLine(),todoEntity.getDeadLineTime());
        assertEquals(toDoModel.getNotes(),todoEntity.getNotes());
        assertEquals(toDoModel.getTodoName(),todoEntity.getListName());


    }

    private void testToDoTaskFields(ToDoTaskModel toDoTaskModel, ToDoTask toDoTask) {
        assertEquals(toDoTaskModel.getTaskName(), toDoTask.getTaskName());

        assertEquals(toDoTaskModel.getId(), toDoTask.getId());

        assertEquals(toDoTaskModel.getCompletedGoal(), toDoTask.getCompletedGoals());

        assertEquals(toDoTaskModel.getTaskGoal(), toDoTask.getGoalsCount());

    }
}