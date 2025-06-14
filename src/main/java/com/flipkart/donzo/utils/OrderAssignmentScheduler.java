package com.flipkart.donzo.utils;

import com.flipkart.donzo.enums.OrderStatus;
import com.flipkart.donzo.services.DriverService;
import com.flipkart.donzo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class OrderAssignmentScheduler {
    private final OrderService orderService;
    private final DriverService driverService;

    @Autowired
    public OrderAssignmentScheduler(OrderService orderService, DriverService driverService) {
        this.orderService = orderService;
        this.driverService = driverService;
    }

    @Scheduled(fixedDelay = 6000)
    public void assignOrderToDriver() {
        System.out.println("Running scheduled task: assignOrderToDriver");
        driverService.getAvailableDrivers().forEach(driver -> {
            System.out.println("Driver " + driver.getId() + " is available");
            orderService.getOrders().forEach(order -> {
                if (order.getStatus() == OrderStatus.PENDING) {
                    System.out.println("Assigning order " + order.getOrderId() + " to driver " + driver.getId());
                    orderService.assignOrderToDriver(order, driver);
                }
            });
        });
    }
}
