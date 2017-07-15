package com.skevary.parser;

import com.skevary.view.OverviewController;

public class NativeParser extends Parser {
    /** The constructor with controller
     *
     * @param controller to update FXML form in runtime
     */
    public NativeParser(OverviewController controller) {
        super(controller);
    }

    @Override public void run() {
        System.out.println("This's Native Parser");
        // Print in Area Log
        getController().updateAreaLog("*****************************************\n");
        getController().updateAreaLog("The implementation is temporarily absent!\n");
        getController().updateAreaLog("\n");
        getController().updateAreaLog("Example of similar implementation, you can watch in the\n");
        getController().updateAreaLog("old version of the app on my profile in GitHub: \n");
        getController().updateAreaLog("https://github.com/Skevary/ContentParser/\n");
        getController().updateAreaLog("*****************************************\n");
        // Stop current thread
        if (getThread().isInterrupted()) stop();
    }
}
