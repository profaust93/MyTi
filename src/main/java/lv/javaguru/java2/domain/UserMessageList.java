package lv.javaguru.java2.domain;

/**
 * Created by Ruslan on 2016.04.25..
 */
public class UserMessageList {

    private Long messageId;
    private Long challengeId;
    private String message;
    private Long senderId;
    private Long recipientId;

    public UserMessageList(Long messageId, Long challengeId, String message, Long senderId, Long recipientId) {
        this.messageId = messageId;
        this.challengeId = challengeId;
        this.message = message;
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

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
