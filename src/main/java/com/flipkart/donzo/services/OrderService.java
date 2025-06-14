package com.flipkart.donzo.services;

import com.flipkart.donzo.entities.Driver;
import com.flipkart.donzo.entities.Order;

import java.util.List;

public interface OrderService {

    String createOrder(String customerId, String itemId);

    void cancelOrder(String orderId);

    void assignOrderToDriver(Order order);

    void assignOrderToDriver(Order order, Driver driver);

    void pickUpOrder(Order order);

    void deliverOrder(Order order);

    Order getOrder(String orderId);

    List<Order> getOrders();

    void freeDriverAssignOrder(String driverId);
}
