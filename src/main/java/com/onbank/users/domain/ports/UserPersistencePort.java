package com.onbank.users.domain.ports;

import com.onbank.users.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserPersistencePort {

    Flux<User> getAll();

    Mono<Boolean> existUser(String userName);

    Mono<User> create(User user);

}
