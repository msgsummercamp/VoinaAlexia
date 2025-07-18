import org.example.model.User;
import org.example.repository.IUserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private IUserRepository repository;

    @InjectMocks
    private UserService userService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUsername() {
        User user = new User(1L, "testuser", "test@example.com", "pass", "John", "Doe");
        when(repository.findByUsername("testuser")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByUsername("testuser");

        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUsername());
    }

    @Test
    void findByEmail() {
        User user = new User(1L, "user", "email@example.com", "pass", "Jane", "Doe");
        when(repository.findByEmail("email@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail("email@example.com");

        assertTrue(result.isPresent());
        assertEquals("email@example.com", result.get().getEmail());
    }

    @Test
    void saveUser() {
        User user = new User(null, "saveuser", "save@example.com", "pass", "Ana", "Pop");
        when(repository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals("saveuser", result.getUsername());
    }

    @Test
    void deleteUser() {
        Long userId = 99L;

        userService.deleteUser(userId);

        verify(repository, times(1)).deleteById(userId);
    }

    @Test
    void getAllUsers() {
        List<User> users = List.of(
                new User(1L, "a", "a@e.com", "p", "a", "a"),
                new User(2L, "b", "b@e.com", "p", "b", "b")
        );
        when(repository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    void updateUser() {
        User user = new User(1L, "updated", "u@e.com", "new", "Up", "Dated");
        when(repository.save(user)).thenReturn(user);

        User result = userService.updateUser(user);

        assertEquals("updated", result.getUsername());
    }

    @Test
    void countAllUsers() {
        when(repository.countUsers()).thenReturn(5L);

        long result = userService.countAllUsers();

        assertEquals(5L, result);
        verify(repository, times(1)).countUsers();
    }
}
