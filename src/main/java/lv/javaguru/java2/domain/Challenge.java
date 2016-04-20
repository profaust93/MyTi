package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Ruslan on 2016.04.15..
 */
@Entity
@Table(name = "Challenge")
public class Challenge {
    @Column(name = "ChallengeName")
    private String challengeName;
    @Column(name = "ChallengeId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long challengeId;
    @Column(name = "FromUserId")
    private Long fromUserId;
    @Column(name = "ToUserId")
    private Long toUserId;
    @Column(name = "ChallengeState")
    private String challengeState;
    @Column(name = "Description")
    private String description;
    @Column(name = "EndTime")
    private LocalDateTime endTime;

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getChallengeState() {
        return challengeState;
    }

    public void setChallengeState(String challengeState) {
        this.challengeState = challengeState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
