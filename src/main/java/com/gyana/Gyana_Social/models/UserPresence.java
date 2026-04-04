package com.gyana.Gyana_Social.models;

import java.io.Serializable;

public class UserPresence implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String userName;
    private Boolean online;
    private Long lastSeen;

    public UserPresence() {}

    public UserPresence(Integer userId, String userName, Boolean online, Long lastSeen) {
        this.userId = userId;
        this.userName = userName;
        this.online = online;
        this.lastSeen = lastSeen;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Long getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Long lastSeen) {
        this.lastSeen = lastSeen;
    }

    @Override
    public String toString() {
        return "UserPresence{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", online=" + online +
                ", lastSeen=" + lastSeen +
                '}';
    }
}
