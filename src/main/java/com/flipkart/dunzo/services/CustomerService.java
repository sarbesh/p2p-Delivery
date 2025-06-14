package com.flipkart.dunzo.services;

import com.flipkart.dunzo.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CustomerService {

    String onboardCustomer(String name,  String emailId, int mobileNumber);

    Customer getCustomer(String id);

    Optional<Customer> getCustomerByEmail(String emailId);


}
