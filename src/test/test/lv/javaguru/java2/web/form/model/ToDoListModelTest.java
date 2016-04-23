package lv.javaguru.java2.web.form.model;

import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.domain.ToDoTask;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ToDoListModelTest {
    ToDo todo;

    @Before
    public void setUp() throws Exception {
        Set<ToDoTask> toDoTasks =
                new HashSet<>(
                        Arrays.asList(new ToDoTask().setGoalsCount(10),
                                new ToDoTask().setGoalsCount(10),
                                new ToDoTask().setGoalsCount(10)));
        todo = new ToDo().setToDoTasks(toDoTasks);

    }

    @Test
    public void testGetPercentForCompletedTasks() throws Exception {
        todo.getToDoTasks().stream().forEach(e -> e.setCompletedGoals(e.getGoalsCount()));
       ToDoListModel toDoListModel = new ToDoListModel(todo);
        assertEquals("todo wtith completed tasks shoudl have 100%",
                toDoListModel.getPercentDone().compareTo(1.0),0);
    }

    @Test
    public void testGetPercentForFullUncompletedTasks() throws Exception {
        todo.getToDoTasks().stream().forEach(e -> e.setCompletedGoals(0));
        ToDoListModel toDoListModel = new ToDoListModel(todo);
        assertEquals("todo with full uncompleted tasks should have 0%",
                toDoListModel.getPercentDone().compareTo(0.0),0);

    }

    @Test
    public void testGetPercentWithHalfTasks() throws Exception {
        //works only if all tasks goals are even
        todo.getToDoTasks().stream().forEach(e -> e.setCompletedGoals(e.getGoalsCount()/2));
        ToDoListModel toDoListModel = new ToDoListModel(todo);
        assertEquals("todo with full half tasks should have 50%",
                toDoListModel.getPercentDone().compareTo(0.5),0);
    }

    @Test
    public void testGetPercent() throws Exception {
        //works only if all tasks goals are even
        todo.getToDoTasks().stream().forEach(e -> e.setCompletedGoals(e.getGoalsCount()/5));
        ToDoListModel toDoListModel = new ToDoListModel(todo);
        assertEquals("todo tasks should have 25%",
                toDoListModel.getPercentDone().compareTo(0.20),0);
    }
}