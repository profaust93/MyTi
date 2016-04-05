package lv.javaguru.java2.domain;

import java.time.LocalDateTime;

/**
 * Created by Ruslan on 2016.04.05..
 */
public class TimeLapsList {

    private Long timeLapsId;
    private String timeLapsName;
    private LocalDateTime completeTime;

    public TimeLapsList(Long timeLapsId, String timeLapsName, LocalDateTime completeTime) {
        this.timeLapsId = timeLapsId;
        this.timeLapsName = timeLapsName;
        this.completeTime = completeTime;
    }

    public Long getTimeLapsId() {
        return timeLapsId;
    }

    public void setTimeLapsId(Long timeLapsId) {
        this.timeLapsId = timeLapsId;
    }

    public String getTimeLapsName() {
        return timeLapsName;
    }

    public void setTimeLapsName(String timeLapsName) {
        this.timeLapsName = timeLapsName;
    }

    public LocalDateTime getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
    }
}
