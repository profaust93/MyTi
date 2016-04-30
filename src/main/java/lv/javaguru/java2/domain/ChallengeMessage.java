package lv.javaguru.java2.domain;

import javax.persistence.*;

/**
 * Created by germans.kuzmins on 2016.04.23..
 */
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

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }
}
