package org.example.weblfux.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.weblfux.user.service.UserService;
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

}
