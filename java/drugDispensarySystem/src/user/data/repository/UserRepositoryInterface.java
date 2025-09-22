package user.data.repository;

import user.data.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryInterface {

    void save(User user);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    void clearAll();
}
