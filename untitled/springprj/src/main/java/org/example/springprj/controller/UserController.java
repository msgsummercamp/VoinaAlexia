package org.example.springprj.controller;
import org.example.springprj.model.User;
import org.example.springprj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) @Min(1) Long minId) {
        logger.info("GET /users called with minId={}", minId);
        List<User> users = userService.getAllUsers();
        if (minId != null) {
            users = users.stream()
                    .filter(u -> u.getId() >= minId)
                    .toList();
            logger.debug("Filtered users with minId >= {}", minId);
        }
        return users;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        logger.info("POST /users called with name: {}", user.getName());
        return userService.saveUser(user);
    }
}
