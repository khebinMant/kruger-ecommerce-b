package krugers.microservicio.auth.authmicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.tags.Tag;
import krugers.microservicio.auth.authmicroservice.dto.OrderRequest;
import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.service.CartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin
public class CartController {

    @Autowired
    CartServiceImpl cartServiceImpl;

    @Tag(name = "Create Order in ShoppingCar")
    @Operation(description = "add  order in car", summary = "calling this endpoint will allow you to add an order to the shopping Car by passing the order data as json in the request body")
    @PostMapping("/")
    public ResponseEntity<?> addCart(@RequestBody(required = true) OrderRequest request){
        Cart user= cartServiceImpl.addCart(request);
        if(user!=null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not find a user with the id provided"
        ,HttpStatus.NOT_FOUND);
    }
    
    // @ApiResponse(responseCode = "200", description = "Successful retrieval of userCar", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    // @Operation(description = "get specific userCar", summary = "calling this endpoint will allow you to fetch a userCar by passing its id in the url as a path variable")
    // @Tag(name = "Retrieve Single user car")
    // @GetMapping("/all/{userId}")
    // public ResponseEntity<?> getAllUserCartsById (@PathVariable Long userId) {
    // 	List<Cart> carts = cartServiceImpl.findAllByUserId(userId);
    	
    // 	return carts == null ? new ResponseEntity<>("Could not find a cards with the id provided"
    // 	        ,HttpStatus.NOT_FOUND):  new ResponseEntity<>(carts,HttpStatus.OK);
    // }

  /*  @DeleteMapping("/{userId}")
    public ResponseEntity deleteOrder(@PathVariable Long userId){
        
        user user= cartServiceImpl.deleteOrder(userId);
        if(user!=null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not find a user with the id provided"
        ,HttpStatus.NOT_FOUND);
    }*/    
}
