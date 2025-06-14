package com.flipkart.donzo.services.impl;

import com.flipkart.donzo.enums.UserType;
import com.flipkart.donzo.services.CustomerService;
import com.flipkart.donzo.services.NotificationService;
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
