package com.glushkov.annotations;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotationsMethodsTest {

    @Test
    public void injectAnnotation() {
        //prepare
        EntityWithAnnotatedFieldsAndMethods entity = new EntityWithAnnotatedFieldsAndMethods();
        assertEquals(0, entity.getCount());
        //when
        AnnotationsMethods.injectAnnotation(entity);
        //then
        assertEquals(100, entity.getCount());
    }

    @Test
    public void fillInTheFields() {
        //prepare
        EntityWithAnnotatedFieldsAndMethods entity = new EntityWithAnnotatedFieldsAndMethods();
        assertEquals(10, entity.getCost());
        assertNull(entity.getArrayList());
        //when
        AnnotationsMethods.fillInTheFields(entity);
        //then
        assertEquals(0, entity.getCost());
        assertNotNull(entity.getArrayList());
    }

    @Test
    public void getDefaultValue() {

        //when
        char actualChar = AnnotationsMethods.getDefaultValue(char.class);
        double actualDouble = AnnotationsMethods.getDefaultValue(double.class);
        String actualString = AnnotationsMethods.getDefaultValue(String.class);

        //then
        assertEquals('\u0000', actualChar);
        assertEquals(0, actualDouble);
        assertNull(actualString);
    }
}