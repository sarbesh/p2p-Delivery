package com.flipkart.dunzo.services;

import com.flipkart.dunzo.enums.UserType;

public interface NotificationService {
    void sendNotification(String userId, UserType userType, String message);
}
