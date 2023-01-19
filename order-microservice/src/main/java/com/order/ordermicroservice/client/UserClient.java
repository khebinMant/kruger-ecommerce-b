package com.order.ordermicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.ordermicroservice.client.fallbacks.UserFallback;
import com.order.ordermicroservice.model.OrderRequest;

@FeignClient(name="auth-microservice", path = "/api/carts", fallback = UserFallback.class)
public interface UserClient {
    @PostMapping("/")
    public ResponseEntity<?> addCart(@RequestBody(required = true) OrderRequest request);
}
