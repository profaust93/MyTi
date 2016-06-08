package lv.javaguru.java2.challenge.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ChallengeFormModel {

    private Long challengeId;

    private String challengeNote;

    private String challengeName;

    private Long senderId;

    private Long receiverId;

    private List<SubChallengeForm> subChallengeForms = new ArrayList<>();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime deadLineTime;

    public Long getChallengeId() {
        return challengeId;
    }

    public ChallengeFormModel setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
        return this;
    }

    public String getChallengeNote() {
        return challengeNote;
    }

    public ChallengeFormModel setChallengeNote(String challengeNote) {
        this.challengeNote = challengeNote;
        return this;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public ChallengeFormModel setChallengeName(String challengeName) {
        this.challengeName = challengeName;
        return this;
    }

    public List<SubChallengeForm> getSubChallengeForms() {
        return subChallengeForms;
    }

    public ChallengeFormModel setSubChallengeForms(List<SubChallengeForm> subChallengeForms) {
        this.subChallengeForms = subChallengeForms;
        return this;
    }

    public LocalDateTime getDeadLineTime() {
        return deadLineTime;
    }

    public ChallengeFormModel setDeadLineTime(LocalDateTime deadLineTime) {
        this.deadLineTime = deadLineTime;
        return this;
    }

    public Long getSenderId() {
        return senderId;
    }

    public ChallengeFormModel setSenderId(Long senderId) {
        this.senderId = senderId;
        return this;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public ChallengeFormModel setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
        return this;
    }
}
