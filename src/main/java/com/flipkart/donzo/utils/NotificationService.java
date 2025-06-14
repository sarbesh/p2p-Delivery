package com.flipkart.donzo.utils;

public class NotificationService {
    public void notifyCustomer(String customerId, String message) {
        System.out.println("Notification to customer " + customerId + ": " + message);
    }

    public void notifyDriver(String driverId, String message) {
        System.out.println("Notification to driver " + driverId + ": " + message);
    }
}
