package user.data.repository;

import user.data.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements UserRepositoryInterface {

    private List<User> users = new ArrayList<>();

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void save(User user) {
        users.add(user);
    }

    @Override

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findByUsername(String username) {
        return users.stream().filter(u -> u.getUserName().equals(username)).findFirst();
    }

    public void clearAll() {
        users.clear();
    }


}
