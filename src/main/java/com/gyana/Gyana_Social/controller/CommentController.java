package com.gyana.Gyana_Social.controller;

import com.gyana.Gyana_Social.Response.ApiResponse;
import com.gyana.Gyana_Social.models.Comment;
import com.gyana.Gyana_Social.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/api/comments/post/{postId}/user/{userId}")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, 
                                                  @PathVariable Integer postId, 
                                                  @PathVariable Integer userId) throws Exception {
        Comment createdComment = commentService.createComment(comment, postId, userId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/comments/{commentId}/user/{userId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId, 
                                                      @PathVariable Integer userId) throws Exception {
        String message = commentService.deleteComment(commentId, userId);
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<Comment> findCommentById(@PathVariable Integer commentId) throws Exception {
        Comment comment = commentService.findCommentById(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/api/comments/post/{postId}")
    public ResponseEntity<List<Comment>> findCommentByPostId(@PathVariable Integer postId) {
        List<Comment> comments = commentService.findCommentByPostId(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/api/comments/user/{userId}")
    public ResponseEntity<List<Comment>> findCommentByUserId(@PathVariable Integer userId) {
        List<Comment> comments = commentService.findCommentByUserId(userId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/api/comments")
    public ResponseEntity<List<Comment>> findAllComments() {
        List<Comment> comments = commentService.findAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PutMapping("/api/comments/{commentId}/user/{userId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer commentId, 
                                                  @RequestBody Comment updatedComment, 
                                                  @PathVariable Integer userId) throws Exception {
        Comment comment = commentService.updateComment(commentId, updatedComment, userId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
