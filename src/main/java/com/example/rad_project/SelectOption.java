package com.example.rad_project;

import javafx.event.ActionEvent;

public class SelectOption {
    public void resturent(ActionEvent event) {
        FxmlLoader.changeScene(event,"addFood.fxml");
    }

    public void hotel(ActionEvent event) {
        FxmlLoader.changeScene(event,"RoomUpdate.fxml");
    }

    public void adminDetail(ActionEvent event) {
        FxmlLoader.changeScene(event,"AdminPanel.fxml");
    }

    public void back(ActionEvent event) {
        FxmlLoader.changeScene(event,"hello-view.fxml");
    }
}
