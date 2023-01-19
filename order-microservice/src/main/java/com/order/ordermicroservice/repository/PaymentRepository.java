package com.order.ordermicroservice.repository;

import com.order.ordermicroservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * This microservice was created by Kevin and David
 */
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
