package com.skevary.parser;

import com.skevary.view.OverviewController;
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class Parser implements Runnable {
    OverviewController controller;
    Thread thread;
    String url;
    String path;
    boolean flag;

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
        flag = false;
        thread = new Thread(this,"Parser-Thread");
        thread.start();
    }

    /** Stop operation of the parser. */
    public void stop() {
        flag = true;
    }


    /** @return url - target address*/
    public String getUrl() { return url; }

    /** @return path - to local directory */
    public String getPath() { return path; }

    /** @return controller - instant overview controller */
    public OverviewController getController() { return controller; }

    /** @return thread - parser thread */
    public Thread getThread() { return thread; }

    /** @return flag - boolean stop flag */
    public boolean getFlag() { return flag; }
}
