package com.flipkart.donzo.services.impl;

import com.flipkart.donzo.enums.UserType;
import com.flipkart.donzo.services.CustomerService;
import com.flipkart.donzo.services.DriverService;
import com.flipkart.donzo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationServiceImpl implements NotificationService {

    private CustomerService customerService;
    private DriverService driverService;

    @Autowired
    public SmsNotificationServiceImpl(CustomerService customerService, DriverService driverService) {
        this.customerService = customerService;
        this.driverService = driverService;
    }

    @Override
    public void sendNotification(String userId, UserType userType, String message){
        int mobileNumber = 0;
        if (userType.equals(UserType.CUSTOMER)) {
            System.out.println("sendNotification "+userId+" customerService: "+customerService.getCustomer(userId));
            mobileNumber = customerService.getCustomer(userId).getPhoneNumber();
        } else if (userType.equals(UserType.DRIVER)) {
            System.out.println("sendNotification "+userId+" driverService: "+driverService.getDriver(userId));
            mobileNumber = driverService.getDriver(userId).getPhoneNumber();
        } else {
            System.out.println("Invalid user type");
        }
        if (mobileNumber != 0) {
            System.out.println("Sending SMS to " + mobileNumber + " with message: " + message);
        }
    }

}
