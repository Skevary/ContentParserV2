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
            double step = 0; // Step in the progress bar
            for (Element link : links) {
                /* Tests whether this thread has been interrupted. */
                if (getThread().isInterrupted()) {
                    stop();
                    break;
                }
                String fileName = link.text();
                /* Absolute path links */
                URL absUrl = new URL(link.absUrl("abs:href"));
                /* New file */
                File file = new File(getPath() + File.separator + fileName);
                /* Save file on are disk */
                if (!file.exists()) {
                    FileUtils.copyURLToFile(absUrl, file);
                    getController().updateAreaLog("File \"" + fileName + "\" is successfully loaded\n");
                    getController().updateCounterFiles((int)step + " / " + (links.size()-1));
                }
                step++;
                getController().updateProgressBar(step / links.size());

            }
            /* The end of the download*/
            endDownload();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
