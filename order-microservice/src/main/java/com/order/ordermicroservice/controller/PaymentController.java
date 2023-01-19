package com.order.ordermicroservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.ordermicroservice.entity.Payment;
import com.order.ordermicroservice.services.payment.PaymentServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RestController
@RequestMapping(value = "/api/payments")
public class PaymentController {
    @Autowired
    PaymentServiceImpl paymentService;

    @Tag(name = "Create a payment")
    @Operation(description = "add  payment", summary = "calling this endpoint will allow you to add a Payment by passing the order data as json in the request body")
    @PostMapping("/placePayment")
    public ResponseEntity<Payment> createPayment (@Valid @RequestBody Payment payment, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Payment paymentDB = paymentService.createPayment(payment);

        return  ResponseEntity.status( HttpStatus.CREATED).body(paymentDB);
    }

    @Tag(name = "Get all payments information")
    @Operation(description = "get all Payments", summary = "calling this endpoint will give you all Payments data")    @GetMapping
    public ResponseEntity<List<Payment>> listAllOrders(){
        List<Payment> payments = paymentService.findAllPayments();
        return ResponseEntity.ok(payments);
    }

    @Tag(name = "Get information of a specific payment")
    @Operation(description = "get specific Payment", summary = "calling this endpoint will allow you to fetch a payment by passing its id in the url as a path variable")    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable("id")long id){
        Payment payment = paymentService.getPayment(id);
        return ResponseEntity.ok(payment);
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
