package com.gyana.Gyana_Social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gyana.Gyana_Social.models.Comment;
import com.gyana.Gyana_Social.models.Post;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    
    @Query("select c from Comment c where c.post.id =:postId")
    List<Comment> findCommentByPostId(@Param("postId") Integer postId);

    @Query("select c from Comment c where c.user.id =:userId")
    List<Comment> findCommentByUserId(@Param("userId") Integer userId);

    Integer deleteByPost(Post post);
    
    void deleteByUserId(Integer userId);
}
