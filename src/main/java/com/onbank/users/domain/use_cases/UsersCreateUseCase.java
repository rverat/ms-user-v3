package com.onbank.users.domain.use_cases;

import com.onbank.users.domain.model.User;
import reactor.core.publisher.Mono;

public interface UsersCreateUseCase {
    Mono<User> createUser(User user);
}
