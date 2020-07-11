package com.glushkov;

public class PersonForTest {

    private final int age;
    private final String name;

    public PersonForTest(int age, String name) {
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
