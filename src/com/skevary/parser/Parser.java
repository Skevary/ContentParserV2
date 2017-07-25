package com.skevary.parser;

import com.skevary.view.OverviewController;
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class Parser implements Runnable {
    private OverviewController controller;
    private Thread thread;
    private String url;
    private String path;
    private boolean isStop;

    /** The default constructor */
    public Parser() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("The default constructor is not supported.");
    }

    /** The constructor with controller
     *
     * @param controller to update FXML form in runtime
     */
    public Parser(OverviewController controller) {
        this.controller = controller;
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
        isStop = false;
        thread.start();
    }

    /** Stop operation of the parser. */
    public void stop() {
        isStop = true;
    }


    /** @return url - target address*/
    public String getUrl() { return url; }

    /** @return path - to local directory */
    public String getPath() { return path; }

    /** @return controller - instant overview controller */
    public OverviewController getController() { return controller; }

    /** @return thread - parser thread */
    public Thread getThread() { return thread; }

    /** @return isStop - boolean stop flag */
    public boolean getFlag() { return isStop; }
}
