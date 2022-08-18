package com.example.rad_project;

import com.example.rad_project.entity.Cashier;
import com.example.rad_project.util.CashierUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.*;

public class AddCashier {

    @FXML
    private TextField address;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField email;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField nic;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup gender;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField username;

    @FXML
    private Label label1;

    @FXML
    String gender_chosen,checkUsername;

    @FXML
    private TableColumn<cashierRecord, String> cashierName;

    @FXML
    private TableColumn<cashierRecord, Integer> cashierNumber;

    @FXML
    private TableColumn<cashierRecord, String> cashierUsername;
    @FXML
    private TableView<Record> table;

    DBConnector connectNow = new DBConnector();
    Connection connectDB = connectNow.getConnection();
    String connectQuery = "SELECT firstName,phoneNumber,username FROM cashier ORDER BY cashierId DESC LIMIT 1";


    ObservableList<Record> cashierList = FXCollections.observableArrayList();


    private static final SessionFactory SESSION_FACTORY = CashierUtil.getSessionFactory();


    @FXML
    public void add(ActionEvent event) {

        try {

            PreparedStatement pStatement = connectDB.prepareStatement("SELECT username FROM cashier  WHERE username='" + username.getText() + "'");
            ResultSet rs2 = pStatement.executeQuery();


            if(rs2.next()) {
                checkUsername = rs2.getString("username");
                System.out.println(username.getText());

//check username availability

                if (username.getText().equals(checkUsername)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Username Already Exits");


                    if (alert.showAndWait().get() == ButtonType.OK) {
                        username.setText(null);

                    }
                }


                } else {

                    //check mobile number

                    String checkNumber = phone.getText();
                    if (checkNumber.length()==10){


                    if (!firstname.getText().isBlank() && !lastname.getText().isBlank() && !phone.getText().isBlank() && !email.getText().isBlank() && !nic.getText().isBlank() && !address.getText().isBlank() && !username.getText().isBlank() && !password.getText().isBlank()) {


                        if (male.isSelected()) {
                            gender_chosen = "Male";
                        } else if (female.isSelected()) {
                            gender_chosen = "Female";
                        }

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Successfully Inserted");


                        try {

                            if (alert.showAndWait().get() == ButtonType.OK) {
//add data to cashier table
                                Session session = SESSION_FACTORY.openSession();
                                Cashier cashier = new Cashier();
                                cashier.setFirstName(firstname.getText());
                                cashier.setLastName(lastname.getText());
                                cashier.setDateOfBirth(String.valueOf(dob.getValue()));
                                cashier.setPhoneNumber(Integer.parseInt(phone.getText()));
                                cashier.setEmail(email.getText());
                                cashier.setNic(nic.getText());
                                cashier.setAddress(address.getText());
                                cashier.setGender(gender_chosen);
                                cashier.setUsername(username.getText());
                                cashier.setPassword(password.getText());

                                session.save(cashier);
                                session.close();


                                try {
                                    Statement statement = connectDB.createStatement();
                                    ResultSet rs = statement.executeQuery(connectQuery);
                                    while (rs.next()) {

                                        cashierList.add(new cashierRecord(rs.getString("firstName"), rs.getInt("phoneNumber"), rs.getString("username")));

                                    }

                                    //add value to tableview

                                    cashierName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

                                    cashierNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                                    cashierUsername.setCellValueFactory(new PropertyValueFactory<>("username"));


                                    table.setItems(cashierList);


                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                                username.setText(null);
                                firstname.setText(null);
                                password.setText(null);
                                lastname.setText(null);
                                address.setText(null);
                                email.setText(null);
                                dob.setValue(null);
                                nic.setText(null);
                                phone.setText(null);


                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } else {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Please Fill All The Fields");


                        if (alert.showAndWait().get() == ButtonType.OK) {
                            FxmlLoader.changeScene(event, "AddCashier.fxml");
                        }

                    }

                }else {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Please Enter Valid Telephone Number");


                        if (alert.showAndWait().get() == ButtonType.OK) {
                            phone.setText(null);
                        }

                }

                }


        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @FXML
    public void reset(ActionEvent event) {
        table.setItems(null);
        firstname.setText(null);
        lastname.setText(null);
        dob.setValue(null);
        phone.setText(null);
        email.setText(null);
        nic.setText(null);
        address.setText(null);
        username.setText(null);
        password.setText(null);
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
        FxmlLoader.changeScene(event, "SelectOption.fxml");
    }
}
