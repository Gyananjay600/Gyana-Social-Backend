package com.gyana.Gyana_Social.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;

    // Sender of the message
    @ManyToOne
    @JsonIgnore
    private User sender;

    // Receiver of the message
    @ManyToOne
    @JsonIgnore
    private User receiver;

    private LocalDateTime sentAt;
    private LocalDateTime editedAt;
    
    private Boolean isRead = false;

    public ChatMessage() {}

    public ChatMessage(Integer id, String content, User sender, User receiver, LocalDateTime sentAt, 
                       LocalDateTime editedAt, Boolean isRead) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.sentAt = sentAt;
        this.editedAt = editedAt;
        this.isRead = isRead;
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

    public LocalDateTime getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(LocalDateTime editedAt) {
        this.editedAt = editedAt;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", sentAt=" + sentAt +
                ", editedAt=" + editedAt +
                ", isRead=" + isRead +
                '}';
    }
}
