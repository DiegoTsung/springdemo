package cl.lightsoft.demo.service;

import cl.lightsoft.demo.model.User;
import cl.lightsoft.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final Map<String, User> tokenMap = new HashMap<>();


    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(User user) {
        User storedUser = userRepository.findByUsername(user.getUsername());
        if (storedUser == null || !storedUser.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Credenciales incorrectas");
        }

        String token = UUID.randomUUID().toString();
        tokenMap.put(token, storedUser);
        return token;
    }

    public User getUserByToken(String token) {
        return tokenMap.get(token);
    }
}
