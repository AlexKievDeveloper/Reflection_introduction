package com.glushkov.reflection;

public class EntityWithPrivateFields {

    private final int age;
    private final String name;

    public EntityWithPrivateFields(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
