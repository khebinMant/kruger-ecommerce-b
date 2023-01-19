package com.order.ordermicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.order.ordermicroservice.client.fallbacks.ProductFallback;
import com.order.ordermicroservice.model.Product;
/**
 * This microservice was created by Kevin and David
 */
@FeignClient(name="product-microservice", path = "/api/products", fallback = ProductFallback.class)
public interface ProductClient {

    @GetMapping(value="/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name="id") Long id);

    @GetMapping(value = "{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable(name="id")Long id, @RequestParam(name="quantity", required = true) Double quantity);
    
    @GetMapping(value = "{id}/counter")
    public ResponseEntity<Product> updateSaleCounter(@PathVariable(name="id")Long id, @RequestParam(name="quantity", required = true) Double quantity);

}
