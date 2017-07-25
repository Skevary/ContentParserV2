package com.skevary.parser;

import com.skevary.view.OverviewController;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsoupParser extends Parser {
    /**
     * Constructor with parameters
     *
     * @param url the target address
     * @param path the local path on disk
     * @param controller to update FXML form in runtime
     */
    public JsoupParser(String url, String path, OverviewController controller) {
        super(url, path, controller);
    }

    /** Script which gets links and saves the content to disk */
    @Override public void run() {
            try {
                Elements links = Jsoup.connect(getUrl()).get().select("a[href$=.webm]");
                for (int i = 0; !getFlag() && i < links.size(); i++) {
                    Element link = links.get(i);
                    String fileName = link.text();
                /* Absolute path links */
                    URL absUrl = new URL(link.absUrl("abs:href"));
                /* New file */
                    File file = new File(getPath() + File.separator + fileName);
                /* Save file on are disk */
                    if (!file.exists()) {
                        FileUtils.copyURLToFile(absUrl, file);
                        getController().updateAreaLog("File \"" + fileName + "\" is successfully loaded.\n");
                        getController().updateCounterFiles(i + " / " + (links.size() - 1));
                    }
                    getController().updateProgressBar((double) i / links.size());
                }
                /* The end of the download*/
                getController().updateAreaLog("The end of the download!\n");
                getController().stopParserButton();

            } catch ( RuntimeException | IOException  e) {
                getController().updateAreaLog(e.getMessage() + "\n");
                e.printStackTrace();
            }
        }
}
