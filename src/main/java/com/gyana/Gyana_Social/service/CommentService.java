package com.gyana.Gyana_Social.service;

import com.gyana.Gyana_Social.models.Comment;
import java.util.List;

public interface CommentService {

    Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;

    String deleteComment(Integer commentId, Integer userId) throws Exception;

    List<Comment> findCommentByPostId(Integer postId);

    List<Comment> findCommentByUserId(Integer userId);

    Comment findCommentById(Integer commentId) throws Exception;

    List<Comment> findAllComments();

    Comment updateComment(Integer commentId, Comment updatedComment, Integer userId) throws Exception;
}
