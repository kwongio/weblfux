package org.example.weblfux.post.repository;

import org.example.weblfux.post.domain.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostRepository extends ReactiveCrudRepository<Post, Long> , PostCustomR2dbcRepository{


}
