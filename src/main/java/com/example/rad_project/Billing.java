package com.example.rad_project;



import com.example.rad_project.entity.Bill;

import com.example.rad_project.entity.BillDetail;
import com.example.rad_project.entity.CustomerDetail;
import com.example.rad_project.util.BillDetailUtil;
import com.example.rad_project.util.BillUtil;

import com.example.rad_project.util.CustomerDetailUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.sql.*;

import static com.example.rad_project.DBConnector.dbLink;

public class Billing implements Initializable {
    @FXML
    private Label totBill;

    @FXML
    private TextField searchBar,searchQ,cName,gMoney,telNo;

    @FXML
    private TableView<Record> table;


    @FXML
    private TableColumn<Record, String> iName;

    @FXML
    private TableColumn<Record, Double> noOfQuanti;

    @FXML
    private TableColumn<Record, Double> total;

    @FXML
    private TableColumn<Record, Double> uPrice;

//    @FXML
//    private Label myLabel;

    @FXML
    private ListView<String> listView;

    @FXML
    String currentFood, quanti,fPrice,tTot;

    @FXML
    double price,tot,givenMoney,sum;

    @FXML
    int mNumber;

    long invoice2;

    @FXML
    String customerName,summation;

    PreparedStatement preparedStatement,preparedStatement2;

    private static final SessionFactory SESSION_FACTORY = BillUtil.getSessionFactory();
    private static final SessionFactory SESSION_FACTORY2 = CustomerDetailUtil.getSessionFactory();
    private static final SessionFactory SESSION_FACTORY3 = BillDetailUtil.getSessionFactory();

    ObservableList<Record> obList = FXCollections.observableArrayList();


    DBConnector connectNow = new DBConnector();
    Connection connectDB = connectNow.getConnection();
    String connectQuery = "SELECT * FROM food";

    ArrayList<Double> totList = new ArrayList<>();
    ArrayList<String> words = new ArrayList<>();

    Statement statement3;


