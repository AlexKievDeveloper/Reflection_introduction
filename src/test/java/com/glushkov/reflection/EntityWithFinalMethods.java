package com.glushkov.reflection;

public class EntityWithFinalMethods {
    int count;
    String name;

    final int getCount() {
        return count;
    }

    String getName() {
        return name;
    }
}
