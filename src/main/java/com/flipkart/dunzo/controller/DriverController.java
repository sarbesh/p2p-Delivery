package com.flipkart.dunzo.controller;

import com.flipkart.dunzo.entities.Driver;
import com.flipkart.dunzo.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping
    public String onboardDriver(@RequestBody Driver driver) {
        return driverService.onboardDriver(driver.getName(), driver.getPhoneNumber());
    }

    // Additional driver-related endpoints can be added here
}
