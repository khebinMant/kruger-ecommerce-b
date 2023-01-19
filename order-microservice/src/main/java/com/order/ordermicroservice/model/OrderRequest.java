package com.order.ordermicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest {
        
    private Long userId;
    private Long orderId;

}
