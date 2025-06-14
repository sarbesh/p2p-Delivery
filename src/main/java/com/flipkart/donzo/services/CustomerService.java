package com.flipkart.donzo.services;

import com.flipkart.donzo.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CustomerService {

    String onboardCustomer(String name,  String emailId, int mobileNumber);

    Customer getCustomer(String id);

    Optional<Customer> getCustomerByEmail(String emailId);


}
