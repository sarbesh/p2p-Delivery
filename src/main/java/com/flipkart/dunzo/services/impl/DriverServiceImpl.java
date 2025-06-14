package com.flipkart.dunzo.services.impl;

import com.flipkart.dunzo.entities.Driver;
import com.flipkart.dunzo.services.DriverService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {
    private final Map<String, Driver> drivers = new HashMap<>();

    @Override
    public String onboardDriver(String name, int mobileNumber) {
        //Simulate DB query to find existing driver
        Optional<Map.Entry<String, Driver>> any = drivers.entrySet().parallelStream()
                .filter(entry -> entry.getValue().getPhoneNumber() == mobileNumber)
                .findAny();
        if (any.isEmpty()) {
            String id = UUID.randomUUID().toString();
            drivers.put(id, new Driver(id, name, true, mobileNumber));
            return id;
        } else {
            System.out.println("Driver with mobile number " + any.get().getValue().getPhoneNumber() + " already exists with id:"+ any.get().getKey());
            return "null";
        }
    }

    @Override
    public Driver getDriver(String id) {
        return drivers.get(id);
    }

    @Override
    public void setDriverAvailability(String id, boolean available) {
        if (drivers.containsKey(id)) {
            drivers.get(id).setAvailable(available);
        }
    }

    @Override
    public List<Driver> getDrivers() {
        return new ArrayList<>(drivers.values());
    }

    @Override
    public List<Driver> getAvailableDrivers() {
        return drivers.values().stream()
                .filter(Driver::isAvailable)
                .collect(Collectors.toList());
    }


}
