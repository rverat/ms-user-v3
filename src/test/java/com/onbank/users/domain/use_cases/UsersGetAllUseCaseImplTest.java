package com.onbank.users.domain.use_cases;

import com.onbank.users.domain.model.User;
import com.onbank.users.domain.ports.UserPersistencePort;
import com.onbank.users.util.UtilMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersGetAllUseCaseImplTest {

    @Mock
    private UserPersistencePort userPort;

    @InjectMocks
    private UsersGetAllUseCaseImpl useCase;

    @Test
    void testGetAllUsers() {

        var userList = Collections.singletonList(UtilMapper.fromJsonString(UtilMapper.RESPONSE, User.class));

        when(userPort.getAll()).thenReturn(Flux.fromIterable(userList));

        Flux<User> result = useCase.getAll();

        StepVerifier.create(result)
                .expectNextSequence(userList)
                .verifyComplete();
    }
}
