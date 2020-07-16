package com.glushkov.reflection;

import java.io.Closeable;
import java.io.IOException;

public class EntityWithInterfaceImplementation implements Closeable {
    @Override
    public void close() throws IOException {
    }
}
