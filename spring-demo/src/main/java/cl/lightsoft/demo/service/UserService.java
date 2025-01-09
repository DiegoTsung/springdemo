package cl.lightsoft.demo.service;

import cl.lightsoft.demo.model.User;
import cl.lightsoft.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        System.out.println("Buscando usuario: " + user.getUsername());
        if (userRepository.findByUsername(user.getUsername()) != null) {
            System.out.println("Usuario ya existe: " + user.getUsername());
            throw new IllegalArgumentException("El usuario ya existe");
        }
        System.out.println("Guardando usuario: " + user.getUsername());
        userRepository.save(user);
        System.out.println("Usuario guardado exitosamente.");
    }

}
