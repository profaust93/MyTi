package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ruslan on 16.17.3 for MyTi project.
 */
@Entity
@Table(name = "timelaps")
public class TimeLaps {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TimeLapsId", nullable = false)
    private Long timeLapsId;

    @Column(name = "CompleteTime")
    private LocalDateTime completeTime;

    @Transient
    private Long userId;
    @Transient
    private String shortDescription;
    @Transient
    private String longDescription;
    @Transient
    private String category;
    @Transient
    private String timeLapsName;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
