package lv.javaguru.java2.domain;

import java.time.LocalDateTime;

/**
 * Created by ruslan on 16.17.3.
 */
public class TimeLaps {

    private Long timeLapsId;
    private LocalDateTime completeTime;
    private Long toDoId;
    private String shorDescription;
    private String longDescription;
    private Integer cathegory;

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

    public String getShorDescription() {
        return shorDescription;
    }

    public void setShorDescription(String shorDescription) {
        this.shorDescription = shorDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Integer getCathegory() {
        return cathegory;
    }

    public void setCathegory(Integer cathegory) {
        this.cathegory = cathegory;
    }
}
