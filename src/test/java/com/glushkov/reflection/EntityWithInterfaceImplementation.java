package com.glushkov.reflection;

import java.io.Closeable;

public class EntityWithInterfaceImplementation implements Closeable {
    @Override
    public void close() {
    }
}
