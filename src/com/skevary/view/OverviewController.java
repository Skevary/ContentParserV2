package com.skevary.view;

import com.skevary.parser.JsoupParser;
import com.skevary.parser.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;

public class OverviewController {
    @FXML private ToggleGroup parserGroup;
    @FXML private TextField fieldUrl;
    @FXML private ToggleButton parserButton;
    @FXML private TextField fieldPath;
    @FXML private Button pathButton;
    @FXML private TextArea areaLog;
    @FXML private ProgressBar progressBar;
    @FXML private Text textCounterFiles;

    private Parser parser;

    @FXML private void handleButtonStart() {
        if (parserButton.isSelected())
            startParserButton();
        else
            stopParserButton();
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

    public void updateAreaLog(String message){ areaLog.appendText(message); }

    public void updateProgressBar(Double value){ progressBar.setProgress(value); }

    public void updateCounterFiles(String counter){ textCounterFiles.setText(counter); }
    
    public void startParserButton(){
        parserButton.setText("Stop");
        parser = new JsoupParser(fieldUrl.getText(), fieldPath.getText(),this);
        parser.start();
    }
    public void stopParserButton(){
        parser.stop();
        parserButton.setText("Start");
        parserButton.setSelected(false);
    }
}
