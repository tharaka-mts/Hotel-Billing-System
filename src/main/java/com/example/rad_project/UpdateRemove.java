package com.example.rad_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class UpdateRemove {

    @FXML
    private TextField address;

    @FXML
    private TextField dob;

    @FXML
    private TextField email;

    @FXML
    private TextField firstname;

    @FXML
    private TextField gender;

    @FXML
    private TextField lastname;

    @FXML
    private TextField nic;

    @FXML
    private TextField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField searchCashier;

    @FXML
    private TextField username;

    @FXML
    private Label output;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    PreparedStatement preparedStatement = null;

    @FXML
    void remove(ActionEvent event) {
        if (!username.getText().isBlank()){

            try {

                DBConnector connectNow = new DBConnector();
                Connection connectDB = connectNow.getConnection();



                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Successfully Removed "+username.getText());

                String sql = "DELETE FROM cashier WHERE userName='"+ username.getText()+"'";
                preparedStatement = connectDB.prepareStatement(sql);
                preparedStatement.executeUpdate(sql);


                if (alert.showAndWait().get() == ButtonType.OK){
                    FxmlLoader.changeScene(event,"UpdateRemove.fxml");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                e.getCause();
            }

        }else {

        }

    }

    @FXML
    void update(ActionEvent event) {

        if (!firstname.getText().isBlank() && !lastname.getText().isBlank() && !dob.getText().isBlank() && !phone.getText().isBlank() && !email.getText().isBlank() && !nic.getText().isBlank() && !address.getText().isBlank() && !gender.getText().isBlank() && !username.getText().isBlank() && !password.getText().isBlank()) {
            try {
                DBConnector connectNow = new DBConnector();
                Connection connectDB = connectNow.getConnection();

                String sql = "UPDATE cashier SET firstName='"+ firstname.getText() +"', lastName='"+ lastname.getText()+"',dateOfBirth='"+ dob.getText()+"',phoneNumber='"+ phone.getText()+"',email='"+ email.getText()+"',nic='"+ nic.getText()+"',address='"+ address.getText()+"',gender='"+ gender.getText()+"',userName='"+ username.getText()+"',password='"+ password.getText()+"' WHERE username='"+username.getText()+"'";
                preparedStatement = connectDB.prepareStatement(sql);
                preparedStatement.executeUpdate(sql);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Update Successfully");


                if (alert.showAndWait().get() == ButtonType.OK){
                    FxmlLoader.changeScene(event,"UpdateRemove.fxml");
                }


            } catch (SQLException e) {
                e.printStackTrace();
                e.getCause();
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Please Fill the Form");


            if (alert.showAndWait().get() == ButtonType.OK){
                FxmlLoader.changeScene(event,"UpdateRemove.fxml");
            }


        }

    }

    @FXML
    void search(ActionEvent event) {
        if (!searchCashier.getText().isBlank()){

            try {
                DBConnector connectNow = new DBConnector();
                Connection connectDB = connectNow.getConnection();
                pst = connectDB.prepareStatement("SELECT * FROM cashier WHERE userName='"+searchCashier.getText()+"'");
                rs =  pst.executeQuery();

                if (rs.next()) {
                    username.setText(rs.getString("userName"));
                    password.setText(rs.getString("password"));
                    firstname.setText(rs.getString("firstName"));
                    lastname.setText(rs.getString("lastName"));
                    dob.setText(rs.getString("dateOfBirth"));
                    phone.setText("0"+rs.getString("phoneNumber"));
                    email.setText(rs.getString("email"));
                    nic.setText(rs.getString("nic"));
                    address.setText(rs.getString("address"));
                    gender.setText(rs.getString("gender"));




                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Username");


                    if (alert.showAndWait().get() == ButtonType.OK){
                        FxmlLoader.changeScene(event,"UpdateRemove.fxml");
                    }


                }


            } catch (SQLException e) {
                e.printStackTrace();
                e.getCause();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Please enter a username to search");


            if (alert.showAndWait().get() == ButtonType.OK){
                FxmlLoader.changeScene(event,"UpdateRemove.fxml");
            }

        }
        searchCashier.setText(null);

    }

    //Buttons to navigate

    @FXML
    void adminDetails(ActionEvent event) {
        FxmlLoader.changeScene(event,"AdminPanel.fxml");
    }

    @FXML
    void registerCashier(ActionEvent event) {
        FxmlLoader.changeScene(event,"AddCashier.fxml");
    }

    @FXML
    void updateRemoveCashier(ActionEvent event) {
        FxmlLoader.changeScene(event,"UpdateRemove.fxml");
    }


    public void close(ActionEvent event) {
        FxmlLoader.changeScene(event,"SelectOption.fxml");
    }
}

