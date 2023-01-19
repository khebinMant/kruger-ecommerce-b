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

import krugers.microservicio.auth.authmicroservice.dto.LoginRequest;
import krugers.microservicio.auth.authmicroservice.dto.LoginResponse;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.entity.TokenDto;
import krugers.microservicio.auth.authmicroservice.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest dto){
        LoginResponse response = userServiceImpl.login(dto);
        if(response == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token){
        TokenDto tokenDto = userServiceImpl.validate(token);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user, BindingResult result){

        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        else{
            User userDB = userServiceImpl.findByEmail(user.getEmail());
            if(userDB != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya esta en uso");
            }
            User authUserCreated = userServiceImpl.save(user);
            return ResponseEntity.ok(authUserCreated);
        }

    }
    // Actualizar user
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully operation"),
        @ApiResponse(responseCode = "404", description = "Multiple validations responses")
    })
    @Operation(summary = "Update a user information by Id", description = "Returns a JSON response with the user updated information ")
    @Tag(name = "PUT update a user ", description = "Retrieve information of a updated user")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") long id) {
        log.info("Updating user with id {}", user.getId());

        User currentUser = userServiceImpl.findById(id);

        if ( null == currentUser ) {
            log.error("Unable to update. user with id {} not found.", user.getId());
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a user with that id not found. ");
        }
        // user.setId(user.getId());
        currentUser=userServiceImpl.updateUser(id, user);
        return  ResponseEntity.ok(currentUser);
    }

    @GetMapping("/hello")
    public String saludar(){
        User currentUser = userServiceImpl.findById(1L);
        System.out.println(currentUser);
        return "hooola";
    }

    // para obtener una lista de todos los users
	@Tag(name = "Retrieve All Users")
	@ApiResponse(responseCode = "200", description = "Successful retrieval of users", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
	@Operation(description = "get all Users", summary = "calling this endpoint will give you all Users")
    @GetMapping( value = "/all")
	public ResponseEntity<List<User>> getAllUsers() {
        List<User> users =  new ArrayList<>();
        users = userServiceImpl.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(users);
        }
	}

    //Obtener un usuario dado su ID
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully operation"),
        @ApiResponse(responseCode = "404", description = "We cant find the user")
    })
    @Operation(summary = "Return a user with the provided Id", description = "Returns a JSON response with the user information with the provided Id")
    @Tag(name = "GET  user by  Id", description = "Retrieve a user with the provided Id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        log.info("Fetching User with id {}", id);
        User user = userServiceImpl.findById(id);
        if (  null == user) {
            log.error("User with id {} not found.", id);
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a user with that id not found. ");
        }
        return  ResponseEntity.ok(user);
    }
    
    
    //Eliminar user
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a user with that id not found.")
	})
    @Operation(summary = "Delete a user by Id", description = "Returns a message if everything is ok")
    @Tag(name = "DELETE delete a user ", description = "Retrieve a message if everything its ok")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        log.info("Fetching & Deleting user with id {}", id);

        User user = userServiceImpl.findById(id);
        if ( null == user ) {
            log.error("Unable to delete. user with id {} not found.", id);
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a user with that id not found. ");
        }
        userServiceImpl.deleteUser(user.getId());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Successfully operation. ");
    }



    //obtener un cliente dado su email
	@ApiResponse(responseCode = "200", description = "Successful retrieval of user", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
	@Operation(description = "get specific user", summary = "calling this endpoint will allow you to fetch a user by passing its email in the url as a path variable")
	@Tag(name = "Retrieve Single user")
	@GetMapping(value="/email/{email}", produces = "application/json")
	public ResponseEntity<?> getUserByEmail (@PathVariable String email) {
		User user = userServiceImpl.findByEmail(email);
		if (user != null)
			return ResponseEntity.ok(user);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please make sure the email is correct!!");
		
	}


    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
