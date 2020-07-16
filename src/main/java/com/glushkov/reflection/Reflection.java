package com.glushkov.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Reflection {

    public static Object getObjectFromClass(Class clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException reflectiveOperationException) {
            throw new RuntimeException("Method Runtime Error", reflectiveOperationException);
        }
    }

    public void invokeAllMethodsWithoutParams(Object object) {
        try {
            Method[] allMethods = object.getClass().getDeclaredMethods();

            for (Method method : allMethods) {
                if (method.getParameterCount() == 0) {
                    method.setAccessible(true);
                    method.invoke(object);
                }
            }
        } catch (ReflectiveOperationException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
        }
    }

    public void printAllFinalMethods(Object object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (Modifier.isFinal(method.getModifiers())) {
                System.out.println(method);
            }
        }
    }

    public void printAllNotPublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (!Modifier.isPublic(method.getModifiers())) {
                System.out.println(method);
            }
        }
    }

    public void printAllParentsAndImpInterfaces(Class clazz) {
        Class temp = clazz;

        while (clazz != Object.class) {
            clazz = clazz.getSuperclass();
            System.out.println(clazz.getName());
        }

        Class[] interfaces = temp.getInterfaces();
        for (Class aClass : interfaces) {
            System.out.println(aClass);
        }
    }

    public static void changeAllPrivateFieldsValuesToNull(Object object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();

            for (Field AField : fields) {
                if (Modifier.isPrivate(AField.getModifiers())) {
                    AField.setAccessible(true);
                    AField.set(object, getDefaultValue(AField.getType()));
                }
            }
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }

    static <T> T getDefaultValue(Class<T> clazz) {
        return (T) Array.get(Array.newInstance(clazz, 1), 0);
    }
}
