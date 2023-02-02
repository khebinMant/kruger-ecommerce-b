package krugers.microservicio.auth.authmicroservice.service.user;

import java.util.List;

import krugers.microservicio.auth.authmicroservice.dto.ChangeCredentialsRequest;
import krugers.microservicio.auth.authmicroservice.dto.ChangeCredentialsResponse;
import krugers.microservicio.auth.authmicroservice.dto.LoginRequest;
import krugers.microservicio.auth.authmicroservice.dto.LoginResponse;
import krugers.microservicio.auth.authmicroservice.dto.PasswordRecoveryRequest;
import krugers.microservicio.auth.authmicroservice.entity.TokenDto;
import krugers.microservicio.auth.authmicroservice.entity.User;

public interface UserService {
    User save(User authUser);
    LoginResponse login(LoginRequest dto);
    TokenDto validate(String token);

    List<User> findAll();
    User findById(Long userId);
    void deleteUser(Long userId);
    User updateUserPersonalInfo(Long userId,User user);
    User updateUserUbication(Long userId,User user);
    User updateUserVerified(Long userId, User user);
    ChangeCredentialsResponse updateUserCredentials(Long userId,ChangeCredentialsRequest req);
    User findByEmail(String email);
    List<User> findAllCustomers();
    
    
    void sendRecoveryCode (String email);
    boolean validateRecoverycode (PasswordRecoveryRequest request);
    boolean resetNewPassword(ChangeCredentialsRequest request);
    
}
