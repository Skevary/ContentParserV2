package com.skevary.parser;

import javafx.scene.control.TextArea;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsoupParser extends Parser {

    public JsoupParser() {
        this.setUrl("Empty URL");
        this.setPath("Empty Path");
    }

    public JsoupParser(String url, String path, TextArea areaLog) {
        this.setUrl(url);
        this.setPath(path);
        this.setAreaLog(areaLog); //The Inject TextArea for to update it in real time from this class.
    }

    @Override
    public void run() {
        try {
            Elements links = Jsoup.connect(getUrl()).get().select("a[href$=.webm]");
            for (Element link : links) {
                if (getThread().isInterrupted()) break;

                String fileName = link.text();
                URL absUrl = new URL(link.absUrl("abs:href"));
                File file = new File(getPath() + File.separator + fileName);
                // Creates a file on disk
                if (!file.exists()) {
                    FileUtils.copyURLToFile(absUrl, file);
                    getAreaLog().appendText("File \"" + fileName + "\" is successfully loaded\n");
                }
            }
            getAreaLog().appendText("The download has been completed!\n");
            stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
