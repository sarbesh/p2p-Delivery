package com.flipkart.dunzo.services;

import com.flipkart.dunzo.entities.Driver;

import java.util.List;

public interface DriverService {

    String onboardDriver(String name, int mobileNumber);

    Driver getDriver(String id);

    void setDriverAvailability(String id, boolean available);

    List<Driver> getDrivers();

    List<Driver> getAvailableDrivers();
}
