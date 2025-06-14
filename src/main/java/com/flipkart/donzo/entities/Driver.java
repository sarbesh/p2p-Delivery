package com.flipkart.donzo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    private  String id;
    private String name;
    private boolean available;
    private int phoneNumber;
}
