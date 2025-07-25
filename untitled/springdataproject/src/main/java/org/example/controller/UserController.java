package org.example.controller;

import org.example.dto.UserRequest;
import org.example.model.User;
import org.example.service.UserService;
import org.example.dto.UserRequest;
import org.example.dto.UserPatchRequest;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }


    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest userRequest) {
        User created = userService.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        boolean exists = userService
                .getAllUsers(Pageable.unpaged())
                .stream()
                .anyMatch(u -> u.getId().equals(id));

        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User updated = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean exists = userService
                .getAllUsers(Pageable.unpaged())
                .stream()
                .anyMatch(u -> u.getId().equals(id));

        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<User> partialUpdateUser(@PathVariable Long id, @RequestBody UserPatchRequest patchRequest) {
        Optional<User> userOpt = userService.getAllUsers(Pageable.unpaged())
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = userOpt.get();

        if (patchRequest.getEmail() != null) existingUser.setEmail(patchRequest.getEmail());
        if (patchRequest.getPassword() != null) existingUser.setPassword(patchRequest.getPassword());
        if (patchRequest.getFirstname() != null) existingUser.setFirstname(patchRequest.getFirstname());
        if (patchRequest.getLastname() != null) existingUser.setLastname(patchRequest.getLastname());
        if (patchRequest.getUsername() != null) existingUser.setUsername(patchRequest.getUsername());

        UserRequest updatedRequest = new UserRequest();
        updatedRequest.setUsername(existingUser.getUsername());
        updatedRequest.setEmail(existingUser.getEmail());
        updatedRequest.setPassword(existingUser.getPassword());
        updatedRequest.setFirstname(existingUser.getFirstname());
        updatedRequest.setLastname(existingUser.getLastname());

        return ResponseEntity.ok(userService.updateUser(id, updatedRequest));
    }

}

