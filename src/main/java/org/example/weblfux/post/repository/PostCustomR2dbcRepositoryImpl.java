package org.example.weblfux.post.repository;

import lombok.RequiredArgsConstructor;
import org.example.weblfux.post.domain.Post;
import org.example.weblfux.user.domain.User;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class PostCustomR2dbcRepositoryImpl implements PostCustomR2dbcRepository {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<Post> findByUserId(Long userId) {
        String sql = """
                SELECT p.id as post_id, 
                p.title as title, 
                p.content as content, 
                u.id as user_id, 
                u.name as name, 
                u.email as email, 
                u.created_at as user_create_at, 
                u.updated_at as user_update_at
                FROM posts p
                LEFT JOIN users u ON p.user_id = u.id
                WHERE p.user_id = :userId
                """;
        return databaseClient.sql(sql)
                .bind("userId", userId)
                .fetch()
                .all()
                .map(row -> Post.builder()
                        .id((Long) row.get("post_id"))
                        .title((String) row.get("title"))
                        .content((String) row.get("content"))
                        .user(User.builder()
                                .id((Long) row.get("user_id"))
                                .name((String) row.get("name"))
                                .email((String) row.get("email"))
                                .createdAt((LocalDateTime) row.get("user_create_at"))
                                .updatedAt((LocalDateTime) row.get("user_update_at"))
                                .build())
                        .build());
    }
}
