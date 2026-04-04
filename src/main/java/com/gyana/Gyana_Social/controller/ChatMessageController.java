package com.gyana.Gyana_Social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyana.Gyana_Social.Response.ApiResponse;
import com.gyana.Gyana_Social.models.ChatMessage;
import com.gyana.Gyana_Social.models.User;
import com.gyana.Gyana_Social.service.ChatMessageService;
import com.gyana.Gyana_Social.service.UserService;

@RestController
@RequestMapping("/api/messages")
public class ChatMessageController {

    @Autowired
    ChatMessageService chatMessageService;

    @Autowired
    private UserService userService;

    @PostMapping("/send/{receiverId}")
    public ResponseEntity<ChatMessage> sendMessage(@PathVariable Integer receiverId,
                                                    @RequestHeader("Authorization") String jwt,
                                                    @RequestBody ChatMessage message) throws Exception {
        User sender = userService.findUserByJwt(jwt);
        ChatMessage sentMessage = chatMessageService.sendMessage(message, sender.getId(), receiverId);
        return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<ApiResponse> deleteMessage(@PathVariable Integer messageId,
                                                     @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        String response = chatMessageService.deleteMessage(messageId, user.getId());
        ApiResponse res = new ApiResponse(response, true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<ChatMessage> getMessageById(@PathVariable Integer messageId,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        ChatMessage message = chatMessageService.findMessageById(messageId);
        
        // Verify user is either sender or receiver
        if (!message.getSender().getId().equals(reqUser.getId()) && 
            !message.getReceiver().getId().equals(reqUser.getId())) {
            throw new Exception("You are not authorized to view this message");
        }
        
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/conversation/{userId}")
    public ResponseEntity<List<ChatMessage>> getConversation(@PathVariable Integer userId,
                                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        List<ChatMessage> conversation = chatMessageService.findConversation(reqUser.getId(), userId);
        return new ResponseEntity<>(conversation, HttpStatus.OK);
    }

    @GetMapping("/inbox")
    public ResponseEntity<List<ChatMessage>> getInbox(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<ChatMessage> messages = chatMessageService.findAllMessagesForUser(user.getId());
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/unread")
    public ResponseEntity<List<ChatMessage>> getUnreadMessages(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<ChatMessage> unreadMessages = chatMessageService.findUnreadMessages(user.getId());
        return new ResponseEntity<>(unreadMessages, HttpStatus.OK);
    }

    @GetMapping("/unread/count")
    public ResponseEntity<Integer> getUnreadCount(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Integer count = chatMessageService.countUnreadMessages(user.getId());
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PutMapping("/{messageId}/read")
    public ResponseEntity<ChatMessage> markAsRead(@PathVariable Integer messageId,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        ChatMessage message = chatMessageService.findMessageById(messageId);
        
        // Verify user is the receiver of the message
        if (!message.getReceiver().getId().equals(reqUser.getId())) {
            throw new Exception("You can only mark messages sent to you as read");
        }
        
        message = chatMessageService.markAsRead(messageId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{messageId}/edit")
    public ResponseEntity<ChatMessage> editMessage(@PathVariable Integer messageId,
                                                   @RequestHeader("Authorization") String jwt,
                                                   @RequestBody ChatMessage updatedMessage) throws Exception {
        User user = userService.findUserByJwt(jwt);
        ChatMessage message = chatMessageService.editMessage(messageId, updatedMessage.getContent(), user.getId());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
