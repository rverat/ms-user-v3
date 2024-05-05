package com.onbank.users.infrastructure.persistence.repository;

import com.onbank.users.infrastructure.persistence.model.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<UserEntity, Long> {

    Mono<Boolean> findByUsername(String userName);
}
