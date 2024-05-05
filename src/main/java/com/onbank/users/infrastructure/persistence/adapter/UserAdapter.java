package com.onbank.users.infrastructure.persistence.adapter;

import com.onbank.users.domain.model.User;
import com.onbank.users.domain.ports.UserPersistencePort;
import com.onbank.users.infrastructure.persistence.mapper.UserPersistenceMapper;
import com.onbank.users.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserAdapter implements UserPersistencePort {

    private final UserRepository respository;

    @Override
    public Flux<User> getAll() {
        return respository.findAll()
                .flatMap(userEntity -> Mono.just(
                        UserPersistenceMapper.toModel(userEntity)));
    }

    @Override
    public Mono<Boolean> existUser(String userName) {
        return respository.findByUsername(userName);
    }

    @Override
    public Mono<User> create(User user) {
        return respository.save(UserPersistenceMapper.toEntity(user))
                .flatMap(userFromDB -> Mono.just(
                        UserPersistenceMapper.toModel(userFromDB)));
    }
}
