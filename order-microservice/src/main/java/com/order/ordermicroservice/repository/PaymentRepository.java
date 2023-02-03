package com.order.ordermicroservice.repository;

import com.order.ordermicroservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
