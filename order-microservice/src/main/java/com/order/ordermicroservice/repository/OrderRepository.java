package com.order.ordermicroservice.repository;

import com.order.ordermicroservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * This microservice was created by Kevin and David
 */
public interface OrderRepository extends JpaRepository<Order,Long> {

}
