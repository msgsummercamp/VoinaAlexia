package org.example.springprj.repository;

import org.example.springprj.model.User;
import java.util.List;

public interface IUserRepository {
    List<User> findAll();
    User save(User user);
}
