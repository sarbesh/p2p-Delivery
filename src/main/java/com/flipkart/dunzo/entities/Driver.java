package com.flipkart.dunzo.entities;

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
    private double rating;
    private int deliveryCount;
    private int deliveryRating;

    public Driver(String id, String name, boolean available, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.phoneNumber = phoneNumber;
    }

    //use existing rating and delivery count and the incoming rating to find the avg rating
    public void updateRating(double rating){
        double totalRating = this.rating * this.deliveryRating;
        totalRating += rating;
        this.deliveryRating++;
        this.rating = totalRating / this.deliveryRating;
    }

    public void incrementDeliveryCount(){
        deliveryCount++;
    }
}
