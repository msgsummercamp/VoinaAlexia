package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPatchRequest {

    @Size(max = 50, message = "Username must be at most 50 characters")
    private String username;

    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email must be at most 100 characters")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Size(max = 50, message = "Firstname must be at most 50 characters")
    private String firstname;

    @Size(max = 50, message = "Lastname must be at most 50 characters")
    private String lastname;
}
