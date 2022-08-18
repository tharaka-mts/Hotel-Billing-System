package com.example.rad_project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GuestDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guestID;

    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String country;
    private int noOfRooms;
    private String arrivalDate;
    private String departureDate;
    private String roomType;
    private String roomCategory;
    private String additionalInfo;
    private int daysOfstay;
    private String specialRequirements;

    public GuestDetail() {
    }

    public GuestDetail(long guestID, String firstName, String lastName, String email, int phone, String country, int noOfRooms, String arrivalDate, String departureDate, String roomType, String roomCategory, String additionalInfo, int daysOfstay, String specialRequirements) {
        this.guestID = guestID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.noOfRooms = noOfRooms;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.roomType = roomType;
        this.roomCategory = roomCategory;
        this.additionalInfo = additionalInfo;
        this.daysOfstay = daysOfstay;
        this.specialRequirements = specialRequirements;
    }

    public long getGuestID() {
        return guestID;
    }

    public void setGuestID(long guestID) {
        this.guestID = guestID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getDaysOfstay() {
        return daysOfstay;
    }

    public void setDaysOfstay(int daysOfstay) {
        this.daysOfstay = daysOfstay;
    }

    public String getSpecialRequirements() {
        return specialRequirements;
    }

    public void setSpecialRequirements(String specialRequirements) {
        this.specialRequirements = specialRequirements;
    }
}
