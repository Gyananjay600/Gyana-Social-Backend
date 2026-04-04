package com.gyana.Gyana_Social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gyana.Gyana_Social.models.RealtimeMessage;
import com.gyana.Gyana_Social.models.TypingIndicator;
import com.gyana.Gyana_Social.models.UserPresence;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/ws")
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Store online users
    private static final Set<Integer> onlineUsers = new HashSet<>();

    // WebSocket message endpoints
    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload RealtimeMessage message) throws Exception {
        if (message.getReceiver() != null && message.getReceiver().getId() != null) {
            Integer receiverId = message.getReceiver().getId();
            messagingTemplate.convertAndSendToUser(
                    receiverId.toString(),
                    "/queue/messages",
                    message
            );
        }
    }

    // Typing indicator endpoint
    @MessageMapping("/typing")
    public void sendTypingIndicator(@Payload TypingIndicator indicator) {
        if (indicator.getReceiverId() != null) {
            messagingTemplate.convertAndSendToUser(
                    indicator.getReceiverId().toString(),
                    "/queue/typing",
                    indicator
            );
        }
    }

    // User online status
    @MessageMapping("/user/online")
    public void userOnline(@Payload UserPresence presence, StompHeaderAccessor headerAccessor) {
        if (presence.getUserId() != null) {
            onlineUsers.add(presence.getUserId());
            // Broadcast to all connected users
            messagingTemplate.convertAndSend(
                    "/topic/user-status",
                    new UserPresence(presence.getUserId(), presence.getUserName(), true, System.currentTimeMillis())
            );
        }
    }

    // User offline status
    @MessageMapping("/user/offline")
    public void userOffline(@Payload UserPresence presence) {
        if (presence.getUserId() != null) {
            onlineUsers.remove(presence.getUserId());
            // Broadcast to all connected users
            messagingTemplate.convertAndSend(
                    "/topic/user-status",
                    new UserPresence(presence.getUserId(), presence.getUserName(), false, System.currentTimeMillis())
            );
        }
    }

    // REST endpoints for status checking
    @GetMapping("/online-users")
    @ResponseBody
    public Set<Integer> getOnlineUsers() {
        return onlineUsers;
    }

    @PostMapping("/check-online/{userId}")
    @ResponseBody
    public boolean isUserOnline(@PathVariable Integer userId) {
        return onlineUsers.contains(userId);
    }
}
