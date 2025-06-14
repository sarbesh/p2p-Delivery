package com.flipkart.dunzo.utils;

import com.flipkart.dunzo.entities.Customer;
import com.flipkart.dunzo.entities.Items;
import com.flipkart.dunzo.services.CustomerService;
import com.flipkart.dunzo.services.DriverService;
import com.flipkart.dunzo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TestingApplication {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderAssignmentScheduler scheduler;

    private List<Items> items = List.of(
            new Items("1", "abc", "abc"),
            new Items("2", "def", "def"),
            new Items("3", "ghi", "ghi"),
            new Items("4", "jkl", "jkl"),
            new Items("5", "mno", "mno"));

    public void happyFlow(){
        System.out.println("Starting happy flow test.");
        String customerId = customerService.onboardCustomer("abc", "abc@gmail", 123);
        System.out.println("Customer onboarded with ID: " + customerId);
        String driverId = driverService.onboardDriver("abc", 123);
        System.out.println("Driver onboarded with ID: " + driverId);
        String orderId = orderService.createOrder(customerId, items.get(0).getId());
        System.out.println("Order created with ID: " + orderId);
        orderService.assignOrderToDriver(orderService.getOrder(orderId));
        orderService.pickUpOrder(orderService.getOrder(orderId));
        orderService.deliverOrder(orderService.getOrder(orderId));
        //try cancelling post delivery
        orderService.cancelOrder(orderId);
        System.out.println("HAPPY FLOW TEST COMPLETED.");
    }

    public void createOrderForLaterPickup(){
        String customerId = customerService.onboardCustomer("abc", "abc2@gmail", 1234);
        System.out.println("Customer onboarded with ID: " + customerId);
        String driverId = driverService.onboardDriver("abc", 1233);
        System.out.println("Driver onboarded with ID: " + driverId);
        String orderId = orderService.createOrder(customerId, items.get(0).getId());
        System.out.println("Order created with ID: " + orderId);
    }

    public void duplicateCustomerOnboard() {
        String customerId = customerService.onboardCustomer("abc", "abc@gmail", 123);
        System.out.println("Customer onboarded with ID: " + customerId);
        String customerId2 = customerService.onboardCustomer("abc", "abc@gmail", 123);
        System.out.println("Customer onboarded with ID: " + customerId2);
    }

    public void multipleOrderSingleDriver(){
        String customerId = customerService.onboardCustomer("abc", "abc@gmail", 123);
        System.out.println("Customer onboarded with ID: " + customerId);
        String driverId = driverService.onboardDriver("abc", 123);
        System.out.println("Driver onboarded with ID: " + driverId);
        String orderId = orderService.createOrder(customerId, items.get(0).getId());
        System.out.println("Order created with ID: " + orderId);
        String orderId2 = orderService.createOrder(customerId, items.get(1).getId());
        System.out.println("Order created with ID: " + orderId2);
        orderService.assignOrderToDriver(orderService.getOrder(orderId));
        orderService.assignOrderToDriver(orderService.getOrder(orderId2));
    }

    public void addnewDriver(){
        String driverId = driverService.onboardDriver("abc", 1236);
        System.out.println("Driver onboarded with ID: " + driverId);
    }

    public void addMultipleDriver(){
        for(int i=0;i<10;i++){
            Random rand = new Random();
            int randomNum = rand.nextInt((100 - 1) + 1) + 1;
            String driverId = driverService.onboardDriver("abc"+randomNum, randomNum );
            System.out.println("Driver onboarded with ID: " + driverId);
        }
    }

    public void addMultipleCustomer(){
        for(int i=0;i<10;i++){
            String customerId = customerService.onboardCustomer("abc", "abc@gmail", 123);
            System.out.println("Customer onboarded with ID: " + customerId);
        }
    }

    public void addMultipleOrder(){
        for(int i=0;i<10;i++){
//            Random rands = new Random();
//            int randomNums = rands.nextInt((100 - 1) + 1) + 1;
//            String customerId = customerService.onboardCustomer("abc", randomNums +"abc@gmail", 123);
//            System.out.println("Customer onboarded with ID: " + customerId);
//            String driverId = driverService.onboardDriver("abc8", 12389);
//            addMultipleDriver();
//            System.out.println("Driver onboarded with ID: " + driverId);
            Optional<Customer> customerByEmail = customerService.getCustomerByEmail("abc@gmail");
            Random rand = new Random();
            int randomNum = rand.nextInt(items.size());
            String orderId = orderService.createOrder(customerByEmail.get().getId(), items.get(randomNum).getId());
            System.out.println("Order created with ID: " + orderId);
            orderService.assignOrderToDriver(orderService.getOrder(orderId));
        }
    }

    public void multipleOrderMultipleDriver() {
        for (int i = 0; i < 10; i++) {
            String customerId = customerService.onboardCustomer("abc", "abc@gmail", 123);
            System.out.println("Customer onboarded with ID: " + customerId);
            Random rand = new Random();
            int randomNum = rand.nextInt((100 - 1) + 1) + 1;
            String driverId = driverService.onboardDriver("abc"+randomNum, randomNum );
            System.out.println("Driver onboarded with ID: " + driverId);
            String orderId = orderService.createOrder(customerId, items.get(0).getId());
            System.out.println("Order created with ID: " + orderId);
            orderService.assignOrderToDriver(orderService.getOrder(orderId));
            orderService.pickUpOrder(orderService.getOrder(orderId));
            orderService.deliverOrder(orderService.getOrder(orderId));
        }
    }

}
