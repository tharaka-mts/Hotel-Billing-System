package com.example.rad_project;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {

    public static Connection dbLink;


    public Connection getConnection(){
        String dbName = "hotel_and_resturant_billing_system";
        String dbUser = "root";
        String dbPWD = "";
        String url = "jdbc:mysql://localhost:3306/" + dbName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbLink = DriverManager.getConnection(url,dbUser,dbPWD);

        } catch (Exception e){
            e.printStackTrace();
        }
        return dbLink;
    }
}
