package com.flipkart.dunzo.controller;

import com.flipkart.dunzo.entities.Customer;
import com.flipkart.dunzo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public String onboardCustomer(@RequestBody Customer customer) {
        return customerService.onboardCustomer(customer.getName(), customer.getEmail(), customer.getPhoneNumber());
    }

    // Additional customer-related endpoints can be added here
}
