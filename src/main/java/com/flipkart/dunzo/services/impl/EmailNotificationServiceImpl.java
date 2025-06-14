package com.flipkart.dunzo.services.impl;

import com.flipkart.dunzo.enums.UserType;
import com.flipkart.dunzo.services.CustomerService;
import com.flipkart.dunzo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceImpl implements NotificationService {

    private CustomerService customerService;

    @Autowired
    public EmailNotificationServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void sendNotification(String id, UserType user , String message) {
        String emailId = customerService.getCustomer(id).getEmail();
        // send email
        System.out.println("Email sent to " + emailId + " : " + message);
    }
}
