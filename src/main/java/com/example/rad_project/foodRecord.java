package com.example.rad_project;

import com.example.rad_project.Record;

public class foodRecord extends Record {
    String foodName;
    Double price;

    public foodRecord(String foodName, Double price, Double quantity, Double total, String foodName1, Double price1) {
        super(foodName, price, quantity,total);
        this.foodName = foodName1;
        this.price = price1;
    }

    public foodRecord(String foodName, Double price) {
        this.foodName = foodName;
        this.price = price;
    }

    @Override
    public String getFoodName() {
        return foodName;
    }

    @Override
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }
}
