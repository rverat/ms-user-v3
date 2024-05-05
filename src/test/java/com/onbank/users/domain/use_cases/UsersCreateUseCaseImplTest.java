package com.onbank.users.domain.use_cases;

import com.onbank.users.domain.model.User;
import com.onbank.users.domain.ports.UserPersistencePort;
import com.onbank.users.util.UtilMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersCreateUseCaseImplTest {


    @Mock
    private UserPersistencePort userPort;

    @InjectMocks
    private UsersCreateUseCaseImpl useCase;

    @Test
    void testCreateUser_WhenUserDoesNotExist() {
        var user = UtilMapper.fromJsonString(UtilMapper.RESPONSE, User.class);

        when(userPort.existUser(user.getUsername())).thenReturn(Mono.just(false));
        when(userPort.create(user)).thenReturn(Mono.just(user));

        Mono<User> result = useCase.createUser(user);

        StepVerifier.create(result)
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    void testCreateUser_WhenUserExists() {

        var existingUser = UtilMapper.fromJsonString(UtilMapper.RESPONSE, User.class);

        when(userPort.existUser(existingUser.getUsername())).thenReturn(Mono.just(true));

        Mono<User> result = useCase.createUser(existingUser);

        StepVerifier.create(result)
                .verifyComplete();
    }
}
