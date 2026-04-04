package com.gyana.Gyana_Social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyana.Gyana_Social.models.ChatMessage;
import com.gyana.Gyana_Social.models.User;
import com.gyana.Gyana_Social.repository.ChatMessageRepository;

import jakarta.transaction.Transactional;


@Service
public class ChatMessageServiceImplementation implements ChatMessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Autowired
    UserService userService;


    @Override
    public ChatMessage sendMessage(ChatMessage message, Integer senderId, Integer receiverId) throws Exception {

        User sender = userService.findUserById(senderId);
        User receiver = userService.findUserById(receiverId);

        if (sender.getId().equals(receiver.getId())) {
            throw new Exception("You cannot send a message to yourself!");
        }

        ChatMessage newMessage = new ChatMessage();
        newMessage.setContent(message.getContent());
        newMessage.setSender(sender);
        newMessage.setReceiver(receiver);
        newMessage.setSentAt(LocalDateTime.now());
        newMessage.setIsRead(false);

        return chatMessageRepository.save(newMessage);
    }

    @Override
    public String deleteMessage(Integer messageId, Integer userId) throws Exception {
        ChatMessage message = findMessageById(messageId);
        User user = userService.findUserById(userId);

        // Only sender can delete their message
        if (!message.getSender().getId().equals(user.getId())) {
            throw new Exception("You can only delete your own messages!");
        }

        chatMessageRepository.delete(message);
        return "Message deleted successfully";
    }

    @Override
    public ChatMessage findMessageById(Integer messageId) throws Exception {
        Optional<ChatMessage> opt = chatMessageRepository.findById(messageId);
        if (opt.isEmpty()) {
            throw new Exception("Message not found with id " + messageId);
        }

        return opt.get();
    }

    @Override
    public List<ChatMessage> findConversation(Integer userId1, Integer userId2) {
        return chatMessageRepository.findConversation(userId1, userId2);
    }

    @Override
    public List<ChatMessage> findAllMessagesForUser(Integer userId) {
        return chatMessageRepository.findAllMessagesForUser(userId);
    }

    @Override
    public List<ChatMessage> findUnreadMessages(Integer userId) {
        return chatMessageRepository.findUnreadMessages(userId);
    }

    @Override
    public Integer countUnreadMessages(Integer userId) {
        return chatMessageRepository.countUnreadMessages(userId);
    }

    @Override
    @Transactional
    public ChatMessage markAsRead(Integer messageId) throws Exception {
        ChatMessage message = findMessageById(messageId);
        message.setIsRead(true);
        return chatMessageRepository.save(message);
    }

    @Override
    @Transactional
    public ChatMessage editMessage(Integer messageId, String newContent, Integer userId) throws Exception {
        ChatMessage message = findMessageById(messageId);
        User user = userService.findUserById(userId);

        // Only sender can edit their message
        if (!message.getSender().getId().equals(user.getId())) {
            throw new Exception("You can only edit your own messages!");
        }

        if (newContent == null || newContent.trim().isEmpty()) {
            throw new Exception("Message content cannot be empty!");
        }

        message.setContent(newContent);
        message.setEditedAt(LocalDateTime.now());

        return chatMessageRepository.save(message);
    }
}
