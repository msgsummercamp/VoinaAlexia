package org.example.springprj.repository;

import org.example.springprj.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserRepository implements IUserRepository {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final List<User> users = new ArrayList<>();

    public InMemoryUserRepository() {
        users.add(new User(1L, "Alice"));
        users.add(new User(2L, "Bob"));
        users.add(new User(3L, "Charlie"));
    }

    @Override
    public List<User> findAll() {
        logger.debug("Retrieving users list");
        return users;
    }

    @Override
    public User save(User user) {
        logger.debug("Adding user: {}", user);
        users.add(user);
        return user;
    }
}
