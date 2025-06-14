package com.flipkart.donzo.entities;


import com.flipkart.donzo.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String customerId;
    private String itemId;
    private String driverId;
    private OrderStatus status;
    private LocalDateTime creationTime;
    private LocalDateTime assignedTime;
    private LocalDateTime pickedUpTime;
    private LocalDateTime deliveredTime;
    private LocalDateTime updatedTime;

    public Order(String orderId, String customerId, String itemId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.itemId = itemId;
        this.status = OrderStatus.PENDING;
        this.creationTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    public void setDriver(String driverId) {
        this.driverId = driverId;
        this.status = OrderStatus.ASSIGNED;
        this.assignedTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    public void pickUp() {
        this.status = OrderStatus.PICKED_UP;
        this.pickedUpTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    public void deliver() {
        this.status = OrderStatus.DELIVERED;
        this.deliveredTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    public void cancel() {
        this.status = OrderStatus.CANCELED;
        this.updatedTime = LocalDateTime.now();
    }
}
