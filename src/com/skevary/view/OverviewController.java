package com.skevary.view;

import com.skevary.parser.GsonParser;
import com.skevary.parser.JsoupParser;
import com.skevary.parser.NativeParser;
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
    @FXML private TextArea areaLog;
    @FXML private ProgressBar progressBar;
    @FXML private Text textCounterFiles;

    private Parser parser;

    @FXML private void handleButtonStart() {
        if (parserButton.isSelected())
            if (!fieldUrl.getText().isEmpty() && !fieldPath.getText().isEmpty()) {
                startParserButton();}
            else {
                parserButton.setSelected(false);
                areaLog.setText("Please fill in all the fields!\n");}
        else stopParserButton();
    }

    @FXML private void handleButtonPath() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select directory");
        directoryChooser.setInitialDirectory(new File("resources"+File.separator+"content"));
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) fieldPath.setText(selectedDirectory.getAbsolutePath());
    }

    @FXML private void handleExit() { System.exit(0); }

    @FXML private void handleClear() { areaLog.clear(); }

    @FXML private void showInExplorer() {
        try {
            Runtime.getRuntime().exec("explorer.exe /select," + fieldPath.getText());
        } catch (IOException e) {
            updateAreaLog(e.getMessage());
        }
    }

    public void updateAreaLog(String message){ areaLog.appendText(message); }

    public void updateProgressBar(Double value){ progressBar.setProgress(value); }

    public void updateCounterFiles(String counter){ textCounterFiles.setText(counter); }

    private void startParserButton(){
        parserButton.setText("Stop");
        String selected = (String) parserGroup.getSelectedToggle().getUserData();
        switch (selected) {
            case "Jsoup":
                parser = new JsoupParser(fieldUrl.getText(), fieldPath.getText().trim(), this);
                break;
            case "Gson":
                parser = new GsonParser(this);
                break;
            case "Native":
                parser = new NativeParser(this);
                break;
            default:
                System.out.println("Wrong choice!");
        }
        parser.start();
    }

    public void stopParserButton(){
        parser.stop();
        parserButton.setText("Start");
        updateProgressBar(0.0);
        updateAreaLog("The end of the download!\n");
        parserButton.setSelected(false);
    }
}
