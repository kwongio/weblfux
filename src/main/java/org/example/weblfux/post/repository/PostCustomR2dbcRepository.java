package org.example.weblfux.post.repository;

import org.example.weblfux.post.domain.Post;
import reactor.core.publisher.Flux;

public interface PostCustomR2dbcRepository {
    Flux<Post> findByUserId(Long userId);
}
