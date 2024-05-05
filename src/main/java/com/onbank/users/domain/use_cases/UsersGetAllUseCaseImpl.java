package com.onbank.users.domain.use_cases;

import com.onbank.users.domain.model.User;
import com.onbank.users.domain.ports.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class UsersGetAllUseCaseImpl implements UsersGetAllUseCase {

    private final UserPersistencePort userPort;

    @Override
    public Flux<User> getAll() {
        return userPort.getAll();
    }
}
