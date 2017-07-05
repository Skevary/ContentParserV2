package com.skevary.parser;

import com.skevary.view.OverviewController;
import javafx.application.Platform;

public abstract class Parser implements Runnable {
    private OverviewController controller;
    private Thread thread;
    private String url;
    private String path;

    /** Start operation of the parser. */
    public void start() {
        thread = new Thread(this);
        thread.start();
    }
    /** Stop operation of the parser. */
    public void stop() {
        try {
            thread.interrupt();
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    /** Update component in {@link OverviewController} at the end of the download files. */
    public void endDownload(){
        Platform.runLater(new Runnable() {
            public void run() {
                getController().updateAreaLog("The download has been completed!\n");
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
