package com.gyana.Gyana_Social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gyana.Gyana_Social.models.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {

    @Query("select m from ChatMessage m where (m.sender.id = :userId1 and m.receiver.id = :userId2) " +
           "or (m.sender.id = :userId2 and m.receiver.id = :userId1) order by m.sentAt asc")
    List<ChatMessage> findConversation(Integer userId1, Integer userId2);

    @Query("select m from ChatMessage m where m.sender.id = :userId or m.receiver.id = :userId order by m.sentAt desc")
    List<ChatMessage> findAllMessagesForUser(Integer userId);

    @Query("select m from ChatMessage m where m.sender.id = :senderId and m.receiver.id = :receiverId order by m.sentAt desc")
    List<ChatMessage> findMessagesBySenderAndReceiver(Integer senderId, Integer receiverId);

    @Query("select m from ChatMessage m where m.receiver.id = :userId and m.isRead = false order by m.sentAt desc")
    List<ChatMessage> findUnreadMessages(Integer userId);

    @Query("select count(m) from ChatMessage m where m.receiver.id = :userId and m.isRead = false")
    Integer countUnreadMessages(Integer userId);

    void deleteByReceiverId(Integer userId);
}
