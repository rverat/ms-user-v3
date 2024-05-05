package com.onbank.users.infrastructure.persistence;

import com.onbank.users.domain.model.User;
import com.onbank.users.infrastructure.persistence.adapter.UserAdapter;
import com.onbank.users.infrastructure.persistence.model.UserEntity;
import com.onbank.users.infrastructure.persistence.repository.UserRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAdapterTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserAdapter adapter;

    @Test
    void testGetAllUsers() {

        var entityList = Arrays.asList(UtilMapper.fromJsonString(UtilMapper.RESPONSE, UserEntity.class));
        var userListEspected = Arrays.asList(UtilMapper.fromJsonString(UtilMapper.RESPONSE, User.class));

        when(repository.findAll()).thenReturn(Flux.fromIterable(entityList));

        Flux<User> result = adapter.getAll();

        StepVerifier.create(result)
                .expectNextSequence(userListEspected)
                .verifyComplete();
    }

    @Test
    void testExistUser() {

        String userName = "the_dev";

        when(repository.findByUsername(userName)).thenReturn(Mono.just(true));

        Mono<Boolean> result = adapter.existUser(userName);

        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void testCreateUser() {

        var userToCreate = UtilMapper.fromJsonString(UtilMapper.RESPONSE, User.class);
        var createdUser = UtilMapper.fromJsonString(UtilMapper.RESPONSE, UserEntity.class);

        when(repository.save(any())).thenReturn(Mono.just(createdUser));

        Mono<User> result = adapter.create(userToCreate);

        StepVerifier.create(result)
                .expectNext(userToCreate)
                .verifyComplete();
    }
}
