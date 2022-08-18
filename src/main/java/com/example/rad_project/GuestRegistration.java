package com.example.rad_project;


import com.example.rad_project.entity.Bill;
import com.example.rad_project.entity.GuestDetail;
import com.example.rad_project.util.GuestDetailUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;

public class GuestRegistration {

    @FXML
    private DatePicker aDate;

    @FXML
    private ToggleGroup addinfo;

    @FXML
    private RadioButton c_double;

    @FXML
    private RadioButton c_family;

    @FXML
    private RadioButton c_single;

    @FXML
    private RadioButton classic;

    @FXML
    private TextField country;

    @FXML
    private DatePicker dDate;

    @FXML
    private TextField daysOfstay;

    @FXML
    private RadioButton deluxe;

    @FXML
    private TextField email;

    @FXML
    private RadioButton fBoard;

    @FXML
    private TextField fname;

    @FXML
    private RadioButton hBoard;

    @FXML
    private TextField lname;

    @FXML
    private TextField number;

    @FXML
    private Label output;

    @FXML
    private RadioButton perNight;

    @FXML
    private RadioButton presidential;

    @FXML
    private ToggleGroup roomCategory;

    @FXML
    private ToggleGroup roomType;

    @FXML
    private TextField rooms;

    @FXML
    private TextArea sRequirement;

    @FXML
    private Button submit;

    @FXML
    private RadioButton withBreakfast;

    @FXML
    String rm_type,add_info,rm_category;

    @FXML
    int checkAvailibal,avRoom;

    private static final SessionFactory SESSION_FACTORY = GuestDetailUtil.getSessionFactory();


    Connection con;
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

    public void print(ActionEvent event) {



        if (!fname.getText().isBlank() && !lname.getText().isBlank() && !email.getText().isBlank() && !number.getText().isBlank() && !country.getText().isBlank() && !rooms.getText().isBlank() &&!sRequirement.getText().isBlank() &&!String.valueOf(aDate.getValue()).isBlank() &&!String.valueOf(dDate.getValue()).isBlank()) {

            String checkNumber = number.getText();
            if (checkNumber.length()==10){
            try {

                //Selecting the Room type

                if (classic.isSelected()) {
                    rm_type = "classic";

                } else if (deluxe.isSelected()) {
                    rm_type = "deluxe";
                } else if (presidential.isSelected()) {
                    rm_type = "presidential";
                }

                //Selecting the Additional Info


                if (perNight.isSelected()) {
                    add_info = "perNight";
                } else if (withBreakfast.isSelected()) {
                    add_info = "withBreakfast";
                } else if (hBoard.isSelected()) {
                    add_info = "hBoard";
                } else if (fBoard.isSelected()) {
                    add_info = "fBoard";
                }

                //Selecting the Room Category


                if (c_single.isSelected()) {
                    rm_category = "single";
                } else if (c_double.isSelected()) {
                    rm_category = "double";
                } else if (c_family.isSelected()) {
                    rm_category = "family";
                }


                try {

                    DBConnector connectNow = new DBConnector();
                    Connection connectDB = connectNow.getConnection();
                    PreparedStatement pStatement = connectDB.prepareStatement("SELECT * FROM hotelrooms  WHERE roomType='" + rm_type + "' AND rooms='" + rm_category + "'");
                    ResultSet rs3 = pStatement.executeQuery();
                    if (rs3.next()) {
                        checkAvailibal = rs3.getInt("currentAvailability");
                    }

                    avRoom = Integer.parseInt(rooms.getText());

                    if (avRoom <= checkAvailibal) {



                        Session session = SESSION_FACTORY.openSession();
                        GuestDetail guestDetail = new GuestDetail();
                        guestDetail.setFirstName(fname.getText());
                        guestDetail.setLastName(lname.getText());
                        guestDetail.setEmail(email.getText());
                        guestDetail.setPhone(Integer.parseInt(number.getText()));
                        guestDetail.setCountry(country.getText());
                        guestDetail.setNoOfRooms(Integer.parseInt(rooms.getText()));
                        guestDetail.setArrivalDate(String.valueOf(aDate.getValue()));
                        guestDetail.setDepartureDate(String.valueOf(dDate.getValue()));
                        guestDetail.setRoomType(rm_type);
                        guestDetail.setRoomCategory(rm_category);
                        guestDetail.setAdditionalInfo(add_info);
                        guestDetail.setDaysOfstay(Integer.parseInt(daysOfstay.getText()));
                        guestDetail.setSpecialRequirements(sRequirement.getText());

                        session.save(guestDetail);
                        session.close();


                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Update Successfully");


                        if (alert.showAndWait().get() == ButtonType.OK) {

                            FxmlLoader.changeScene(event, "GuestReceipt.fxml");
                        }


                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Sorry,This Roooms Doesn't Available Right Now");


                        if (alert.showAndWait().get() == ButtonType.OK) {

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Please Enter Valid Telephone Number");


            if (alert.showAndWait().get() == ButtonType.OK) {
                number.setText(null);
            }

        }

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Fill all the fields");


            if (alert.showAndWait().get() == ButtonType.OK){

            }

        }

    }
}
