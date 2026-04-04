package com.gyana.Gyana_Social.service;

import java.util.List;

import com.gyana.Gyana_Social.models.ChatMessage;

public interface ChatMessageService {

    ChatMessage sendMessage(ChatMessage message, Integer senderId, Integer receiverId) throws Exception;

    String deleteMessage(Integer messageId, Integer userId) throws Exception;

    ChatMessage findMessageById(Integer messageId) throws Exception;

    List<ChatMessage> findConversation(Integer userId1, Integer userId2);

    List<ChatMessage> findAllMessagesForUser(Integer userId);

    List<ChatMessage> findUnreadMessages(Integer userId);

    Integer countUnreadMessages(Integer userId);

    ChatMessage markAsRead(Integer messageId) throws Exception;

    ChatMessage editMessage(Integer messageId, String newContent, Integer userId) throws Exception;
}
