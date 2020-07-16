package com.glushkov.annotations;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationsMethods {

    static void injectAnnotation(Object value) {
        try {
            Class clazz = value.getClass();
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                Run annotation = method.getAnnotation(Run.class);
                if (annotation != null) {
                    if (method.toString().contains("()")) {
                        method.setAccessible(true);
                        method.invoke(value);
                    }
                }
            }
        } catch (ReflectiveOperationException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
        }
    }

    static void fillInTheFields(Object value) {
        try {
            Class clazz = value.getClass();

            for (Field field : clazz.getDeclaredFields()) {
                Inject annotation = field.getAnnotation(Inject.class);
                if (annotation != null) {
                    if (annotation.value() == Void.class) {
                        Class fieldClazz = field.getClass();
                        field.setAccessible(true);
                        field.set(value, fieldClazz.getConstructor().newInstance());
                        break;
                    }

                    field.setAccessible(true);

                    if (annotation.value().isPrimitive()) {
                        field.set(value, getDefaultValue(annotation.value()));
                    } else {
                        field.set(value, annotation.value().getConstructor().newInstance());
                    }
                }
            }
        } catch (ReflectiveOperationException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
        }
    }

    static <T> T getDefaultValue(Class<T> clazz) {
        return (T) Array.get(Array.newInstance(clazz, 1), 0);
    }
}




