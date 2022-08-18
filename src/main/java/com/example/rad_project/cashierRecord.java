package com.example.rad_project;

public class cashierRecord extends Record {
    String username,firstName;
    int phoneNumber;

    public cashierRecord( String firstName,int phoneNumber,String username) {

        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
