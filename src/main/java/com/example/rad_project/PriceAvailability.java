package com.example.rad_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.Locale;

public class PriceAvailability {

    @FXML
    private Label dAvailability;

    @FXML
    private Label dBreakfast;

    @FXML
    private Label dFBoard;

    @FXML
    private Label dHBoard;

    @FXML
    private Label dPerNight;

    @FXML
    private Label fAvailability;

    @FXML
    private Label fBreakfast;

    @FXML
    private Label fFBoard;

    @FXML
    private Label fHBoard;

    @FXML
    private Label fPerNight;

    @FXML
    private Label sAvailability;

    @FXML
    private Label sBreakfast;

    @FXML
    private Label sFBoard;

    @FXML
    private Label sHBoard;

    @FXML
    private Label sPerNight;

    @FXML
    private TextField search;

    DBConnector connectNow = new DBConnector();
    Connection connectDB = connectNow.getConnection();
    PreparedStatement pst,pst2,pst3;
    ResultSet rs,rs2,rs3;
    PreparedStatement preparedStatement = null;

    public void back(ActionEvent event) {
        FxmlLoader.changeScene(event,"Cssh_Selection.fxml");
    }

    public void registration(ActionEvent event) {
        FxmlLoader.changeScene(event,"GuestRegistration.fxml");
    }

    public void availability(ActionEvent event) {
        FxmlLoader.changeScene(event,"PriceAvailability.fxml");
    }

    @FXML
    void classic(ActionEvent event) {
        search.setText("Classic");
    }

    @FXML
    void deluxe(ActionEvent event) {
        search.setText("Deluxe");
    }

    @FXML
    void presidential(ActionEvent event) {
        search.setText("Presidential");
    }

    //display method

    public void display(String roomType){

        try {

            pst = connectDB.prepareStatement("SELECT * FROM hotelrooms WHERE roomType='"+roomType+"' AND rooms='single'");
            rs =  pst.executeQuery();



            if (rs.next()) {
                sPerNight.setText("$"+rs.getString("perNight"));
                sBreakfast.setText("$"+rs.getString("withBreakfast"));
                sHBoard.setText("$"+rs.getString("hBoard"));
                sFBoard.setText("$"+rs.getString("fBoard"));
                sAvailability.setText(rs.getString("currentAvailability"));


            }

            pst2 = connectDB.prepareStatement("SELECT * FROM hotelrooms WHERE roomType='"+roomType+"' AND rooms='double'");
            rs2 =  pst2.executeQuery();

            if(rs2.next()){
                dPerNight.setText("$"+rs2.getString("perNight"));
                dBreakfast.setText("$"+rs2.getString("withBreakfast"));
                dHBoard.setText("$"+rs2.getString("hBoard"));
                dFBoard.setText("$"+rs2.getString("fBoard"));
                dAvailability.setText(rs2.getString("currentAvailability"));
            }


            pst3 = connectDB.prepareStatement("SELECT * FROM hotelrooms WHERE roomType='"+roomType+"' AND rooms='family'");
            rs3 =  pst3.executeQuery();

            if(rs3.next()){
                fPerNight.setText("$"+rs3.getString("perNight"));
                fBreakfast.setText("$"+rs3.getString("withBreakfast"));
                fHBoard.setText("$"+rs3.getString("hBoard"));
                fFBoard.setText("$"+rs3.getString("fBoard"));
                fAvailability.setText(rs3.getString("currentAvailability"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

    }



    public void search(ActionEvent event) {

        String roomType = search.getText().toLowerCase();

        if (roomType.equals("classic")){
            display("classic");
        }else if (roomType.equals("deluxe")){
            display("deluxe");
        }else if (roomType.equals("presidential")){
            display("presidential");
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Roomtype");
            alert.setContentText("Please Enter Correct Roomtype");

            if (alert.showAndWait().get() == ButtonType.OK){
                FxmlLoader.changeScene(event,"PriceAvailability.fxml");
            }
        }

       }

    }

