package com.example.rad_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.rad_project.DBConnector.dbLink;

public class CsshSelection {
    @FXML
    private Button cancel;

    public void resturentLogin(ActionEvent event) {

        try {

            String sql ="DELETE FROM customerdetail;";
            String sql1 = "DELETE FROM bill";

            PreparedStatement preparedStatement = dbLink.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
            preparedStatement.executeUpdate(sql1);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        FxmlLoader.changeScene(event,"SelectBill.fxml");
    }

    public void hotelLogin(ActionEvent event) {
        FxmlLoader.changeScene(event,"PriceAvailability.fxml");
    }
    public void back(ActionEvent event) {
        FxmlLoader.changeScene(event,"hello-view.fxml");
    }


}
