package com.example.rad_project;

import javafx.event.ActionEvent;

public class SelectBill {
    public void billDetail(ActionEvent event) {
        FxmlLoader.changeScene(event,"Invoice.fxml");
    }

    public void createBill(ActionEvent event) {
        FxmlLoader.changeScene(event,"Billing.fxml");
    }
    public void back(ActionEvent event) {
        FxmlLoader.changeScene(event,"Cssh_Selection.fxml");
    }
}
