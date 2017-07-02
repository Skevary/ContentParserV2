package com.skevary.parser;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

public abstract class Parser implements Runnable {
    private TextArea areaLog;
    private ProgressBar progressBar;
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
            progressBar.setProgress(0);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public TextArea getAreaLog() { return areaLog; }

    public void setAreaLog(TextArea areaLog) { this.areaLog = areaLog; }

    public ProgressBar getProgressBar() { return progressBar; }

    public void setProgressBar(ProgressBar progressBar) { this.progressBar = progressBar; }

    public Thread getThread() { return thread; }
}
