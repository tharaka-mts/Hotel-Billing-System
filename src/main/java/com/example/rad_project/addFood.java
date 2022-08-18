package com.example.rad_project;

import com.example.rad_project.entity.Food;
import com.example.rad_project.util.FoodUtil;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.example.rad_project.DBConnector.dbLink;

public class addFood implements Initializable {

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private TextField fName;

    @FXML
    private TableColumn<foodRecord, String> fPrice;

    @FXML
    private TableColumn<foodRecord, String> iName;
    @FXML
    private ListView<String> listView;

    @FXML
    private TextField price;

    @FXML
    private Button save;

    @FXML
    private Button search;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Record> table;

    @FXML
    String currentFood, iFName,foodPrice;
    Double iFPrice;

    private static final SessionFactory SESSION_FACTORY = FoodUtil.getSessionFactory();

    ObservableList<Record> obList2 = FXCollections.observableArrayList();

    DBConnector connectNow = new DBConnector();
    Connection connectDB = connectNow.getConnection();
    String connectQuery = "SELECT * FROM food";

    ArrayList<String> words = new ArrayList<String>();
    Statement statement,statement2,statement3;

    {
        try {
            statement = connectDB.createStatement();
            ResultSet rs3 = statement.executeQuery(connectQuery);
            while(rs3.next()){

                words.add(rs3.getString("foodName"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(ActionEvent event) {

        if (!fName.getText().isBlank() && !price.getText().isBlank()) {

            if (currentFood==null) {   // check correct button


                iFName = fName.getText();
                iFPrice = Double.valueOf(price.getText());


                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Successfully Insert Food");


                if (alert.showAndWait().get() == ButtonType.OK) {

                    try {
                        Session session = SESSION_FACTORY.openSession();    //add data to food table
                        Food food = new Food();
                        food.setFoodName(iFName);
                        food.setFoodPrice(iFPrice);

                        session.save(food);
                        session.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                obList2.add(new foodRecord(iFName, iFPrice));
                iName.setCellValueFactory(new PropertyValueFactory<>("foodName"));  //set tableview value

                fPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                table.setItems(obList2);
                fName.setText(null);
                price.setText(null);

            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wrong");
                alert.setHeaderText("You Clicked Wrong Button");


                if (alert.showAndWait().get() == ButtonType.OK){

                }
            }


        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Please Fill the Food Name and Food Price");


            if (alert.showAndWait().get() == ButtonType.OK){
                FxmlLoader.changeScene(event,"addFood.fxml");
            }
        }






    }


    @FXML
    void add(ActionEvent event) {    // add food to tableview


        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput.next()){

                String foodName = queryOutput.getString("foodName");
                Double foodPrice;

                if (foodName.equals(currentFood)) {
                    table.getItems().clear();

                    ResultSet rs= dbLink.createStatement().executeQuery("SELECT * FROM food WHERE foodName ='"+currentFood+"'");
                    foodPrice = queryOutput.getDouble("foodPrice");
                    while (rs.next()){
                        obList2.add(new foodRecord(rs.getString("foodName"),rs.getDouble("foodprice")));


                    }

                    iName.setCellValueFactory(new PropertyValueFactory<>("foodName"));

                    fPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


                    table.setItems(obList2);
                    fName.setText(foodName);
                    price.setText(String.valueOf(foodPrice));

                }


            }

            searchBar.setText(null);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void delete(ActionEvent event) {   //delete food

            try {

                    String sql3 = "DELETE FROM food WHERE foodName='" + fName.getText() + "'";
                    statement3 = connectDB.prepareStatement(sql3);

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(fName.getText()+" will be deleted");


                if (alert.showAndWait().get() == ButtonType.OK) {

                    statement3.executeUpdate(sql3);
                    FxmlLoader.changeScene(event, "addFood.fxml");
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }




    }

    @FXML
    void reset(ActionEvent event) {
        FxmlLoader.changeScene(event,"addFood.fxml");

    }

    @FXML
    void back(ActionEvent event) {
        FxmlLoader.changeScene(event,"SelectOption.fxml");

    }

    @FXML
    void update(ActionEvent event) {
      try {
          if (fName.getText()!=null&&price.getText()!=null) {
              String sql = "UPDATE food SET foodName='" + fName.getText() + "',foodPrice='" + price.getText() + "' WHERE foodName='" + currentFood + "'";
              statement2 = connectDB.prepareStatement(sql);
              statement2.executeUpdate(sql);
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Success");
              alert.setHeaderText("Successfully Updated");


              if (alert.showAndWait().get() == ButtonType.OK) {
                  table.getItems().clear();


                  ResultSet rs2 = dbLink.createStatement().executeQuery("SELECT * FROM food WHERE foodName ='" + fName.getText() + "'");
                  while (rs2.next()) {
                      obList2.add(new foodRecord(rs2.getString("foodName"), rs2.getDouble("foodPrice")));

                  }

                  iName.setCellValueFactory(new PropertyValueFactory<>("foodName"));

                  fPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


                  table.setItems(obList2);
                  fName.setText(null);
                  price.setText(null);


              }
          }else {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Wrong");
              alert.setHeaderText("You Clicked Wrong Button");


              if (alert.showAndWait().get() == ButtonType.OK) {

              }


          }
      } catch (SQLException e) {
          e.printStackTrace();
          e.getCause();
      }



    }

    @FXML
    void search(ActionEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(),words));
        listView.getItems().addAll(words);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentFood = listView.getSelectionModel().getSelectedItem();
                searchBar.setText(currentFood);
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


}
