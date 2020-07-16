package com.glushkov.reflection;

public class EntityWithMethodsWithoutParams {
    boolean m1Called;
    boolean m2Called;
    boolean m3Called;
    boolean m4Called;

    void m1(int a) {
        m1Called = true;
    }

    public int m2(double a) {
        m2Called = true;
        return 0;
    }

    void m3() {
        m3Called = true;
    }

    int m4() {
        m4Called = true;
        return 0;
    }
}
