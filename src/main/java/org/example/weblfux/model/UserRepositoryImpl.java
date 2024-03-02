package org.example.weblfux.model;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final HashMap<Long, User> userHashMap = new HashMap<>();
    private Long autoIncrementId = 1L;

    @Override
    public Mono<User> save(User user) {
        if (user.getId() == null) {
            user.setId(autoIncrementId);

        }
        user.setUpdateAt(LocalDateTime.now());
        userHashMap.put(autoIncrementId++, user);
        return Mono.just(user);
    }

    @Override
    public Flux<User> findAll() {
        return Flux.fromIterable(userHashMap.values());
    }

    @Override
    public Mono<User> findById(Long id) {
        return Mono.justOrEmpty(userHashMap.getOrDefault(id, null));
    }

    @Override
    public Mono<Integer> deleteById(Long id) {
        if(userHashMap.containsKey(id)){
            userHashMap.remove(id);
            return Mono.just(1);
        }
        return Mono.just(0);
    }
}
