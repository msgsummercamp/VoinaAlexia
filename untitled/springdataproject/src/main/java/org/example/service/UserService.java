package org.example.service;

import org.example.dto.UserRequest;
import org.example.model.User;
import org.example.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository repository;

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User saveUser(UserRequest request) {
        return repository.save(new User(
                null,
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                request.getFirstname(),
                request.getLastname()
        ));
    }


    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public User updateUser(Long id, UserRequest request) {
        return repository.save(new User(
                id,
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                request.getFirstname(),
                request.getLastname()
        ));
    }


    public Page<User> getAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public long countAllUsers() {
        return repository.countUsers();
    }

}

