package lv.javaguru.java2.domain;

import javax.persistence.*;

@Entity
@Table(name = "UserMessages")
public class ChallengeMessage {
    @Column(name = "Id",nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;
    @Column(name = "ChallengeId")
    private Long challengeId;
    @Column(name = "Message")
    private String message;
    @Column(name = "SenderId")
    private Long senderId;
    @Column(name = "RecipientId")
    private Long recipientId;

    public Long getMessageId() {
        return messageId;
    }

    public ChallengeMessage setMessageId(Long messageId) {
        this.messageId = messageId;
        return this;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public ChallengeMessage setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ChallengeMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public Long getSenderId() {
        return senderId;
    }

    public ChallengeMessage setSenderId(Long senderId) {
        this.senderId = senderId;
        return this;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public ChallengeMessage setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
        return this;
    }
}
