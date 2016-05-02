package lv.javaguru.java2.domain;

import java.time.LocalDateTime;

/**
 * Created by Ruslan on 2016.04.17..
 */
public class ChallengeList {

    private Long challengeId;
    private String challengeName;
    private Long fromUserId;
    private Long toUserId;
    private String challengeState;
    private String description;
    private LocalDateTime endTime;

    public ChallengeList(Challenge challenge) {
        this.challengeId = challenge.getChallengeId();
        this.challengeName = challenge.getChallengeName();
        this.fromUserId = challenge.getFromUserId();
        this.toUserId = challenge.getToUserId();
        this.challengeState = challenge.getChallengeState();
        this.description = challenge.getDescription();
        this.endTime = challenge.getEndTime();
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
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
