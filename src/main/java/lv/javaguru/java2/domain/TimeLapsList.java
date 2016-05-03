package lv.javaguru.java2.domain;


import java.time.LocalDateTime;

public class TimeLapsList {

    private Long timeLapsId;
    private String timeLapsName;
    private LocalDateTime completeTime;
    private String shortDescription;
    private String longDescription;
    private String category;


    public TimeLapsList(TimeLaps timeLaps) {
        this.timeLapsId = timeLaps.getTimeLapsId();
        this.timeLapsName = timeLaps.getTimeLapsName();
        this.completeTime = timeLaps.getCompleteTime();
        this.shortDescription = timeLaps.getShortDescription();
        this.longDescription = timeLaps.getLongDescription();
        this.category = timeLaps.getCategory();
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
