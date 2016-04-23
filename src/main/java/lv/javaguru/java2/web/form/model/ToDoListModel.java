package lv.javaguru.java2.web.form.model;

import lv.javaguru.java2.domain.ToDo;
import lv.javaguru.java2.domain.ToDoTask;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ToDoListModel {
    private String toDoName;
    private LocalDateTime createDate;
    private LocalDateTime deadLine;
    private Integer taskCount;
    private Double percentDone;

    public ToDoListModel(ToDo toDo) {
        this.toDoName = toDo.getListName();
        this.createDate = toDo.getCreateTime();
        this.deadLine = toDo.getDeadLineTime();
        this.taskCount = toDo.getToDoTasks().size();
        this.percentDone = calculateDone(toDo.getToDoTasks());
    }

    public String getToDoName() {
        return toDoName;
    }

    public ToDoListModel setToDoName(String toDoName) {
        toDoName = toDoName;
        return this;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public ToDoListModel setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public ToDoListModel setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
        return this;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public ToDoListModel setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
        return this;
    }

    public Double getPercentDone() {
        return percentDone;
    }

    public ToDoListModel setPercentDone(Double percentDone) {
        this.percentDone = percentDone;
        return this;
    }

    /**
     * Count completed precentage of all tasks
     * @param todoTasks set of all tasks in one TodoList
     * @return how many percents in task are complete
     */

    private Double calculateDone(Set<ToDoTask> todoTasks) {
        Double total = todoTasks.stream().mapToDouble(e->(double)e.getCompletedGoals()/e.getGoalsCount())
                .reduce((s1,s2)->s1+s2).orElse(0);

        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(total/todoTasks.size()));
    }
}
