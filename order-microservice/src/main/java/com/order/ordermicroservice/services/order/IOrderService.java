package com.order.ordermicroservice.services.order;

import java.util.List;

import com.order.ordermicroservice.entity.Order;
/**
 * This microservice was created by Kevin and David
 */
public interface IOrderService {

    public List<Order> findAllOrders();

    public Order createOrder(Order Order, Long customerId);
    public Order updateOrder(Order Order);
    public void deleteOrder(Long id);

    public Order getOrder(Long id);
}
