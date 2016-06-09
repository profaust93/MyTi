package lv.javaguru.java2.challenge.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Challenge")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ChallengeId")
    private Long challengeId;

    @Column(name = "SenderId")
    private Long senderId;

    @Column(name = "ReceiverId")
    private Long receiverId;

    @Column(name = "ChallengeName")
    private String challengeName;

    @Column(name = "ChallengeNote")
    private String challengeNote;

    @Column(name = "Status")
    private String status;

    @Column(name = "CreateTime")
    @Type(type = "lv.javaguru.java2.domain.types.LocalDateTimeUserType")
    private LocalDateTime createTime;

    @Column(name = "DeadLineTime")
    @Type(type = "lv.javaguru.java2.domain.types.LocalDateTimeUserType")
    private LocalDateTime deadLineTime;

    @Column(name = "IsComplete")
    private Boolean isComplete;

    @ElementCollection
    @CollectionTable(name = "SubChallenge", joinColumns = @JoinColumn(name = "ChallengeId"))
    private List<SubChallenge> subChallenges;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Challenge setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public Challenge setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
        return this;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Challenge setSenderId(Long senderId) {
        this.senderId = senderId;
        return this;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public Challenge setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
        return this;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public Challenge setChallengeName(String challengeName) {
        this.challengeName = challengeName;
        return this;
    }

    public String getChallengeNote() {
        return challengeNote;
    }

    public Challenge setChallengeNote(String challengeNote) {
        this.challengeNote = challengeNote;
        return this;
    }

    public List<SubChallenge> getSubChallenges() {
        return subChallenges;
    }

    public Challenge setSubChallenges(List<SubChallenge> subChallenges) {
        this.subChallenges = subChallenges;
        return this;
    }

    public LocalDateTime getDeadLineTime() {
        return deadLineTime;
    }

    public Challenge setDeadLineTime(LocalDateTime deadLineTime) {
        this.deadLineTime = deadLineTime;
        return this;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public Challenge setComplete(Boolean complete) {
        isComplete = complete;
        return this;
    }
}
