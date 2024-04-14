package org.example.weblfux.post.service;


import lombok.RequiredArgsConstructor;
import org.example.weblfux.post.controller.PostCreateRequest;
import org.example.weblfux.post.domain.Post;
import org.example.weblfux.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Mono<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }

    public Mono<Post> save(PostCreateRequest postCreateRequest) {
        return postRepository.save(Post.builder()
                .title(postCreateRequest.getTitle())
                .content(postCreateRequest.getContent())
                .userId(postCreateRequest.getUserId())
                .build());
    }

    public Flux<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }
}
