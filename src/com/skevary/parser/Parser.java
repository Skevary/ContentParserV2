package com.skevary.parser;

import com.skevary.view.OverviewController;
import javafx.application.Platform;

public abstract class Parser implements Runnable {
    private OverviewController controller;
    private Thread thread;
    private String url;
    private String path;

    /** The default constructor */
    public Parser() {
        this.url = "Empty URL";
        this.path = "Empty Path";
    }
    /**
     * Constructor with parameters
     *
     * @param url the target address
     * @param path the local path on disk
     * @param controller to update FXML form in runtime
     */
    public Parser(String url, String path, OverviewController controller) {
        this.url = url;
        this.path = path;
        this.controller = controller;
    }

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
                controller.updateAreaLog("The download has been completed!\n");
                controller.stopParserButton();
            }
        });
    }
    /** @return parser - url address*/
    public String getUrl() { return url; }

    /** @return parser - local path*/
    public String getPath() { return path; }

    /** @return parser - OverviewController*/
    public OverviewController getController() { return controller; }

    /** @return parser - thread*/
    public Thread getThread() { return thread; }
}
