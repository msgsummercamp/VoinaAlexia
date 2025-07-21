import org.example.dto.UserRequest;
import org.example.model.User;
import org.example.repository.IUserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("saveuser");
        userRequest.setEmail("save@example.com");
        userRequest.setPassword("pass123");
        userRequest.setFirstname("Ana");
        userRequest.setLastname("Pop");

        User expectedUser = new User(null, "saveuser", "save@example.com", "pass123", "Ana", "Pop");

        when(repository.save(any(User.class))).thenReturn(expectedUser);

        User result = userService.saveUser(userRequest);

        assertNotNull(result);
        assertEquals("saveuser", result.getUsername());
        assertEquals("save@example.com", result.getEmail());
        verify(repository, times(1)).save(any(User.class));
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
                new User(1L, "alice", "alice@example.com", "pass", "Alice", "A"),
                new User(2L, "bob", "bob@example.com", "pass", "Bob", "B")
        );
        Pageable pageable = PageRequest.of(0, 2);
        Page<User> userPage = new PageImpl<>(users);

        when(repository.findAll(pageable)).thenReturn(userPage);

        Page<User> result = userService.getAllUsers(pageable);

        assertEquals(2, result.getTotalElements());
        assertEquals("alice", result.getContent().get(0).getUsername());
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void updateUser() {
        Long id = 1L;
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("updated");
        userRequest.setEmail("u@e.com");
        userRequest.setPassword("newpass");
        userRequest.setFirstname("Up");
        userRequest.setLastname("Dated");

        User expectedUser = new User(id, "updated", "u@e.com", "newpass", "Up", "Dated");

        when(repository.save(any(User.class))).thenReturn(expectedUser);

        User result = userService.updateUser(id, userRequest);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("updated", result.getUsername());
        assertEquals("u@e.com", result.getEmail());
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void countAllUsers() {
        when(repository.countUsers()).thenReturn(5L);

        long result = userService.countAllUsers();

        assertEquals(5L, result);
        verify(repository, times(1)).countUsers();
    }
}
