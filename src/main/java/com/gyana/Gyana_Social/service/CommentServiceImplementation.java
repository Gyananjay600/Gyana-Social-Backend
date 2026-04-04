package com.gyana.Gyana_Social.service;

import com.gyana.Gyana_Social.models.Comment;
import com.gyana.Gyana_Social.models.Post;
import com.gyana.Gyana_Social.models.User;
import com.gyana.Gyana_Social.repository.CommentRepository;
import com.gyana.Gyana_Social.repository.PostRepository;
import com.gyana.Gyana_Social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with id: " + userId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new Exception("Post not found with id: " + postId));

        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    @Override
    public String deleteComment(Integer commentId, Integer userId) throws Exception {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception("Comment not found with id: " + commentId));

        if (!comment.getUser().getId().equals(userId)) {
            throw new Exception("You are not authorized to delete this comment");
        }

        commentRepository.deleteById(commentId);
        return "Comment deleted successfully";
    }

    @Override
    public List<Comment> findCommentByPostId(Integer postId) {
        return commentRepository.findCommentByPostId(postId);
    }

    @Override
    public List<Comment> findCommentByUserId(Integer userId) {
        return commentRepository.findCommentByUserId(userId);
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception("Comment not found with id: " + commentId));
    }

    @Override
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment updateComment(Integer commentId, Comment updatedComment, Integer userId) throws Exception {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception("Comment not found with id: " + commentId));

        if (!comment.getUser().getId().equals(userId)) {
            throw new Exception("You are not authorized to update this comment");
        }

        comment.setContent(updatedComment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }
}
