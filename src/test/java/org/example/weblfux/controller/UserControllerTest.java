package org.example.weblfux.controller;

import org.example.weblfux.model.User;
import org.example.weblfux.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@WebFluxTest(UserController.class)
@AutoConfigureWebTestClient
class UserControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    void createUser() {
        when(userService.create("greg", "greg@fastcampus.co.kr")).thenReturn(
                Mono.just(new User(1L, "greg", "greg@fastcampus.co.kr"))
        );
        webTestClient.post().uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserCreateRequest("greg", "greg@fastcampus.co.kr"))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(UserResponse.class)
                .value(res -> {
                    assertEquals("greg", res.getName());
                    assertEquals("greg@fastcampus.co.kr", res.getEmail());
                });
    }

    @Test
    void findAll() {
        when(userService.findAll()).thenReturn(
                Flux.just(
                        new User(1L, "greg", "kwon@gmail.com"),
                        new User(1L, "greg", "kwon@gmail.com"),
                        new User(1L, "greg", "kwon@gmail.com")
                )

        );
        webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(UserResponse.class)
                .hasSize(3);
    }

    @Test
    void findById() {
        when(userService.findById(1L)).thenReturn(
                Mono.just(new User(1L, "greg", "kwon@gmail.com")));
        webTestClient.get()
                .uri("/users/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(UserResponse.class)
                .value(res -> {
                    assertEquals("greg", res.getName());
                    assertEquals("kwon@gmail.com", res.getEmail());
                });
    }

    @Test
    void notFoundUser() {
        when(userService.findById(1L)).thenReturn(
                Mono.empty());
        webTestClient.get()
                .uri("/users/1")
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}