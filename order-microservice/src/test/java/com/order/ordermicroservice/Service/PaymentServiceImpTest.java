package com.order.ordermicroservice.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.order.ordermicroservice.entity.Payment;
import com.order.ordermicroservice.repository.PaymentRepository;
import com.order.ordermicroservice.services.payment.PaymentServiceImpl;

@SpringBootTest(classes = { OrderServiceImplTest.class })
@RunWith(SpringRunner.class)
public class PaymentServiceImpTest {

    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    PaymentServiceImpl paymentServiceImpl;

    // Testing obtener todos las sucursales
    @Test
    @Order(1)
    public void test_findPayments() {

        List<Payment> customPayments = new ArrayList<Payment>();
        customPayments.add(new Payment("succces", null, new Date(), null));
        customPayments.add(new Payment("Failed", null, new Date(), null));

        when(paymentRepository.findAll()).thenReturn(customPayments);
        assertEquals(2, StreamSupport.stream(paymentServiceImpl.findAllPayments().spliterator(), false).count());
    }

    // Testing obtener sucursal por su Id
    @Test
    @Order(2)
    public void test_getPayment() {

        Payment customPayment = new Payment("succces", null, new Date(), null);

        when(paymentRepository.findById(1L)).thenReturn(Optional.of(customPayment));
        Assertions.assertThat(customPayment.getId()).isEqualTo(1L);
    }

    // Testing crear branch
    @Test
    @Order(3)
    public void test_createPayment() {

        Payment customPayment = new Payment("succces", null, new Date(), null);

        when(paymentRepository.save(customPayment)).thenReturn(customPayment);
        assertEquals(customPayment, paymentRepository.save(customPayment));
    }

}
