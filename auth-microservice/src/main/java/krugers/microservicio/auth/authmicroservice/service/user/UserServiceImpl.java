package krugers.microservicio.auth.authmicroservice.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import krugers.microservicio.auth.authmicroservice.dto.ChangeCredentialsRequest;
import krugers.microservicio.auth.authmicroservice.dto.ChangeCredentialsResponse;
import krugers.microservicio.auth.authmicroservice.dto.LoginRequest;
import krugers.microservicio.auth.authmicroservice.dto.LoginResponse;
import krugers.microservicio.auth.authmicroservice.entity.Address;
import krugers.microservicio.auth.authmicroservice.entity.Role;
import krugers.microservicio.auth.authmicroservice.entity.TokenDto;
import krugers.microservicio.auth.authmicroservice.entity.User;
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
		authUser.setCompanyId(1L);
		if(authUser.getRole().equals(Role.ADMIN)){
			authUser.setVerified(true);
		}

		Long customerId;
		User userDB = new User();
		userDB = userRepository.save(authUser);
		customerId = userDB.getId();

		if (!authUser.getAddresses().isEmpty()) {
			authUser.getAddresses().forEach(userAddress -> {
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
			LoginResponse response = LoginResponse.builder().id(user.get().getId()).email(user.get().getEmail())
					.password(user.get().getPassword())
					.birthDate(user.get().getBirthDate()).firstName(user.get().getFirstName())
					.lastName(user.get().getLastName()).cellPhone(user.get().getCellPhone())
					.companyId(user.get().getCompanyId()).signDate(user.get().getSignDate())
					.verified(user.get().getVerified()).role(user.get().getRole())
					.imageUrl(user.get().getImageUrl())
					.addresses(user.get().getAddresses())
					.token(jwtProvider.createToken(user.get())).build();
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

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}

	@Override
	public User updateUserPersonalInfo(Long userId, User user) {
		User userDB = findById(userId);
		if (user != null && userDB != null) {
			userDB.setFirstName(user.getFirstName());
			userDB.setLastName(user.getLastName());
			userDB.setImageUrl(user.getImageUrl());
			userDB.setBirthDate(user.getBirthDate());
			userDB.setCellPhone(user.getCellPhone());
			User userUpdated = userRepository.save(userDB);
			System.out.println("---------------------------------------------------");
			return userUpdated;
		} else
			return null;
	}

	@Override
	public User updateUserUbication(Long userId, User user) {
		User userDB = findById(userId);
		if (user != null && userDB != null && user.getAddresses() != null && !user.getAddresses().isEmpty()) {
			List<Address> newAddresses=new ArrayList<>();
			for(Address address:user.getAddresses()) {
				newAddresses.add(address);
				address.setUserId(userDB.getId());
			}
			userDB.setAddresses(newAddresses);
			User userUpdated = userRepository.save(userDB);
			return userUpdated;
		} else
			return null;
	}

	@Override
	public ChangeCredentialsResponse updateUserCredentials(Long userId, ChangeCredentialsRequest req) {
		User userDB = findById(userId);
		if (userDB != null) {
			if( userDB.getEmail().equals(req.getEmail())) {
				if (passwordEncoder.matches(req.getOldPassword(), userDB.getPassword())) {
					String newPassword = passwordEncoder.encode(req.getNewPassword());
					userDB.setPassword(newPassword);
					User userUpdated = userRepository.save(userDB);
					return ChangeCredentialsResponse.CHANGED;
				}else {
					return ChangeCredentialsResponse.WRONG_OLD_PASSWORD;
				}
			}
			return ChangeCredentialsResponse.EMAIL_DOES_NOT_MATCH;
			

		} else
			return ChangeCredentialsResponse.USER_ID_NOT_FOUND;
	}

	/*
	 * public User updateUser(Long userId, User user) { User userDB =
	 * findById(userId); String password =
	 * passwordEncoder.encode(user.getPassword());
	 * 
	 * if (userDB != null) { userDB.setFirstName(user.getFirstName());
	 * userDB.setLastName(user.getLastName());
	 * userDB.setUserName(user.getUserName());
	 * userDB.setVerified(user.getVerified());
	 * userDB.setImageUrl(user.getImageUrl());
	 * userDB.setBirthDate(user.getBirthDate());
	 * userDB.setCellPhone(user.getCellPhone());
	 * userDB.setAddresses(user.getAddresses()); userDB.setEmail(user.getEmail());
	 * userDB.setPassword(password); User userUpdated = userRepository.save(userDB);
	 * return userUpdated; } return null; }
	 */
}
