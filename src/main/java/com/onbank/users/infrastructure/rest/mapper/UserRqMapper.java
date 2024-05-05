package com.onbank.users.infrastructure.rest.mapper;

import com.onbank.users.application.dto.UserDto;
import com.onbank.users.infrastructure.rest.dto.UserRq;
import com.onbank.users.infrastructure.rest.dto.UserRs;

import java.time.LocalDateTime;

public class UserRqMapper {
    public static UserDto toDto(UserRq user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setCreatedAt(LocalDateTime.now());
        dto.setEnabled(user.isEnabled());
        return dto;
    }

    public static UserRs toUserRs(UserDto dto) {
        UserRs userRs = new UserRs();
        userRs.setId(dto.getId());
        userRs.setUsername(dto.getUsername());
        userRs.setPassword(dto.getPassword());
        userRs.setEmail(dto.getEmail());
        userRs.setFirstName(dto.getFirstName());
        userRs.setLastName(dto.getLastName());
        userRs.setCreatedAt(dto.getCreatedAt());
        userRs.setUpdatedAt(dto.getUpdatedAt());
        userRs.setEnabled(dto.isEnabled());
        return userRs;
    }
}
