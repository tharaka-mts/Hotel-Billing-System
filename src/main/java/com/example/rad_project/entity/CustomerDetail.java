package com.example.rad_project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String customerName;
    private int mobileNumber;
    private double givenMoney;

    private double totalMoney;

    public CustomerDetail() {
    }

    public CustomerDetail(long id, String customerName, int mobileNumber, double givenMoney, double totalMoney) {
        this.id = id;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.givenMoney = givenMoney;

        this.totalMoney = totalMoney;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public double getGivenMoney() {
        return givenMoney;
    }

    public void setGivenMoney(double givenMoney) {
        this.givenMoney = givenMoney;
    }



    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
