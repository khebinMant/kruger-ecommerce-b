package com.order.ordermicroservice.services.payment;

import com.order.ordermicroservice.entity.Payment;
import com.order.ordermicroservice.repository.PaymentRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * This microservice was created by Kevin and David
 */
@Service
public class PaymentServiceImpl implements IPaymentService{

    @Autowired
    PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment){
        payment.setCreated(new Date());
        return paymentRepository.save(payment);
    }
    // -------------------getPayment by ID service--------------------------------------------
    public Payment getPayment(Long id){
        return paymentRepository.findById(id).orElse(null);
    }
    @Override
    public List<Payment> findAllPayments() {
        return  paymentRepository.findAll();
    }
}
