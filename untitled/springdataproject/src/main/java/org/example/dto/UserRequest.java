package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Username is mandatory")
    @Size(max = 50, message = "Username must be at most 50 characters")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email must be at most 100 characters")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Firstname is mandatory")
    @Size(max = 50, message = "Firstname must be at most 50 characters")
    private String firstname;

    @NotBlank(message = "Lastname is mandatory")
    @Size(max = 50, message = "Lastname must be at most 50 characters")
    private String lastname;
}
