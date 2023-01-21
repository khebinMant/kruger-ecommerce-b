package krugers.microservicio.auth.authmicroservice.service.user;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.netflix.discovery.converters.Auto;

import krugers.microservicio.auth.authmicroservice.dto.LoginRequest;
import krugers.microservicio.auth.authmicroservice.dto.LoginResponse;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.entity.TokenDto;
import krugers.microservicio.auth.authmicroservice.repository.UserRepository;
import krugers.microservicio.auth.authmicroservice.security.JwtProvider;
import krugers.microservicio.auth.authmicroservice.service.address.AddressServiceImpl;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AddressServiceImpl addressServiceImpl;

    @Autowired
    JwtProvider jwtProvider;

    public User save(User authUser) {
        Optional<User> user = userRepository.findByEmail(authUser.getEmail());
        if (user.isPresent()) {
            return null;
        }
        String password = passwordEncoder.encode(authUser.getPassword());
        authUser.setPassword(password);
        authUser.setVerified(false);
        
        Long customerId;
        User userDB =  new User();
        userDB = userRepository.save(authUser);
        customerId = userDB.getId();

        if(!authUser.getAddresses().isEmpty()){
            authUser.getAddresses().forEach(userAddress->{
                addressServiceImpl.createAddress(userAddress, customerId);
            });
        }

        return userRepository.save(userDB);
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
            userDB.setAddresses(user.getAddresses());
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
