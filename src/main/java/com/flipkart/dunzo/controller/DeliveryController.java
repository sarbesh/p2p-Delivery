package com.flipkart.dunzo.controller;

import com.flipkart.dunzo.entities.Order;
import com.flipkart.dunzo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class DeliveryController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public String createOrder(@RequestParam String customerId, @RequestParam String itemId) {
        return orderService.createOrder(customerId, itemId);
    }

    @PostMapping("/{orderId}/pickup")
    public String pickUpOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        orderService.pickUpOrder(order);
        return "Order picked up";
    }

    @PostMapping("/{orderId}/deliver")
    public String deliverOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        orderService.deliverOrder(order);
        return "Order delivered";
    }

    // Additional endpoints can be added here
}
