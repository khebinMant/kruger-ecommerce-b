package com.order.ordermicroservice.client.fallbacks;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.order.ordermicroservice.client.UserClient;
import com.order.ordermicroservice.model.User;
import com.order.ordermicroservice.model.OrderRequest;

@Component
public class UserFallback implements UserClient{

    @Override
    public ResponseEntity<?> addCart(OrderRequest request) {

        User customer = new User();
        customer.setEmail("none");
        customer.setFirstName("none");
        
        return ResponseEntity.ok(customer);
    }
    
}
