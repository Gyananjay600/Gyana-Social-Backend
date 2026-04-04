package com.gyana.Gyana_Social.request;

public class SendMessageRequest {

    private String content;
    private Integer receiverId;

    public SendMessageRequest() {}

    public SendMessageRequest(String content, Integer receiverId) {
        this.content = content;
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "SendMessageRequest{" +
                "content='" + content + '\'' +
                ", receiverId=" + receiverId +
                '}';
    }
}
