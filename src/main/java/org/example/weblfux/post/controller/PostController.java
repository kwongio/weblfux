package org.example.weblfux.post.controller;


import lombok.RequiredArgsConstructor;
import org.example.weblfux.post.service.PostService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public Mono<PostResponse> findById(@PathVariable Long postId) {
        return postService.findById(postId)
                .map(PostResponse::from);
    }

    @PostMapping
    public Mono<PostResponse> save(@RequestBody PostCreateRequest postCreateRequest) {
        return postService.save(postCreateRequest).map(PostResponse::from);
    }

    @GetMapping("users/{userId}")
    public Flux<PostResponse> findByUserId(@PathVariable Long userId) {
        return postService.findByUserId(userId)
                .map(PostResponse::from);
    }
}
