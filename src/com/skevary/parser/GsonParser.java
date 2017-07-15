package com.skevary.parser;

import com.skevary.view.OverviewController;

public class GsonParser extends Parser{
    /** The constructor with controller
     *
     * @param controller to update FXML form in runtime
     */
    public GsonParser(OverviewController controller) {
      super(controller);
    }

    @Override public void run() {
        // Stop current thread
        if (getThread().isInterrupted()) stop();
        System.out.println("This's Gson Parser");
        // Print in Area Log
        getController().updateAreaLog("*****************************************\n");
        getController().updateAreaLog("The implementation is temporarily absent!\n");
        getController().updateAreaLog("\n");
        getController().updateAreaLog("Example of similar implementation, you can watch in the\n");
        getController().updateAreaLog("old version of the app on my profile in GitHub: \n");
        getController().updateAreaLog("https://github.com/Skevary/ContentParser/\n");
        getController().updateAreaLog("*****************************************\n");
    }
}
