package cl.lightsoft.demo.controller;

import cl.lightsoft.demo.dto.BaseResponseDTO;
import cl.lightsoft.demo.model.User;
import cl.lightsoft.demo.service.AuthService;
import cl.lightsoft.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping
    public BaseResponseDTO createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return new BaseResponseDTO("OK", "Usuario Creado Exitosamente");
    }

    @PostMapping("/login")
    public BaseResponseDTO login(@Valid @RequestBody User user) {
        String token = authService.login(user);
        return new BaseResponseDTO("OK", "Login exitoso. Token: " + token);
    }

    @GetMapping("/self")
    public BaseResponseDTO getUserData(@RequestHeader("Authorization") String token) {
        User user = authService.getUserByToken(token);
        if (user == null) {
            return new BaseResponseDTO("ERROR", "Token inválido o expirado");
        }
        return new BaseResponseDTO("OK", "Usuario autenticado: " + user.getUsername());
    }
}
