package com.onbank.users.application.service;

import com.onbank.users.application.dto.UserDto;
import com.onbank.users.application.mapper.UserMapper;
import com.onbank.users.domain.model.User;
import com.onbank.users.domain.ports.UserPersistencePort;
import com.onbank.users.util.UtilMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserPersistencePort userPort;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetAll() {
        var userList = Collections.singletonList(UtilMapper.fromJsonString(UtilMapper.RESPONSE, User.class));
        var userDtoList = Collections.singletonList(UtilMapper.fromJsonString(UtilMapper.RESPONSE, UserDto.class));

        when(userPort.getAll()).thenReturn(Flux.fromIterable(userList));

        Flux<UserDto> result = userService.getAll();

        StepVerifier.create(result)
                .expectNextSequence(userDtoList)
                .verifyComplete();
    }

    @Test
    void testExistUser() {
        String username = "the_dev";

        when(userPort.existUser(username)).thenReturn(Mono.just(true));

        Mono<Boolean> result = userService.existUser(username);

        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void testCreate() {
        var user = UtilMapper.fromJsonString(UtilMapper.RESPONSE, User.class);
        var userDto = UtilMapper.fromJsonString(UtilMapper.RESPONSE, UserDto.class);

        when(userPort.create(user)).thenReturn(Mono.just(user));

        Mono<UserDto> result = userService.create(userDto);

        StepVerifier.create(result)
                .expectNext(userDto)
                .verifyComplete();
    }
}
