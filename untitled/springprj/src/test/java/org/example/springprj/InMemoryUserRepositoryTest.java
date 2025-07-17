package org.example.springprj;

import org.example.springprj.model.User;
import org.example.springprj.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @Test
    void findAllMocked() {
        IUserRepository repo = mock(IUserRepository.class);
        when(repo.findAll()).thenReturn(Arrays.asList(
                new User(1L, "MockUser")
        ));

        List<User> users = repo.findAll();
        assertEquals(1, users.size());
        assertEquals("MockUser", users.get(0).getName());
        verify(repo, times(1)).findAll();
    }

    @Test
    void saveUserMocked() {
        IUserRepository repo = mock(IUserRepository.class);
        User user = new User(99L, "Test");

        when(repo.save(user)).thenReturn(user);

        User result = repo.save(user);
        assertEquals("Test", result.getName());
        verify(repo, times(1)).save(user);
    }
}
