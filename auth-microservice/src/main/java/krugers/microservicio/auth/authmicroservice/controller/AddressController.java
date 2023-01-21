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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import krugers.microservicio.auth.authmicroservice.entity.Address;
import krugers.microservicio.auth.authmicroservice.service.address.IAddressService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping (value = "/api/address")
public class AddressController {

    @Autowired
    IAddressService iAddressService;

    //Obtener todas las direcciones
    // @ApiResponses(value = { 
	// 	@ApiResponse(responseCode = "200", description = "Successfully operation"),
	// 	@ApiResponse(responseCode = "404", description = "No content")
	// })
    // @Operation(summary = "Return a list with all branchs", description = "Returns a JSON response with the branchs information")
    // @Tag(name = "GET all branchs ", description = "Retrieve information of all branchs")
    @GetMapping("/")
    public ResponseEntity<List<Address>> listAddress(){
            List<Address> addresses = new ArrayList<>();

            addresses = iAddressService.findAddresses();
            if(addresses.isEmpty()){
                return ResponseEntity.noContent().build();
            }

        return ResponseEntity.ok(addresses);
    }

    //Obtener sucursal dado su id
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Return a branch by Id", description = "Returns a JSON response branch information")
    @Tag(name = "GET branch by Id ", description = "Retrieve information of branch by Id")
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getAddress(@PathVariable(name="id") Long id){
        Address address = iAddressService.getAddress(id);

        if(address==null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a branch with that id not found. ");
        }
        return ResponseEntity.ok(address);
    }
    
    // //Crear sucursal y adjuntar a cliente
    // @ApiResponses(value = { 
	// 	@ApiResponse(responseCode = "200", description = "Successfully operation"),
	// 	@ApiResponse(responseCode = "404", description = "No content")
	// })
    // @Operation(summary = "Create a new branch to a customer", description = "Returns a JSON response with the branch information")
    // @Tag(name = "POST branch", description = "Retrieve information of a created branch")
    @PostMapping("/")
    public ResponseEntity<Address> createAddress(@Valid @RequestBody Address address, BindingResult result){
        
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  this.formatMessage(result));
        }
        
        Address createdAddress = iAddressService.createAddress(address, address.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
    }

    //Actualizar la información de la sucursal
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a branch with that id not found.")
	})
    @Operation(summary = "Update branch of a customer", description = "Returns a JSON response with the branch information")
    @Tag(name = "PUT update branch information", description = "Retrieve information of a created branch")
    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable(name="id") Long id, @RequestBody Address address){
        address.setId(id);
        Address addressDB = iAddressService.updateAddress(address);
        if(addressDB == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a branch with that id not found. ");
        }
        return ResponseEntity.ok(addressDB);
    }

    //Eliminar una sucursal
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a branch with that id not found.")
	})
    @Operation(summary = "Delete branch of a customer", description = "Returna OK status")
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name="id") Long id){
        Address addressDeleted =  iAddressService.deleteAddress(id);
        if(addressDeleted == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("We cant find a branch with that id not found. ");
        }
        return ResponseEntity.ok(addressDeleted);
    }


    private String formatMessage(BindingResult bindingResult){
        List<Map<String,String>> errors =  bindingResult.getFieldErrors().stream()
            .map(err -> {
                Map<String,String> error = new HashMap<>();
                error.put(err.getField(), err.getDefaultMessage());
                return error;
            }).collect(Collectors.toList());
        
        //Aqui guardo utilizando la clase que creamos para gestionar el error
        ErrorMessage errorMessage = ErrorMessage.builder()
            .code("01")
            .messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();

        String jsoString="";
        try{
            jsoString = mapper.writeValueAsString(errorMessage);
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return jsoString;
    } 
}
