package com.skevary.parser;

import com.skevary.view.OverviewController;
import javafx.application.Platform;

public abstract class Parser implements Runnable {
    private OverviewController controller;
    private Thread thread;
    private String url;
    private String path;

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        try {
            thread.interrupt();
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void endDownload(){
        Platform.runLater(new Runnable() {
            public void run() {
                getController().updateAreaLog("The download has been completed!\n");
                getController().updateProgressBar(0.0);
                getController().stopParserButton();
            }
        });
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public OverviewController getController() { return controller; }

    public void setController(OverviewController controller) { this.controller = controller; }

    public Thread getThread() { return thread; }
}
