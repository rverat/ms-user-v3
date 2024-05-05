package com.onbank.users.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain alphanumeric characters and underscores")
    private String username;

    @Size(min = 8, max = 125)
    private String password;

    @Size(max = 80)
    @Email
    private String email;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean enabled;

}
