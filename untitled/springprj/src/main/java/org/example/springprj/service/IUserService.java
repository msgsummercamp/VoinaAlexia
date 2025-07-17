package org.example.springprj.service;

import org.example.springprj.model.User;
import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User saveUser(User user);
}
