package com.skevary.parser;

public class NativeParser extends Parser{
    @Override
    public void run() {
        System.out.println("This's Native Parser");
        if (getThread().isInterrupted()) stop();
    }
}
