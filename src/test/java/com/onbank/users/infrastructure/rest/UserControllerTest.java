package com.onbank.users.infrastructure.rest;

import com.onbank.users.application.dto.UserDto;
import com.onbank.users.application.service.UserService;
import com.onbank.users.infrastructure.rest.dto.UserRq;
import com.onbank.users.infrastructure.rest.dto.UserRs;
import com.onbank.users.util.UtilMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    @Test
    void testCreateUser() {
        var rq = UtilMapper.fromJsonString(UtilMapper.REQUEST, UserRq.class);
        var rs = UtilMapper.fromJsonString(UtilMapper.RESPONSE, UserRs.class);
        var dto = UtilMapper.fromJsonString(UtilMapper.RESPONSE, UserDto.class);

        when(service.create(any(UserDto.class))).thenReturn(Mono.just(dto));

        WebTestClient.bindToController(controller)
                .build()
                .post()
                .uri("/v1/api/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(rq)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(UserRs.class)
                .isEqualTo(rs);
    }

    @Test
    void testGetAllUsers() {
        var rs = Collections.singletonList(UtilMapper.fromJsonString(UtilMapper.RESPONSE, UserRs.class));
        var dto = UtilMapper.fromJsonString(UtilMapper.RESPONSE, UserDto.class);

        when(service.getAll()).thenReturn(Flux.just(dto));

        WebTestClient.bindToController(controller)
                .build()
                .get()
                .uri("/v1/api/user/getAll")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(UserRs.class)
                .isEqualTo(rs);
    }


}
