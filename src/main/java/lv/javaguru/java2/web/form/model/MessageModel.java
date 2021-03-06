package lv.javaguru.java2.web.form.model;

import lv.javaguru.java2.domain.ChallengeMessage;

/**
 * Created by germans.kuzmins on 2016.04.23..
 */
public class MessageModel {

    private Long messageId;
    private Long challengeId;
    private String message;
    private Long senderId;
    private Long recipientId;

    public MessageModel(ChallengeMessage challengeMessage) {
        this.messageId = challengeMessage.getMessageId();
        this.challengeId = challengeMessage.getChallengeId();
        this.message = challengeMessage.getMessage();
        this.senderId = challengeMessage.getSenderId();
        this.recipientId = challengeMessage.getRecipientId();
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
