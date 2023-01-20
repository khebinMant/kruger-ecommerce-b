package krugers.microservicio.auth.authmicroservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import krugers.microservicio.auth.authmicroservice.dto.LoginRequest;
import krugers.microservicio.auth.authmicroservice.dto.LoginResponse;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.entity.TokenDto;
import krugers.microservicio.auth.authmicroservice.repository.UserRepository;
import krugers.microservicio.auth.authmicroservice.security.JwtProvider;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    public User save(User authUser) {
        Optional<User> user = userRepository.findByEmail(authUser.getEmail());
        if (user.isPresent()) {
            return null;
        }
        String password = passwordEncoder.encode(authUser.getPassword());
        User authUserCreated = User.builder()
                .email(authUser.getEmail())
                .password(password)
                .userName(authUser.getUserName())
                .firstName(authUser.getUserName())
                .imageUrl(authUser.getImageUrl())
                .companyId(authUser.getCompanyId())
                .cellPhone(authUser.getCellPhone())
                .lastName(authUser.getLastName())
                .signDate(new Date())
                .verified(false)
                .role(authUser.getRole())// poner el resto
                .build();

        return userRepository.save(authUserCreated);
    }

    public LoginResponse login(LoginRequest dto) {
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if (!user.isPresent())
            return null;

        if (passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
            LoginResponse response = LoginResponse.builder()
                    .id(user.get().getId())
                    .email(user.get().getEmail())
                    .password(user.get().getPassword())
                    .userName(user.get().getUserName())
                    .birthDate(user.get().getBirthDate())
                    .firstName(user.get().getFirstName())
                    .lastName(user.get().getLastName())
                    .cellPhone(user.get().getCellPhone())
                    .companyId(user.get().getCompanyId())
                    .signDate(user.get().getSignDate())
                    .verified(user.get().getVerified())
                    .role(user.get().getRole())
                    .token(jwtProvider.createToken(user.get()))
                    .build();
            return response;
        }
        return null;
    }

    public TokenDto validate(String token) {
        if (!jwtProvider.validate(token))
            return null;
        String email = jwtProvider.getEmailFromToken(token);
        if (!userRepository.findByEmail(email).isPresent())
            return null;
        return new TokenDto(token);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User updateUser(Long userId, User user) {
        User userDB = findById(userId);
        String password = passwordEncoder.encode(user.getPassword());

        if (userDB != null) {
            userDB.setFirstName(user.getFirstName());
            userDB.setLastName(user.getLastName());
            userDB.setUserName(user.getUserName());
            userDB.setVerified(user.getVerified());
            userDB.setImageUrl(user.getImageUrl());
            userDB.setBirthDate(user.getBirthDate());
            userDB.setCellPhone(user.getCellPhone());
            userDB.setEmail(user.getEmail());
            userDB.setPassword(password);
            User userUpdated = userRepository.save(userDB);
            return userUpdated;
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
