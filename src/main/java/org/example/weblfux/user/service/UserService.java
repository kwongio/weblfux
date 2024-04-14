package org.example.weblfux.user.service;

import lombok.RequiredArgsConstructor;
import org.example.weblfux.user.domain.User;
import org.example.weblfux.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Mono<User> create(String name, String email) {
        return userRepository.save(User.builder()
                .email(email)
                .name(name)
                .build());

    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(Long id) {
        return userRepository.findById(id);
    }


}
