package lv.javaguru.java2.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Type(type = "lv.javaguru.java2.domain.types.LocalDateTimeUserType")
    private LocalDateTime endTime;

    public String getChallengeName() {
        return challengeName;
    }

    public Challenge setChallengeName(String challengeName) {
        this.challengeName = challengeName;
        return this;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public Challenge setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
        return this;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public Challenge setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public Challenge setToUserId(Long toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public String getChallengeState() {
        return challengeState;
    }

    public Challenge setChallengeState(String challengeState) {
        this.challengeState = challengeState;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Challenge setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Challenge setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }
}
