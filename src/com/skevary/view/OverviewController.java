package com.skevary.view;

import com.skevary.parser.JsoupParser;
import com.skevary.parser.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;

public class OverviewController {
    @FXML private TextField fieldUrl;
    @FXML private ToggleButton buttonStart;
    @FXML private TextField fieldPath;
    @FXML private Button buttonPath;
    @FXML private TextArea areaLog;
    @FXML private ProgressBar progressBar;

    private Parser parser;

    @FXML private void handleButtonStart() {
        if (buttonStart.isSelected()) {
            buttonStart.setText("Stop");
            parser = new JsoupParser(fieldUrl.getText(), fieldPath.getText(), areaLog, progressBar);
            parser.start();
            buttonPath.setDisable(true);
        } else {
            buttonStart.setText("Start");
            parser.stop();
            buttonPath.setDisable(false);
        }
    }

    @FXML private void handleButtonPath() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select directory");
        directoryChooser.setInitialDirectory(new File("resources"+File.separator+"content"));
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) fieldPath.setText(selectedDirectory.getAbsolutePath());
        else selectedDirectory = null;
    }

    @FXML private void handleExit() { System.exit(0); }

    @FXML private void handleClear() { areaLog.clear(); }

    @FXML private void showInExplorer() throws IOException {
        Runtime.getRuntime().exec("explorer.exe /select," + fieldPath.getText());
    }
}
