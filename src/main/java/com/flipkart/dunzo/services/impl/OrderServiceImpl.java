package com.flipkart.dunzo.services.impl;

import com.flipkart.dunzo.entities.Order;
import com.flipkart.dunzo.entities.Driver;
import com.flipkart.dunzo.enums.OrderStatus;
import com.flipkart.dunzo.enums.UserType;
import com.flipkart.dunzo.services.NotificationService;
import com.flipkart.dunzo.services.OrderService;
import com.flipkart.dunzo.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private final Map<String, Order> orders = new HashMap<>();
    private final DriverService driverService;
    private final NotificationService smsNotificationService;

    @Autowired
    public OrderServiceImpl(DriverService driverService,
                            @Qualifier("smsNotificationServiceImpl") SmsNotificationServiceImpl smsNotificationService) {
        this.smsNotificationService = smsNotificationService;
        this.driverService = driverService;
    }

    @Override
    public String createOrder(String customerId, String itemId) {
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, customerId, itemId);
        orders.put(orderId, order);
        System.out.println("Order: "+order+" created with ID: " + orderId);
        assignOrderToDriver(order);
        return orderId;
    }

    @Override
    public void cancelOrder(String orderId) {
        Order order = orders.get(orderId);
        if(order == null){
            throw new IllegalArgumentException("Order not found");
        }
        if (order.getStatus() == OrderStatus.PENDING || order.getStatus() == OrderStatus.ASSIGNED) {
            order.cancel();
            System.out.println("Order " + orderId + " has been canceled.");
//            emailnotificationservice.sendNotification(order.getCustomerId(), UserType.CUSTOMER, "Your order has been canceled");
            smsNotificationService.sendNotification(order.getCustomerId(), UserType.CUSTOMER, "Your order has been canceled");
        } else{
            System.out.println("Order once moved to "+order.getStatus()+" can not be canceled");
        }
    }

    @Override
    public void assignOrderToDriver(Order order) {
        Optional<Driver> availableDriver = driverService.getAvailableDrivers().stream()
                .findFirst();
        if (availableDriver.isPresent()) {
            Driver driver = availableDriver.get();
            assignOrderToDriver(order, driver);
        } else {
            System.out.println("No available drivers found, scheduling order");
        }
    }

    @Override
    public void assignOrderToDriver(Order order, Driver driver) {
        driver.setAvailable(false);
        order.setDriver(driver.getId());
        System.out.println("Order " + order + " assigned to driver " + driver.getName());
        smsNotificationService.sendNotification(order.getCustomerId(), UserType.CUSTOMER, "Your order has been assigned to driver " + driver.getName());
//        emailnotificationservice.sendNotification(order.getCustomerId(), UserType.CUSTOMER, "Your order has been assigned to driver " + driver.getName());
        smsNotificationService.sendNotification(driver.getId(), UserType.DRIVER, "You have been assigned to order " + order.getOrderId());
    }

    @Override
    public void pickUpOrder(Order order) {
        order.pickUp();
        System.out.println("Order " + order.getOrderId() + " has been picked up.");
//        emailnotificationservice.sendNotification(order.getCustomerId(), UserType.CUSTOMER, "Your order has been picked up");
        smsNotificationService.sendNotification(order.getCustomerId(), UserType.CUSTOMER, "Your order has been picked up");
    }

    @Override
    public void deliverOrder(Order order) {
        order.deliver();
        System.out.println("Order " + order.getOrderId() + " has been delivered.");
        driverService.setDriverAvailability(order.getDriverId(), true);
//        emailnotificationservice.sendNotification(order.getCustomerId(), UserType.CUSTOMER, "Your order has been delivered");
        smsNotificationService.sendNotification(order.getCustomerId(), UserType.CUSTOMER, "Your order has been delivered");
        freeDriverAssignOrder(order.getDriverId());
    }

    @Override
    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public void freeDriverAssignOrder(String driverId) {
        Optional<Order> first = orders.values().stream()
                .filter(order -> order.getStatus() == OrderStatus.PENDING)
                .findFirst();
        if(first.isPresent()){
            System.out.println("Driver " + driverId + " is now available and has been assigned to a pending order.");
        }
    }

    @Override
    public void addRating(String orderId, int rating) {
        Order order = orders.get(orderId);
        order.setRating(rating);
        driverService.updateDriverRating(order.getDriverId(), rating);
    }
}
