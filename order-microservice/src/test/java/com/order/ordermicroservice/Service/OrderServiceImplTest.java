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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.order.ordermicroservice.entity.Order;

import com.order.ordermicroservice.repository.OrderRepository;
import com.order.ordermicroservice.services.order.OrderServiceImpl;

@SpringBootTest(classes = { OrderServiceImplTest.class })
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    // Testing obtener todos los Orders
    @Test
    @org.junit.jupiter.api.Order(1)
    public void test_findOrderAll() {

        List<Order> customOrders = new ArrayList<Order>();

        customOrders.add(new Order(200L, 2.10, new Date(), "SUCCES", 1L , null, new Date(), null));
        customOrders.add(new Order(300L, 3.30, new Date(), "SUCCES", 1L, null, new Date(), null));

        when(orderRepository.findAll()).thenReturn(customOrders);
        assertEquals(2, StreamSupport.stream(orderServiceImpl.findAllOrders().spliterator(), false).count());
    }

    // Testing obtener Order por su Id
    @Test
    @org.junit.jupiter.api.Order(2)
    public void test_getOrder() {

        Order customOrder = new Order(200L, 2.10, new Date(), "SUCCES", 1L, null,  new Date(), null);

        when(orderRepository.findById(300L)).thenReturn(Optional.of(customOrder));
        Assertions.assertThat(customOrder.getId()).isEqualTo(300L);
    }

    // Testing crear Order
    @Test
    @org.junit.jupiter.api.Order(3)
    public void test_createOrder() {

        Order customOrder =new Order(200L, 2.10, new Date(), "SUCCES", 1L, null, new Date(), null);

        when(orderRepository.save(customOrder)).thenReturn(customOrder);
        assertEquals(customOrder, orderRepository.save(customOrder));
    }

}
