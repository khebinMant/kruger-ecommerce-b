package krugers.microservicio.auth.authmicroservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import krugers.microservicio.auth.authmicroservice.dto.ChangeCredentialsRequest;
import krugers.microservicio.auth.authmicroservice.dto.ChangeCredentialsResponse;
import krugers.microservicio.auth.authmicroservice.dto.LoginRequest;
import krugers.microservicio.auth.authmicroservice.dto.LoginResponse;
import krugers.microservicio.auth.authmicroservice.dto.PasswordRecoveryRequest;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.service.address.AddressServiceImpl;
import krugers.microservicio.auth.authmicroservice.service.mail.MailService;
import krugers.microservicio.auth.authmicroservice.service.user.UserServiceImpl;
import krugers.microservicio.auth.authmicroservice.entity.Address;
import krugers.microservicio.auth.authmicroservice.entity.TokenDto;
import lombok.extern.slf4j.Slf4j;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import io.swagger.v3.oas.annotations.media.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	AddressServiceImpl addressServiceImpl;
	
	//logear un user
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully operation"),
			@ApiResponse(responseCode = "400", description = "Failed to log in") })
	@Operation(summary = "This endpoint will login the user, the credentials of the user should be passed as a LoginRequest object",
	description = "You should call this endpoint when you want to log a user in")
	@Tag(name = "Login a user with credentials", description = "calling this endpoint will login the user"
			+ "who has the credentials stored in the LoginRequest object")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest dto) {
		LoginResponse response = userServiceImpl.login(dto);
		if (response == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(response);
	}

	@PostMapping("/validate")
	public ResponseEntity<TokenDto> validate(@RequestParam String token) {
		TokenDto tokenDto = userServiceImpl.validate(token);
		if (tokenDto == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(tokenDto);
	}
	
	//enviar codigo para recuperar contrasena
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "SUCCESS"),
			@ApiResponse(responseCode = "400", description = "FAIELD"),
			@ApiResponse(responseCode = "404", description = "WRONG_EMAIL") })
	@Operation(summary = "This endpoint will send a recoveryCode to the user email ", description = "You should call this endpoint whenever you want to get a recoverycode to reset password")
	@Tag(name = "send the recoveryCode to user email", description = "send a recoveryCode to user's email to reset password")
	@GetMapping("/recovery-code/{email}")
	public ResponseEntity sendRecoveryCode (@PathVariable("email") String email) throws MessagingException {
		User user=userServiceImpl.findByEmail(email);
		if(user!=null) {
			userServiceImpl.sendRecoveryCode(email,user);
			return new ResponseEntity("SUCCESS", HttpStatus.OK);
		}else {
			return ResponseEntity.badRequest().build();
		}
		
	}

	//validar el codigo de recuperacion ingresado de parte del usuario
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "SUCCESS"),
			@ApiResponse(responseCode = "400", description = "FAIELD"),
			@ApiResponse(responseCode = "404", description = "WRONG_EMAIL") })
	@Operation(summary = "This endpoint will vaildate thecode ingressed by the user in order to reset password", description = "You should call this endpoint whenever you want validate whether the user typed correct recovery code")
	@Tag(name = "validate the recoveryCode", description = "validate the recoveryCode ingressed by the user")
	@PostMapping("/recovery-code/validate")
	public ResponseEntity validateRecoverycode (@RequestBody PasswordRecoveryRequest request) throws MessagingException {
		User user=userServiceImpl.findByEmail(request.getEmail());
		if(user!=null) {
			return userServiceImpl.validateRecoverycode(request) ?  new ResponseEntity("SUCCESS", HttpStatus.OK) :
				new ResponseEntity("INVALID_CODE", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("WRONG_EMAIL", HttpStatus.NOT_FOUND);
	}

	//resetear la contraseña sin tener la contraseña antigua
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "SUCCESS"),
			@ApiResponse(responseCode = "400", description = "FAIELD"),
			@ApiResponse(responseCode = "404", description = "WRONG_EMAIL") })
	@Operation(summary = "This endpoint will take care of reseting a new password when user has lost the old password ", description = "You should call this endpoint whenever you want recover or reset the user password")
	@Tag(name = "Set a new password when the old password is lost", description = "calling this endpoint will reset a new password when the old one is lost")
	@PostMapping("/recovery-code/reset-password")
	public ResponseEntity resetPassword (@RequestBody ChangeCredentialsRequest request)  {
		User user=userServiceImpl.findByEmail(request.getEmail());
		if(user!=null) {
			return userServiceImpl.resetNewPassword(request) ?  new ResponseEntity("SUCCESS", HttpStatus.OK) :
				new ResponseEntity("FAIELD", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("WRONG_EMAIL", HttpStatus.NOT_FOUND);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully operation"),
			@ApiResponse(responseCode = "400", description = "There is no customers found") })
	@Operation(summary = "This endpoint will return a list of all customers in the Db through a GET request", description = "You should call this endpoint whenever you want to get all customers")
	@Tag(name = "Get all users with role Customers in the database", description = "calling this endpoint will return a list of all customers")
	@GetMapping("/customers")
	public ResponseEntity getAllCusotmers() {
		List<User> customers = userServiceImpl.findAllCustomers();
		if (customers != null) {
			return ResponseEntity.ok(customers);
		}
		return new ResponseEntity("There is no customers found", HttpStatus.NO_CONTENT);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully operation"),
			@ApiResponse(responseCode = "400", description = "El email ya esta en uso") })
	@Operation(summary = "This endpoint will create a new user by passing a user object through post request", description = "You should pass user object as a post rquest body so this endpoint will create a new user")
	@Tag(name = "Create a new user", description = "calling this endpoint will create a new user")
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody User user, BindingResult result) {

		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
		} else {
			User userDB = userServiceImpl.findByEmail(user.getEmail());
			if (userDB != null) {
				System.out.println(userDB);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya esta en uso");
			}
			User authUserCreated = userServiceImpl.save(user);
			return ResponseEntity.ok(authUserCreated);
		}

	}

	// Actualizar user
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully operation"),
			@ApiResponse(responseCode = "404", description = "Multiple validations responses") })
	@Operation(summary = "Update a user personal information by Id", description = "Returns a JSON response with the user personal information updated")
	@Tag(name = "PUT update a user ", description = "Retrieve information of a updated user")
	@PutMapping(value = "/update/personal/{id}")
	public ResponseEntity<?> updateUserPersonalInfo(@RequestBody User user, @PathVariable("id") long id) {
		log.info("Updating user personal info with id {}", user.getId());
		// user.setId(user.getId());
		User updatedUser = userServiceImpl.updateUserPersonalInfo(id, user);
		if (updatedUser == null) {
			log.error("Unable to update. user with id {} not found.", user.getId());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a user with that id not found. ");
		}
		return ResponseEntity.ok(updatedUser);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully operation"),
			@ApiResponse(responseCode = "404", description = "Multiple validations responses") })
	@Operation(summary = "Update a user ubication by Id", description = "Returns a JSON response with the user updated information ")
	@Tag(name = "PUT update a user ", description = "Retrieve information of a updated user")
	@PutMapping(value = "/update/ubication/{id}")
	public ResponseEntity updateUserUbication(@RequestBody User user, @PathVariable Long id) {
		User updatedUser = userServiceImpl.updateUserUbication(id, user);
		if (updatedUser == null) {
			log.error("Unable to update. user with id {} not found.", user.getId());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a user with that id not found. ");
		}
		log.info("Updating user personal info with id {}", user.getId());
		return ResponseEntity.ok(updatedUser);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully operation"),
			@ApiResponse(responseCode = "404", description = "Multiple validations responses") })
	@Operation(summary = "Update a user credentials by Id", description = "Returns a JSON response with the user updated information ")
	@Tag(name = "PUT update a user ", description = "Retrieve information of a updated user")
	@PutMapping(value = "/update/credentials/{id}")
	public ResponseEntity updateUserCredentials(@RequestBody ChangeCredentialsRequest req, @PathVariable Long id) {
		ChangeCredentialsResponse resp = userServiceImpl.updateUserCredentials(id, req);
		if (resp != ChangeCredentialsResponse.CHANGED) {
			log.error("Unable to update. user credentials.");
			return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
		}
		log.info("Updating user credentials");
		return ResponseEntity.ok(resp);
	}

	// para obtener una lista de todos los users
	@Tag(name = "Retrieve All Users")
	@ApiResponse(responseCode = "200", description = "Successful retrieval of users", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
	@Operation(description = "get all Users", summary = "calling this endpoint will give you all Users")
	@GetMapping(value = "/all")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = new ArrayList<>();
		users = userServiceImpl.findAll();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			log.info("Getting all users", users);
			return ResponseEntity.ok(users);
		}
	}

	// Obtener un usuario dado su ID
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully operation"),
			@ApiResponse(responseCode = "404", description = "We cant find the user") })
	@Operation(summary = "Return a user with the provided Id", description = "Returns a JSON response with the user information with the provided Id")
	@Tag(name = "GET  user by  Id", description = "Retrieve a user with the provided Id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		log.info("Fetching User with id {}", id);
		User user = userServiceImpl.findById(id);
		if (null == user) {
			log.error("User with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	// Eliminar user
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully operation"),
			@ApiResponse(responseCode = "404", description = "We cant find a user with that id not found.") })
	@Operation(summary = "Delete a user by Id", description = "Returns a message if everything is ok")
	@Tag(name = "DELETE delete a user ", description = "Retrieve a message if everything its ok")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		log.info("Fetching & Deleting user with id {}", id);

		User user = userServiceImpl.findById(id);
		if (null == user) {
			log.error("Unable to delete. user with id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a user with that id not found. ");
		}
		userServiceImpl.deleteUser(user.getId());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Successfully operation. ");
	}

	// obtener un cliente dado su email
	@ApiResponse(responseCode = "200", description = "Successful retrieval of user", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
	@Operation(description = "get specific user", summary = "calling this endpoint will allow you to fetch a user by passing its email in the url as a path variable")
	@Tag(name = "Retrieve Single user")
	@GetMapping(value = "/email/{email}", produces = "application/json")
	public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
		User user = userServiceImpl.findByEmail(email);
		if (user != null){
			log.info("Getting a user", user);
			return ResponseEntity.ok(user);
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please make sure the email is correct!!");
	}

	// Obtener todas las sucursales de un cliente dado su ID
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully operation"),
			@ApiResponse(responseCode = "404", description = "We cant find the customer") })
	@Operation(summary = "Return all customer branches  with the provided customer Id", description = "Returns a JSON response with the customer branchs information with the provided customer Id")
	@Tag(name = "GET  customer branches by customer Id", description = "Retrieve a customer branches with the provided customer Id")
	@GetMapping(value = "/{id}/addresses")
	public ResponseEntity<List<Address>> getAddressesByUserId(@PathVariable(name = "id") Long id) {
		List<Address> branchs = addressServiceImpl.findByUserId(id);

		if (branchs == null) {
			log.info("Getting a user branches", branchs);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(branchs);
	}

	// Actualizar user verified
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully operation"),
	@ApiResponse(responseCode = "404", description = "Multiple validations responses") })
	@Operation(summary = "Update a user personal information by Id", description = "Returns a JSON response with the user personal information updated")
	@Tag(name = "PUT update a user ", description = "Retrieve information of a updated user")
	@PutMapping(value = "/update/verified/{id}")
	public ResponseEntity<?> updateUserVerified(@RequestBody User user, @PathVariable("id") long id) {
		log.info("Updating user personal info with id {}", user.getId());
		User updatedUser = userServiceImpl.updateUserVerified(id, user);
		if (updatedUser == null) {
			log.error("Unable to update. user with id {} not found.", user.getId());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a user with that id not found. ");
		}
		return ResponseEntity.ok(updatedUser);
	}

	// Establecer sucursal matriz de un cliente
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully operation"),
			@ApiResponse(responseCode = "404", description = "Customer information") })
	@Operation(summary = "Update a customer matriz branch by Id", description = "Returns a JSON response with the customer updated information ")
	@Tag(name = "PUT update a customer matriz branch ", description = "Retrieve information of a updated customer")
	@PutMapping(value = "/{userId}/address/{addressId}")
	public ResponseEntity<User> updateCustomerMatriz(@PathVariable("userId") Long userId,
			@PathVariable("addressId") Long addressId) {
		log.info("Updating Customer matriz branch with id {}", userId);

		// Obtengo todas las sucursales que son del cliente
		List<Address> addresses = addressServiceImpl.findByUserId(userId);

		// Obtengo la sucursal que deseo establecer como matriz
		Address address = addressServiceImpl.getAddress(addressId);

		// Si no encontro entonce retorno vacio
		if (addresses.isEmpty()) {
			log.error("Unable to update. Customer with id {} not found.", userId);
			return ResponseEntity.notFound().build();
		}
		// Si encontro actualizo una por una si es matriz o no

		for (Address address2 : addresses) {
			if (address2.getId() == address.getId()) {
				addressServiceImpl.updateMatrizAddress(address2, true);
			} else {
				addressServiceImpl.updateMatrizAddress(address2, false);
			}
		}

		User currentUser = userServiceImpl.findById(userId);

		return ResponseEntity.ok(currentUser);
	}

	private String formatMessage(BindingResult result) {
		List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
			Map<String, String> error = new HashMap<>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;

		}).collect(Collectors.toList());
		ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(errorMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
}
