package com.onbank.users.infrastructure.rest;

import com.onbank.users.application.service.UserService;
import com.onbank.users.infrastructure.rest.dto.UserRq;
import com.onbank.users.infrastructure.rest.dto.UserRs;
import com.onbank.users.infrastructure.rest.mapper.UserRqMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/v1/api/user")
public class UserController {
    private final UserService service;

    @PostMapping("/create")
    public Mono<UserRs> create(@RequestBody UserRq userRq) {
        return service.create(UserRqMapper.toDto(userRq))
                .flatMap(userDto -> Mono.just(UserRqMapper.toUserRs(userDto)));
    }

    @GetMapping("/getAll")
    public Flux<UserRs> getAll() {
        return service.getAll()
                .flatMap(userDto -> Mono.just(UserRqMapper.toUserRs(userDto)));
    }

}
