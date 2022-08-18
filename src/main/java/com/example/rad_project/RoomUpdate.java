package com.example.rad_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class RoomUpdate {

    @FXML
    private TextField d_fb;

    @FXML
    private TextField d_hb;

    @FXML
    private TextField d_pn;

    @FXML
    private TextField d_ra;

    @FXML
    private TextField d_wb;

    @FXML
    private TextField f_fb;

    @FXML
    private TextField f_hb;

    @FXML
    private TextField f_pn;

    @FXML
    private TextField f_ra;

    @FXML
    private TextField f_wb;

    @FXML
    private Label output;

    @FXML
    private TextField s_fb;

    @FXML
    private TextField s_hb;

    @FXML
    private TextField s_pn;

    @FXML
    private TextField s_ra;

    @FXML
    private TextField s_wb;

    @FXML
    private TextField roomtypefield;

    PreparedStatement pst,pst2,pst3;
    ResultSet rs,rs2,rs3;
    PreparedStatement preparedStatement = null;

    DBConnector connectNow = new DBConnector();
    Connection connectDB = connectNow.getConnection();

    @FXML
    void classic(ActionEvent event) {
            display("classic");
    }

    @FXML
    void deluxe(ActionEvent event) {
            display("deluxe");
    }

    @FXML
    void presidential(ActionEvent event) {
            display("presidential");
    }


    @FXML
    void update(ActionEvent event) {
        String roomForUpdate = roomtypefield.getText();
        priceUpdate(roomForUpdate);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Successfully Update");



        if (alert.showAndWait().get() == ButtonType.OK){
            FxmlLoader.changeScene(event,"RoomUpdate.fxml");
        }

    }

    //FOR ROOM PRICE UPDATE
    public void priceUpdate(String roomTypeUpdate){
        try {

            String sql = "UPDATE hotelrooms SET perNight='"+ s_pn.getText() +"', withBreakfast='"+ s_wb.getText()+"',hBoard='"+ s_hb.getText()+"',fBoard='"+ s_fb.getText()+"',currentAvailability='"+ s_ra.getText()+"' WHERE roomType='"+roomTypeUpdate+"' AND rooms='single'";
            String sql2 = "UPDATE hotelrooms SET perNight='"+ d_pn.getText() +"', withBreakfast='"+ d_wb.getText()+"',hBoard='"+ d_hb.getText()+"',fBoard='"+ d_fb.getText()+"',currentAvailability='"+ d_ra.getText()+"' WHERE roomType='"+roomTypeUpdate+"' AND rooms='double'";

            String sql3 = "UPDATE hotelrooms SET perNight='"+ f_pn.getText() +"', withBreakfast='"+ f_wb.getText()+"',hBoard='"+ f_hb.getText()+"',fBoard='"+ f_fb.getText()+"',currentAvailability='"+ f_ra.getText()+"' WHERE roomType='"+roomTypeUpdate+"' AND rooms='family'";


            preparedStatement = connectDB.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);

            preparedStatement = connectDB.prepareStatement(sql2);
            preparedStatement.executeUpdate(sql2);

            preparedStatement = connectDB.prepareStatement(sql3);
            preparedStatement.executeUpdate(sql3);





        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

    }


    //FOR ROOM PRICE SEARCH
    public void display(String roomType){

        try {

            pst = connectDB.prepareStatement("SELECT * FROM hotelrooms WHERE roomType='"+roomType+"' AND rooms='single'");
            rs =  pst.executeQuery();

            roomtypefield.setText(roomType);

            if (rs.next()) {
                s_pn.setText(rs.getString("perNight"));
                s_wb.setText(rs.getString("withBreakfast"));
                s_hb.setText(rs.getString("hBoard"));
                s_fb.setText(rs.getString("fBoard"));
                s_ra.setText(rs.getString("currentAvailability"));


            }

            pst2 = connectDB.prepareStatement("SELECT * FROM hotelrooms WHERE roomType='"+roomType+"' AND rooms='double'");
            rs2 =  pst2.executeQuery();

            if(rs2.next()){
                d_pn.setText(rs2.getString("perNight"));
                d_wb.setText(rs2.getString("withBreakfast"));
                d_hb.setText(rs2.getString("hBoard"));
                d_fb.setText(rs2.getString("fBoard"));
                d_ra.setText(rs2.getString("currentAvailability"));
            }


            pst3 = connectDB.prepareStatement("SELECT * FROM hotelrooms WHERE roomType='"+roomType+"' AND rooms='family'");
            rs3 =  pst3.executeQuery();

            if(rs3.next()){
                f_pn.setText(rs3.getString("perNight"));
                f_wb.setText(rs3.getString("withBreakfast"));
                f_hb.setText(rs3.getString("hBoard"));
                f_fb.setText(rs3.getString("fBoard"));
                f_ra.setText(rs3.getString("currentAvailability"));

            }



        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void back(ActionEvent event) {
        FxmlLoader.changeScene(event,"SelectOption.fxml");
    }

    @FXML
    void searchGuest(ActionEvent event) {
        FxmlLoader.changeScene(event,"GuestSearch.fxml");
    }
}
