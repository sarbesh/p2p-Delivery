package com.flipkart.donzo.services.impl;

import com.flipkart.donzo.entities.Customer;
import com.flipkart.donzo.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<String, Customer> customers = new HashMap<>();

    @Override
    public String onboardCustomer(String name, String emailId, int mobileNumber) {
        //simulate database query to check for existing customer
        Optional<Map.Entry<String, Customer>> any = customers.entrySet().parallelStream()
                .filter(entry -> entry.getValue().getEmail().equals(emailId)).findAny();
        if (any.isEmpty()) {
            String id = UUID.randomUUID().toString();
            customers.put(id, new Customer(id, name, emailId, mobileNumber));
            return id;
        } else {
            System.out.println("Customer with email " + any.get().getValue().getEmail() + " already exists with id:"+ any.get().getKey());
            return "null";
        }
    }

    public Customer getCustomer(String id) {
        return customers.get(id);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customers.values().stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();
    }
}
