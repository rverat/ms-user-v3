package com.onbank.users.domain.use_cases;

import com.onbank.users.domain.model.User;
import com.onbank.users.domain.ports.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UsersCreateUseCaseImpl implements UsersCreateUseCase {

    private final UserPersistencePort userPort;

    @Override
    public Mono<User> createUser(User user) {
        return userPort.existUser(user.getUsername())
                .filter(exists -> !exists)
                .flatMap(exists -> userPort.create(user));
    }
}
