package lv.javaguru.java2.domain;

import java.time.LocalDateTime;

/**
 * Created by ruslan on 16.17.3.
 */
public class TimeLaps {

    private Long timeLapsId;
    private LocalDateTime completeTime;
    private Long toDoId;
    private String shortDescription;
    private String longDescription;
    private Integer category;

    public Long getTimeLapsId() {
        return timeLapsId;
    }

    public void setTimeLapsId(Long timeLapsId) {
        this.timeLapsId = timeLapsId;
    }

    public LocalDateTime getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
    }

    public Long getToDoId() {
        return toDoId;
    }

    public void setToDoId(Long toDoId) {
        this.toDoId = toDoId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
