package com.onbank.users.application.service;

import com.onbank.users.application.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<UserDto> getAll();

    Mono<Boolean> existUser(String userName);

    Mono<UserDto> create(UserDto user);

}
