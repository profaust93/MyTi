package lv.javaguru.java2.statistic.form;


public class StatisticFormModel {
    private Long toDoCount;

    private Long timeLapsCount;

    public Long getToDoCount() {
        return toDoCount;
    }

    public StatisticFormModel setToDoCount(Long toDoCount) {
        this.toDoCount = toDoCount;
        return this;
    }

    public Long getTimeLapsCount() {
        return timeLapsCount;
    }

    public StatisticFormModel setTimeLapsCount(Long timeLapsCount) {
        this.timeLapsCount = timeLapsCount;
        return this;
    }
}
