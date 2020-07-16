package com.glushkov.reflection;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class AdditionalClassForRedirectingOutputStream {
    protected final ByteArrayOutputStream output = new ByteArrayOutputStream();

    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    public void cleanUpStreams() {
        System.setOut(null);
    }
}
