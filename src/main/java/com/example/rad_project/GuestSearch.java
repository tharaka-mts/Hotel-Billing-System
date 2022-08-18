package com.example.rad_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GuestSearch {
    @FXML
    private Label aDate;

    @FXML
    private Label aInfo;

    @FXML
    private Label balance;

    @FXML
    private Label billAmount;

    @FXML
    private Label dDate;

    @FXML
    private Label email;

    @FXML
    private Label gName;

    @FXML
    private Label givenCash;

    @FXML
    private TextField invoice;

    @FXML
    private Label mNumber;

    @FXML
    private Label nRoom;

    @FXML
    private Label rCategory;

    @FXML
    private Label rType,country;

    @FXML
    private Label sRequirement;

    @FXML
    private Button search;

    @FXML
    double gBalance;
    @FXML
    void search(ActionEvent event) {

        if (!invoice.getText().isBlank()) {


            try {
                DBConnector connectNow = new DBConnector();
                Connection connectDB = connectNow.getConnection();
                PreparedStatement pStatement = connectDB.prepareStatement("SELECT * FROM guestdetail  WHERE guestID='" + invoice.getText() + "'");
                ResultSet rs = pStatement.executeQuery();

                PreparedStatement pStatement2 = connectDB.prepareStatement("SELECT * FROM guestbill  WHERE guestID='" + invoice.getText() + "'");
                ResultSet rs2 = pStatement2.executeQuery();



                if (rs.next()) {
                    aInfo.setText(rs.getString("additionalInfo"));
                    aDate.setText(rs.getString("arrivalDate"));
                    country.setText(rs.getString("country"));
                    dDate.setText(rs.getString("departureDate"));
                    email.setText(rs.getString("email"));
                    gName.setText(rs.getString("firstName")+" "+rs.getString("lastName"));
                    nRoom.setText(rs.getString("noOfRooms"));
                    mNumber.setText("0"+rs.getString("phone"));
                    rCategory.setText(rs.getString("roomCategory"));
                    rType.setText(rs.getString("roomType"));
                    sRequirement.setText(rs.getString("specialRequirements"));


                    if(rs2.next()){
                        balance.setText(rs2.getString("balance"));
                        billAmount.setText(rs2.getString("billAmount"));
                        givenCash.setText(rs2.getString("cashGiven"));


                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Invoice Number");


                    if (alert.showAndWait().get() == ButtonType.OK){
                        FxmlLoader.changeScene(event,"Invoice.fxml");
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Please enter a Invoice Number to search");


            if (alert.showAndWait().get() == ButtonType.OK){
                FxmlLoader.changeScene(event,"Invoice.fxml");
            }
        }
        invoice.setText(null);


    }


    public void back(ActionEvent event) {
        FxmlLoader.changeScene(event,"RoomUpdate.fxml");
    }
}
