package lv.javaguru.java2.challenge.form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChallengeListModel {

    private Long id;
    private String name;
    private LocalDateTime deadLineTime;
    private Boolean isComplete;
    private Integer challengesCount;


    public Long getId() {
        return id;
    }

    public ChallengeListModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChallengeListModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getDeadLineTime() {
        return deadLineTime;
    }

    public ChallengeListModel setDeadLineTime(LocalDateTime deadLineTime) {
        this.deadLineTime = deadLineTime;
        return this;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public ChallengeListModel setComplete(Boolean complete) {
        isComplete = complete;
        return this;
    }

    public Integer getChallengesCount() {
        return challengesCount;
    }

    public ChallengeListModel setChallengesCount(Integer challengesCount) {
        this.challengesCount = challengesCount;
        return this;
    }

    public String getFormatedDeadLineTime() {
        if(deadLineTime != null) {
            return deadLineTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        return null;
    }
}
