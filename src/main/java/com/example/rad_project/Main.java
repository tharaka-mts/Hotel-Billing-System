package com.example.rad_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.jpg")));

    }

    public static void main(String[] args) {
        launch();
    }
}