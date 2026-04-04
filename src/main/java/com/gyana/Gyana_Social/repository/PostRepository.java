package com.gyana.Gyana_Social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gyana.Gyana_Social.models.Post;
import com.gyana.Gyana_Social.models.User;

public interface PostRepository extends JpaRepository <Post, Integer> {
    @Query("select p from Post p where p.user.id =:userId")
    List<Post>findPostByUserId(Integer userId);

    Integer user(User user);
    
    void deleteByUserId(Integer userId);
}
