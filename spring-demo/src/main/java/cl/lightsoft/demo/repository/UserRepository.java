package cl.lightsoft.demo.repository;

import cl.lightsoft.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private final Map<String, User> userMap = new HashMap<>();

    public User findByUsername(String username) {
        return userMap.get(username.toLowerCase());
    }

    public void save(User user) {
        userMap.put(user.getUsername().toLowerCase(), user);
    }
}
