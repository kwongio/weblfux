package org.example.weblfux.post.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.weblfux.user.domain.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("posts")
@NoArgsConstructor
@Getter
public class Post {

    @Id
    private Long id;
    private Long userId;
    private String title;
    private String content;

    @Transient
    private User user;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    @Builder
    public Post(Long id, Long userId, String title, String content, User user, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
