package com.gyana.Gyana_Social.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gyana.Gyana_Social.models.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {
    
    @Query("select s from Story s where s.user.id =:userId order by s.createdAt desc")
    List<Story> findStoryByUserId(Integer userId);

    @Query("select s from Story s where s.expiresAt > :currentTime order by s.createdAt desc")
    List<Story> findAllActiveStories(LocalDateTime currentTime);

    void deleteByUserId(Integer userId);

    @Query("delete from Story s where s.expiresAt <= :currentTime")
    void deleteExpiredStories(LocalDateTime currentTime);
}
