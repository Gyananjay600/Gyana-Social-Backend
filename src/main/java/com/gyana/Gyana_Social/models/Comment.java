package com.gyana.Gyana_Social.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;
    private LocalDateTime createdAt;

    // Many comments belong to one user
    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToMany
    private final List<User> liked = new ArrayList<>();

    // Many comments belong to one post
    @ManyToOne
    @JsonIgnore
    private Post post;

    public Comment() {}

    public Comment(Integer id, String content, LocalDateTime createdAt, User user, Post post) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<User> getLiked() {
        return liked;
    }

    public void addLike(User user) {
        if (!this.liked.contains(user)) {
            this.liked.add(user);
        }
    }

    public void removeLike(User user) {
        this.liked.remove(user);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
