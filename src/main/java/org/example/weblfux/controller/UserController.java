package org.example.weblfux.controller;

import lombok.RequiredArgsConstructor;
import org.example.weblfux.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public Mono<UserResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest.getName(), userCreateRequest.getEmail())
                .map(UserResponse::fromUser);
    }

    @GetMapping
    public Flux<UserResponse> findAll() {
        return userService.findAll()
                .map(UserResponse::fromUser);
    }

    @GetMapping("/{id}")
    public Mono<UserResponse> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(UserResponse::fromUser);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> deleteById(@PathVariable Long id) {
        return userService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> update(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(id, userUpdateRequest.getName(), userUpdateRequest.getEmail())
                .map(user-> ResponseEntity.ok(UserResponse.fromUser(user)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }
}
