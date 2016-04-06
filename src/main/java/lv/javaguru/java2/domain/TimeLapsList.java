package lv.javaguru.java2.domain;

import java.time.LocalDateTime;

/**
 * Created by Ruslan on 2016.04.05..
 */
public class TimeLapsList {

    private Long timeLapsId;
    private String timeLapsName;
    private LocalDateTime completeTime;
    private String shortDescription;
    private String longDescription;
    private String category;


    public TimeLapsList(Long timeLapsId, String timeLapsName, LocalDateTime completeTime,
                        String shortDescription, String longDescription,String category) {
        this.timeLapsId = timeLapsId;
        this.timeLapsName = timeLapsName;
        this.completeTime = completeTime;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
