package com.example.rad_project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GuestBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceId;

    private int guestID;
    private double billAmount;
    private double cashGiven;
    private double balance;

    public GuestBill() {
    }

    public GuestBill(long invoiceId, int guestID, double billAmount, double cashGiven, double balance) {
        this.invoiceId = invoiceId;
        this.guestID = guestID;
        this.billAmount = billAmount;
        this.cashGiven = cashGiven;
        this.balance = balance;
    }

    public long getInvoiceID() {
        return invoiceId;
    }

    public void setInvoiceID(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public double getCashGiven() {
        return cashGiven;
    }

    public void setCashGiven(double cashGiven) {
        this.cashGiven = cashGiven;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
