package org.example.weblfux.post.controller;


import lombok.Builder;
import lombok.Getter;
import org.example.weblfux.post.domain.Post;
import org.example.weblfux.user.domain.User;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private final Long id;
    private final User user;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public PostResponse(Long id, User user, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .user(post.getUser())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreateAt())
                .updatedAt(post.getUpdateAt())
                .build();
    }
}
