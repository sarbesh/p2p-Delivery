package com.flipkart.donzo;

import com.flipkart.donzo.utils.TestingApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class p2pApp {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(p2pApp.class, args);

        System.out.println("Application context initialized.");

        // Retrieve the TestingApplication bean and run the happyFlow method
        TestingApplication testingApplication = context.getBean(TestingApplication.class);
        testingApplication.happyFlow();
        testingApplication.createOrderForLaterPickup();
        testingApplication.duplicateCustomerOnboard();
        testingApplication.multipleOrderSingleDriver();
        testingApplication.addnewDriver();
        testingApplication.addMultipleDriver();
        testingApplication.addMultipleCustomer();
        testingApplication.addMultipleOrder();
        testingApplication.addMultipleDriver();
    }
}