    {
        try {
            statement3 = connectDB.createStatement();
            ResultSet rs3 = statement3.executeQuery(connectQuery);
            while(rs3.next()){

                words.add(rs3.getString("foodName"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void search(ActionEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(),words));  //set values for list view
        listView.getItems().addAll(words);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentFood = listView.getSelectionModel().getSelectedItem();
                searchBar.setText(currentFood);                                             //get selected food
                listView.getItems().clear();
            }
        });

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(words);
        listView.getItems().clear();

    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));


        return listOfStrings.stream().filter(input -> {//input = test
            return searchWordsArray.stream().allMatch(word ->//word=te
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }



    public void add(ActionEvent event) {

         quanti = searchQ.getText();  //get quantity

        try {
        Statement statement = connectDB.createStatement();
        ResultSet queryOutput = statement.executeQuery(connectQuery);
        double totalBill =0;
        while (queryOutput.next()){

            String foodName = queryOutput.getString("foodName");
            Double foodPrice = queryOutput.getDouble("foodPrice");

            price =foodPrice;

           double quant = Double.parseDouble(quanti);

            tot = Double.parseDouble(String.format("%.2f",price*quant));
            fPrice = String.format("%.2f",price);
            tTot = String.format("%.2f",tot);

            try {
                ResultSet rs4= dbLink.createStatement().executeQuery("SELECT * FROM billdetail");

                while (rs4.next()){

                    invoice2 = rs4.getInt("invoice");    //get invoice number

                    invoice2+=1;
                }


                String sql3 = "CREATE TABLE `" + invoice2 +"` ("
                        + "id INT(64) NOT NULL AUTO_INCREMENT,"
                        + "foodName VARCHAR(100),"                          //create table for invoice
                        + "price DOUBLE,"
                        + "quantity DOUBLE, "
                        + "total DOUBLE,"
                        + "PRIMARY KEY(id))";

                preparedStatement = dbLink.prepareStatement(sql3);

                preparedStatement.executeUpdate(sql3);


            }catch (Exception e){
                e.printStackTrace();
            }


            if (foodName.equals(currentFood)) {
                   table.getItems().clear();


                    Session session = SESSION_FACTORY.openSession();
                    Bill bill = new Bill();
                    bill.setFoodName(currentFood);
                    bill.setQuantity(Integer.parseInt(quanti));
                    bill.setPrice(price);
                    bill.setTotal(tot);

                    session.save(bill);
                    session.close();


                    //invoice
                try {
                    String sql4 = "INSERT INTO `" + invoice2 + "` (foodName,price,quantity,total) VALUES ('" + currentFood + "'," +
                            "'" + price + "','" + Double.parseDouble(quanti) + "','" + tot + "')";    //add values
                    preparedStatement2 = dbLink.prepareStatement(sql4);
                    preparedStatement.executeUpdate(sql4);

                }catch (Exception e){
                    e.printStackTrace();
                }

                       ResultSet rs= dbLink.createStatement().executeQuery("SELECT * FROM bill");

                       while (rs.next()){
                           obList.add(new Record(rs.getString("foodName"),
                                   rs.getDouble("price"),(double) rs.getInt("quantity"),
                                   rs.getDouble("total") ));

                       }

                   iName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
                   noOfQuanti.setCellValueFactory(new PropertyValueFactory<>("quantity")); //set values for table view
                   uPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                   total.setCellValueFactory(new PropertyValueFactory<>("total"));

                   table.setItems(obList);


                       totList.add(tot);


               }


        }
            searchBar.setText(null);
            searchQ.setText(null);


            for(int i =0;i< totList.size();i++){
                totalBill += totList.get(i);
            }
            summation = String.format("%.2f",totalBill);    //calculate total bill

            totBill.setText("$"+String.format("%.2f",totalBill));



    } catch (SQLException e) {
        e.printStackTrace();
    }


    }



    public void close(ActionEvent event) {
        FxmlLoader.changeScene(event,"SelectBill.fxml");
        PreparedStatement preparedStatement;

        try {
            String sql ="DELETE FROM bill;";
            preparedStatement = dbLink.prepareStatement(sql);

            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void next(ActionEvent event) {
        String checkNumber = telNo.getText();
        if (checkNumber.length()==10){

        if (!cName.getText().isBlank() && !telNo.getText().isBlank() && !totBill.getText().isBlank() && !gMoney.getText().isBlank()) {
            customerName = cName.getText();
            mNumber = Integer.parseInt(telNo.getText());
            givenMoney = Double.parseDouble(gMoney.getText());
            sum = Double.parseDouble(summation);

            if (givenMoney >= sum) { //check valid mobile number

                try {

                    Session session = SESSION_FACTORY2.openSession();
                    CustomerDetail customerDetail = new CustomerDetail();
                    customerDetail.setCustomerName(customerName);
                    customerDetail.setMobileNumber(mNumber);
                    customerDetail.setGivenMoney(givenMoney);
                    customerDetail.setTotalMoney(sum);


                    session.save(customerDetail);
                    session.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    Session session = SESSION_FACTORY3.openSession();
                    BillDetail billDetail = new BillDetail();
                    billDetail.setCustomerName(customerName);
                    billDetail.setMobileNumber(mNumber);
                    billDetail.setGivenMoney(givenMoney);

                    billDetail.setTotalMoney(sum);


                    session.save(billDetail);
                    session.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }


                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Successfully Update");


                if (alert.showAndWait().get() == ButtonType.OK) {

                    FxmlLoader.changeScene(event, "Bill_View.fxml");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("Your given money is not enough to pay bill");


                if (alert.showAndWait().get() == ButtonType.OK) {
                    gMoney.setText(null);

                }


            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Please Fill The All Fields");


            if (alert.showAndWait().get() == ButtonType.OK) {

                FxmlLoader.changeScene(event, "Billing.fxml");
            }
        }

    }else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Please Enter Valid Telephone Number");


            if (alert.showAndWait().get() == ButtonType.OK) {
                telNo.setText(null);
            }

        }
    }

}





