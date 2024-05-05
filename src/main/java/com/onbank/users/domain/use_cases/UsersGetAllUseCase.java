package com.onbank.users.domain.use_cases;

import com.onbank.users.domain.model.User;
import reactor.core.publisher.Flux;

public interface UsersGetAllUseCase {
    Flux<User> getAll();

}
