package com.example.rad_project;



public class Record {
   String foodName;
   Double quantity,price,total;

    public Record(String foodName, Double price, Double quantity, Double total) {
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Record() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}

