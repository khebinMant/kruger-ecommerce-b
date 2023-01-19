package krugers.microservicio.auth.authmicroservice.service;

import java.util.List;

import krugers.microservicio.auth.authmicroservice.dto.LoginRequest;
import krugers.microservicio.auth.authmicroservice.dto.LoginResponse;
import krugers.microservicio.auth.authmicroservice.entity.TokenDto;
import krugers.microservicio.auth.authmicroservice.entity.User;

public interface UserService {
    User save(User authUser);
    LoginResponse login(LoginRequest dto);
    TokenDto validate(String token);

    List<User> findAll();
    User findById(Long userId);
    void deleteUser(Long userId);
    User updateUser (Long userId, User user);
    User findByEmail(String email);
}
