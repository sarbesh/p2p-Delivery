package com.flipkart.donzo.services;

import com.flipkart.donzo.enums.UserType;

public interface NotificationService {
    void sendNotification(String userId, UserType userType, String message);
}
