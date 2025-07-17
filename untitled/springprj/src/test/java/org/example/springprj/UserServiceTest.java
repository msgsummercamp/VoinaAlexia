package org.example.springprj;
import org.example.springprj.model.User;
import org.example.springprj.repository.IUserRepository;
import org.example.springprj.service.UserService;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void getAllUsers() {
        IUserRepository mockRepo = mock(IUserRepository.class);
        List<User> mockUsers = Arrays.asList(
                new User(1L, "Alice"),
                new User(2L, "Bob")
        );
        when(mockRepo.findAll()).thenReturn(mockUsers);
        UserService service = new UserService(mockRepo);
        List<User> users = service.getAllUsers();
        assertEquals(2, users.size());
        verify(mockRepo, times(1)).findAll();
    }

    @Test
    void saveUser() {
        IUserRepository mockRepo = mock(IUserRepository.class);
        User user = new User(3L, "Charlie");
        when(mockRepo.save(user)).thenReturn(user);
        UserService service = new UserService(mockRepo);
        User saved = service.saveUser(user);
        assertEquals("Charlie", saved.getName());
        verify(mockRepo, times(1)).save(user);
    }
}
