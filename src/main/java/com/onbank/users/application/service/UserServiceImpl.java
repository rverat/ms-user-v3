package com.onbank.users.application.service;

import com.onbank.users.application.dto.UserDto;
import com.onbank.users.application.mapper.UserMapper;
import com.onbank.users.domain.ports.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserPersistencePort userPort;

    @Override
    public Flux<UserDto> getAll() {
        return userPort.getAll()
                .flatMap(user -> UserMapper.toDto(Mono.just(user)));
    }

    @Override
    public Mono<Boolean> existUser(String userName) {
        return userPort.existUser(userName);
    }

    @Override
    public Mono<UserDto> create(UserDto userDto) {
        return UserMapper.toEntity(Mono.just(userDto))
                .flatMap(userPort::create)
                .flatMap(user -> UserMapper.toDto(Mono.just(user)));
    }
}
