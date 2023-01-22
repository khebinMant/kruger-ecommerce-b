package krugers.microservicio.auth.authmicroservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import krugers.microservicio.auth.authmicroservice.client.order.OrderClientF;
import krugers.microservicio.auth.authmicroservice.dto.OrderRequest;
import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.entity.User;
import krugers.microservicio.auth.authmicroservice.service.cart.CartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin
public class CartController {

    @Autowired
    CartServiceImpl cartServiceImpl;

    @Autowired
    OrderClientF orderClientF;

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

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Cart>> getUserCarts(@PathVariable("id") Long id){

        List<Cart> carts = new ArrayList<>();

        carts = cartServiceImpl.findAllCartsByUserId(id);
        if(carts.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        carts.stream().map(cart ->{
            cart.setOrder(orderClientF.getOrder(cart.getOrderId()).getBody());
            return cart;
        }).collect(Collectors.toList());


     return ResponseEntity.ok(carts);
    }

}
