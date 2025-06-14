package com.flipkart.dunzo.services;

import com.flipkart.dunzo.entities.Driver;
import com.flipkart.dunzo.entities.Order;

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

    void addRating(String orderId, int rating);
}
