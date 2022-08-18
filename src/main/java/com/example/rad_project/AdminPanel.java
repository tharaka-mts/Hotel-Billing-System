package com.example.rad_project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

import static com.example.rad_project.DBConnector.dbLink;

public class AdminPanel {

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newPasswordAgain;

    @FXML
    private TextField oldPassword;



    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    PreparedStatement preparedStatement;

    @FXML
    void changePassword(ActionEvent event) {
        if (!newPassword.getText().isBlank() && !newPasswordAgain.getText().isBlank() && !oldPassword.getText().isBlank()){

            try {
                DBConnector connectNow = new DBConnector();
                Connection connectDB = connectNow.getConnection();
                pst = connectDB.prepareStatement("SELECT password FROM cashier WHERE username='Admin'");
                rs = pst.executeQuery();

                if (rs.next()) {
                    String db_oldPassword = rs.getString("password");

                    //CHECKING WHETHER OLD PASSWORDS MATCH
                    if(db_oldPassword.equals(oldPassword.getText())){
                        //CHECKING WHETHER new PASSWORDS MATCH
                        if(newPassword.getText().equals(newPasswordAgain.getText())){

                            //UPDATING PASSWORD
                            String sql = "UPDATE cashier SET password ='"+newPassword.getText() +"' WHERE username ='Admin'";
                            preparedStatement = connectDB.prepareStatement(sql);
                            preparedStatement.executeUpdate(sql);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation");
                            alert.setHeaderText("Password change Successful");


                            if (alert.showAndWait().get() == ButtonType.OK){
                                FxmlLoader.changeScene(event,"AdminPanel.fxml");
                            }



                        }
                        else {

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("New Password Does Not Match");
                            alert.setContentText("Please Enter Correct Password");


                            if (alert.showAndWait().get() == ButtonType.OK){
                                FxmlLoader.changeScene(event,"AdminPanel.fxml");
                            }

                        }

                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Old Password Does Not Match");
                        alert.setContentText("Please Enter Correct Password");


                        if (alert.showAndWait().get() == ButtonType.OK){
                            FxmlLoader.changeScene(event,"AdminPanel.fxml");
                        }

                    }


                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid passwords");
                    alert.setContentText("Please Enter Correct Password");


                    if (alert.showAndWait().get() == ButtonType.OK){
                        FxmlLoader.changeScene(event,"AdminPanel.fxml");
                    }

                }




            } catch (SQLException e) {
                e.printStackTrace();
                e.getCause();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Please fill all the fields");



            if (alert.showAndWait().get() == ButtonType.OK){
                FxmlLoader.changeScene(event,"AdminPanel.fxml");
            }

        }


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

    public void closeAdmin(ActionEvent event) {
        FxmlLoader.changeScene(event, "SelectOption.fxml");
    }
}
