package com.order.ordermicroservice.services.payment;

import java.util.List;

import com.order.ordermicroservice.entity.Payment;

public interface IPaymentService {
    public Payment createPayment(Payment payment);
    public Payment getPayment(Long id);
    public List<Payment> findAllPayments();
}
