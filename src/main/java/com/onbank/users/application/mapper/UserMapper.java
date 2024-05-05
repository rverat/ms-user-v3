package com.onbank.users.application.mapper;

import com.onbank.users.application.dto.UserDto;
import com.onbank.users.domain.model.User;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class UserMapper {
    public static Mono<UserDto> toDto(Mono<User> userMono) {
        return userMono.map(user -> {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setPassword(user.getPassword());
            dto.setEmail(user.getEmail());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setCreatedAt(user.getCreatedAt());
            dto.setUpdatedAt(user.getUpdatedAt());
            dto.setEnabled(user.isEnabled());
            return dto;
        });
    }

    public static Mono<User> toEntity(Mono<UserDto> dtoMono) {
        return dtoMono.map(dto -> {
            User user = new User();
            user.setId(dto.getId());
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            user.setEmail(dto.getEmail());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setCreatedAt(dto.getCreatedAt());
            user.setUpdatedAt(dto.getUpdatedAt());
            user.setEnabled(dto.isEnabled());
            return user;
        });
    }
}
