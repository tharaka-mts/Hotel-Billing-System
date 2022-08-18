package com.example.rad_project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoice;

    private String customerName;
    private int mobileNumber;
    private double givenMoney;

    private double totalMoney;

    public BillDetail() {
    }

    public BillDetail(long invoice, String customerName, int mobileNumber, double givenMoney,double totalMoney) {
        this.invoice = invoice;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.givenMoney = givenMoney;
        this.totalMoney = totalMoney;
    }

    public long getInvoice() {
        return invoice;
    }

    public void setInvoice(long invoice) {
        this.invoice = invoice;
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

