package com.example.rad_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;
import java.sql.*;


public class Login {
    @FXML
    private Button cancel;

    @FXML
    private Button login;
    @FXML
    private ImageView imageView;

    @FXML
    Stage stage;

    @FXML
    private Pane scenePane;

    @FXML
    private Label loginMassage;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username,password2;
    @FXML
    String pwd ;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    Image showImage = new Image("show.png");
    Image hideImage = new Image("hide.png");

    //show password
    public void show() {
        imageView.setImage(showImage);
        password2.setText(password.getText());
        password2.setVisible(true);
        password.setVisible(false);

    }
    //hide password
    public void hide() {

        imageView.setImage(hideImage);
        password.setText(password2.getText());
        password.setVisible(true);

    }
//check login
    @FXML
    void loginButton(ActionEvent event) {

        if (!username.getText().isBlank() && !password.getText().isBlank()) {
            try {
                DBConnector connectNow = new DBConnector();
                Connection connectDB = connectNow.getConnection();
                pst = connectDB.prepareStatement("select * from cashier where username = ? and password = ?");
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());

                rs =  pst.executeQuery();


                if (rs.next()) {
                    if(username.getText().equals("Admin")){
                        FxmlLoader.changeScene(event,"SelectOption.fxml");
                    }
                    else {
                        FxmlLoader.changeScene(event,"Cssh_Selection.fxml");
                    }

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Login");
                    alert.setHeaderText("Invalid Username or Password");
                    alert.setContentText("Please Enter Correct Username or Password");

                    if (alert.showAndWait().get() == ButtonType.OK){
                        FxmlLoader.changeScene(event,"hello-view.fxml");
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Please Enter Username and Password");


            if (alert.showAndWait().get() == ButtonType.OK){
                FxmlLoader.changeScene(event,"hello-view.fxml");
            }


        }
    }




    public void reset(ActionEvent event){
        username.setText(null);
        password.setText(null);
    }


}
