package com.skevary.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RootController {
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Website Webm Parser");
        alert.setHeaderText("About");
        alert.setContentText(
                "Author: Alexander Raievskyi\nFeedback: araievskyi@gmail.com");
        alert.showAndWait();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
