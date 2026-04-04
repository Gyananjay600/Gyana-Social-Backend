package com.gyana.Gyana_Social.request;

public class RealtimeMessageRequest {

    private String content;
    private Integer senderId;
    private Integer receiverId;

    public RealtimeMessageRequest() {}

    public RealtimeMessageRequest(String content, Integer senderId, Integer receiverId) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "RealtimeMessageRequest{" +
                "content='" + content + '\'' +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                '}';
    }
}
