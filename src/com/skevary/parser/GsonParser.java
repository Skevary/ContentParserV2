package com.skevary.parser;

public class GsonParser extends Parser{
    @Override
    public void run() {
        System.out.println("This's Gson Parser");
        if (getThread().isInterrupted()) stop();
    }
}
