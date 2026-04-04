package com.gyana.Gyana_Social.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "realtime_messages")
public class RealtimeMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;

    @ManyToOne
    @JsonIgnore
    private User sender;

    @ManyToOne
    @JsonIgnore
    private User receiver;

    private LocalDateTime sentAt;
    private Boolean isRead = false;

    public RealtimeMessage() {}

    public RealtimeMessage(String content, User sender, User receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.sentAt = LocalDateTime.now();
        this.isRead = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "RealtimeMessage{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sender=" + (sender != null ? sender.getId() : null) +
                ", receiver=" + (receiver != null ? receiver.getId() : null) +
                ", sentAt=" + sentAt +
                ", isRead=" + isRead +
                '}';
    }
}
