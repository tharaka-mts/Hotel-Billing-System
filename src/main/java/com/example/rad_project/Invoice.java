package com.example.rad_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Invoice implements Initializable{

    @FXML
    private Button back;

    @FXML
    private Label balance;

    @FXML
    private Label customerName;

    @FXML
    private Label foodPrice;

    @FXML
    private Label givenMoney;

    @FXML
    private TextField invoice;

    @FXML
    private Label invoiceNumber;

    @FXML
    private Label mobileNumber;

    @FXML
    private Button search;

    @FXML
    private TableColumn<Record, String> fName;

    @FXML
    private TableColumn<Record, Double> nQuantity;

    @FXML
    private TableView<Record> table;

    @FXML
    private TableColumn<Record, Double> total;

    @FXML
    private TableColumn<Record, Double> uPrice;



    @FXML
    double blnce;


    DBConnector connectNow = new DBConnector();
    Connection connectDB = connectNow.getConnection();
    PreparedStatement pStatement;

    ResultSet rs,rs2;

    ObservableList<Record> detail = FXCollections.observableArrayList();

    @FXML
    void back(ActionEvent event) {
        FxmlLoader.changeScene(event,"SelectBill.fxml");

    }

    @FXML
    void search(ActionEvent event) {
        table.getItems().clear();
        if (!invoice.getText().isBlank()) {


            try {
                pStatement = connectDB.prepareStatement("SELECT * FROM billdetail WHERE invoice='"+invoice.getText()+"'");
                rs =  pStatement.executeQuery();


                if (rs.next()) {
                    invoiceNumber.setText(rs.getString("invoice"));
                    customerName.setText(rs.getString("customerName"));
                    givenMoney.setText("$"+rs.getString("givenMoney"));
                    mobileNumber.setText("0"+rs.getString("mobileNumber"));
                    foodPrice.setText("$"+rs.getString("totalMoney"));
                    blnce = (rs.getDouble("givenMoney") )- (rs.getDouble("totalMoney"));
                    balance.setText("$"+String.format("%.2f",blnce));
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

            try {


                String connectQuery = "SELECT * FROM `" + invoice.getText() +"`";

                Statement statement2;
                statement2 = connectDB.createStatement();
                ResultSet rs2 = statement2.executeQuery(connectQuery);

                while (rs2.next()) {
                    detail.add(new Record(rs2.getString("foodName"), rs2.getDouble("price"), rs2.getDouble("quantity"), rs2.getDouble("total")));


                }


                fName.setCellValueFactory(new PropertyValueFactory<>("foodName"));

                uPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                nQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                total.setCellValueFactory(new PropertyValueFactory<>("total"));

                table.setItems(detail);
            }catch (Exception e){
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}

