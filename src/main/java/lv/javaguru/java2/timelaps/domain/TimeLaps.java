package lv.javaguru.java2.timelaps.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TimeLaps")
public class TimeLaps {

    @Id
    @Column(name = "TimeLapsId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long timeLapsId;
    @Column(name = "CompleteTime")
    @Type(type = "lv.javaguru.java2.domain.types.LocalDateTimeUserType")
    private LocalDateTime completeTime;
    @Column(name = "UserId")
    private Long userId;
    @Column(name = "ShortDescription")
    private String shortDescription;
    @Column(name = "LongDescription")
    private String longDescription;
    @Column(name = "Category")
    private String category;
    @Column(name = "TimeLapsName")
    private String timeLapsName;

    public Long getTimeLapsId() {
        return timeLapsId;
    }

    public TimeLaps setTimeLapsId(Long timeLapsId) {
        this.timeLapsId = timeLapsId;
        return this;
    }

    public LocalDateTime getCompleteTime() {
        return completeTime;
    }

    public TimeLaps setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public TimeLaps setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public TimeLaps setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public TimeLaps setLongDescription(String longDescription) {
        this.longDescription = longDescription;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public TimeLaps setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getTimeLapsName() {
        return timeLapsName;
    }

    public TimeLaps setTimeLapsName(String timeLapsName) {
        this.timeLapsName = timeLapsName;
        return this;
    }
}
