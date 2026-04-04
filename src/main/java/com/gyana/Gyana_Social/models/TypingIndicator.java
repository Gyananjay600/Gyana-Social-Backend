package com.gyana.Gyana_Social.models;

import java.io.Serializable;

public class TypingIndicator implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer senderId;
    private Integer receiverId;
    private Boolean isTyping;

    public TypingIndicator() {}

    public TypingIndicator(Integer senderId, Integer receiverId, Boolean isTyping) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.isTyping = isTyping;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Boolean getIsTyping() {
        return isTyping;
    }

    public void setIsTyping(Boolean isTyping) {
        this.isTyping = isTyping;
    }

    @Override
    public String toString() {
        return "TypingIndicator{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", isTyping=" + isTyping +
                '}';
    }
}
