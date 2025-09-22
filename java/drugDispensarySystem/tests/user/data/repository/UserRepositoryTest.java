package user.data.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.data.model.User;
import user.data.repository.UserRepository;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    UserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new UserRepository();
    }


    @Test
    void saveUser_userCountIsOne() {
        User user = new User();
        assertEquals(0, repository.findAll().size());
        repository.save(user);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void saveTwoUsers_userCountIsTwo() {
        User user1 = new User();
        User user2 = new User();
        assertEquals(0, repository.findAll().size());
        repository.save(user1);
        assertEquals(1, repository.findAll().size());
        repository.save(user2);
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void findByUsername_userDoesNotExist_returnsEmpty() {
        repository.save(new User("alice", "moFlaj", "MAlay23$$", "1"));
        Optional<User> result = repository.findByUsername("charlie");
        assertFalse(result.isPresent());
    }

    @Test
    void findByUsername_userExists_returnsUser() {
        repository.save(new User("alice", "moFlaj", "MAlay23$$", "1"));
        Optional<User> result = repository.findByUsername("moFlaj");
        assertTrue(result.isPresent());
    }

}
