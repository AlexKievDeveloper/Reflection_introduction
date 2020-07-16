package com.glushkov.reflection;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Test
    public void getObjectFromClass() {
        //prepare
        String expected = "class java.util.ArrayList";

        //when
        String actual = Reflection.getObjectFromClass(ArrayList.class).getClass().toString();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void invokeAllMethodsWithoutParam() {
        //prepare
        Reflection reflection = new Reflection();
        EntityWithMethodsWithoutParams entityWithMethodsWithoutParams = new EntityWithMethodsWithoutParams();

        //when
        reflection.invokeAllMethodsWithoutParams(entityWithMethodsWithoutParams);

        //then
        assertFalse(entityWithMethodsWithoutParams.m1Called);
        assertFalse(entityWithMethodsWithoutParams.m2Called);
        assertTrue(entityWithMethodsWithoutParams.m3Called);
        assertTrue(entityWithMethodsWithoutParams.m4Called);
    }

    @Test
    public void printAllFinalMethods() {
        //prepare
        String expected = "final int com.glushkov.reflection.EntityWithFinalMethods.getCount()\n";
        System.setOut(new PrintStream(output));
        Reflection reflection = new Reflection();
        EntityWithFinalMethods entityWithFinalMethods = new EntityWithFinalMethods();

        //when
        reflection.printAllFinalMethods(entityWithFinalMethods);

        //then
        for (int i = 0; i < expected.getBytes().length; i++) {
            assertEquals(expected.getBytes()[i], output.toByteArray()[i]);
        }
        System.setOut(null);
    }

    @Test
    public void printAllNotPublicMethods() {
        //prepare
        String expected = "private void com.glushkov.reflection.EntityWithNotPublicMethods.testMethod1()\n" +
                "protected void com.glushkov.reflection.EntityWithNotPublicMethods.testMethod2()\n" +
                "void com.glushkov.reflection.EntityWithNotPublicMethods.testMethod3()\n";
        System.setOut(new PrintStream(output));
        Reflection reflection = new Reflection();

        //when
        reflection.printAllNotPublicMethods(EntityWithNotPublicMethods.class);

        //then
        for (int i = 0; i < expected.getBytes().length; i++) {
            assertEquals(expected.getBytes()[i], output.toByteArray()[i]);
        }
        System.setOut(null);
    }

    @Test
    public void printAllParentsAndImpInterfaces() {
        //prepare
        String expected = "java.lang.Object\ninterface java.io.Closeable\n";
        System.setOut(new PrintStream(output));
        Reflection reflection = new Reflection();

        //when
        reflection.printAllParentsAndImpInterfaces(EntityWithInterfaceImplementation.class);

        //then
        for (int i = 0; i < expected.getBytes().length; i++) {
            assertEquals(expected.getBytes()[i], output.toByteArray()[i]);
        }
        System.setOut(null);
    }

    @Test
    public void changeAllPrivateFieldsValuesToNull() {
        //prepare
        EntityWithPrivateFields entityWithPrivateFields = new EntityWithPrivateFields(50, "Nikola");

        //when
        Reflection.changeAllPrivateFieldsValuesToNull(entityWithPrivateFields);

        //then
        assertEquals(0, entityWithPrivateFields.getAge());
        assertEquals(null, entityWithPrivateFields.getName());
    }

    @Test
    public void getDefaultValue() {
        //when
        char actualChar = Reflection.getDefaultValue(char.class);
        double actualDouble = Reflection.getDefaultValue(double.class);
        String actualString = Reflection.getDefaultValue(String.class);

        //then
        assertEquals('\u0000', actualChar);
        assertEquals(0, actualDouble);
        assertNull(actualString);
    }
}