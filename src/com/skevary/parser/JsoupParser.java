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
                Elements links = Jsoup.connect(url).get().select("a[href$=.webm]");
                for (int i = 0; !flag && i < links.size(); i++) {
                    Element link = links.get(i);
                    String fileName = link.text();
                    URL address = new URL(link.absUrl("abs:href")); /* Direct link to content file */
                    File file = new File(path + File.separator + fileName);
                    /* Save file on are disk */
                    if (!file.exists()) {
                        FileUtils.copyURLToFile(address, file);
                        controller.updateAreaLog("File \"" + fileName + "\" is successfully loaded.\n");
                        controller.updateCounterFiles(i + " / " + (links.size() - 1));
                    }
                    controller.updateProgressBar((double) i / links.size());
                }
                /* The end of the download*/
                controller.updateAreaLog("The end of the download!\n");
                controller.stopParserButton();

            } catch ( RuntimeException | IOException  e) {
                controller.updateAreaLog(e.getMessage() + "\n");
                e.printStackTrace();
            }
        }
}
