package com.order.ordermicroservice.repository;

import com.order.ordermicroservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * This microservice was created by Kevin and David
 */
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
