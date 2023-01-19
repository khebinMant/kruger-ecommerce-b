package com.order.ordermicroservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.ordermicroservice.entity.Order;
import com.order.ordermicroservice.services.order.OrderServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This microservice was created by Kevin and David
 */
// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/orders")
public class OrderController {


    @Autowired
    private OrderServiceImpl orderService;

    // -------------------Retrieve All Orders--------------------------------------------
    @Tag(name = "Retrieve All Orders")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of orders", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class))))
    @Operation(description = "get all Orders", summary = "calling this endpoint will give you all Orders")
    @GetMapping
    public ResponseEntity<List<Order>> listAllOrders() {
        List<Order> Orders = orderService.findAllOrders();
        if (Orders.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(Orders);
    }

    // -------------------Retrieve Single Order------------------------------------------
    @ApiResponse(responseCode = "200", description = "Successful retrieval of order", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class))))
    @ApiResponse(responseCode = "404", description = "Order nor found or doesnt exist", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    @Operation(description = "get specific order", summary = "calling this endpoint will allow you to fetch order by passing its id in the url as a path variable")
    @Tag(name = "Retrieve Single Order")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
        Order Order  = orderService.getOrder(id);
        if (null == Order) {
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(Order);
    }

    //Aqui debo recibir el ID del customer ya que lo necesitare para crear el cart en un futuro
    // -------------------Create a Order-------------------------------------------
    @Tag(name = "Create a Order")
    @Operation(description = "add  orders", summary = "calling this endpoint will allow you to add orders by passing the order data as json in the request body")
    @Tag(name = "Create a Order")
    @PostMapping(value= "/user/{id}")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order Order, BindingResult result, @PathVariable("id") Long userId) {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Order OrderDB = orderService.createOrder(Order, userId);

        return  ResponseEntity.status( HttpStatus.CREATED).body(OrderDB);
    }

    // ------------------- Update a Order ------------------------------------------------
    @Tag(name = "Update a Order")
    @Operation(description = "add  orders", summary = "calling this endpoint will allow you to edit orders ")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @RequestBody Order Order) {

        Order.setId(id);
        Order currentOrder=orderService.updateOrder(Order);

        if (currentOrder == null) {
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(currentOrder);
    }

    // ------------------- Delete a Order-----------------------------------------
    @Operation(description = "delete an order", summary = "calling this endpoint will allow you to delete an order")
    @Tag(name = "Delete a Order")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") long id) {
        orderService.deleteOrder(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Successfully operation. ");
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
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
