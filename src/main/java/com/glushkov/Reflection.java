package com.glushkov;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Reflection {

    private ArrayList<Method> testListOfMethodsWithoutParam = new ArrayList<>();
    private ArrayList<Method> testListOfFinalMethods = new ArrayList<>();
    private ArrayList<Method> testListOfNotPublicMethods = new ArrayList<>();
    private ArrayList<Class> testListOfParentClassesAndInterfaces = new ArrayList<>();


    public static Object getObjectFromClass(Class clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException reflectiveOperationException) {
            throw new RuntimeException("Method Runtime Error", reflectiveOperationException);
        }
    }

    public void invokeAllMethodsWithOutParam(Object object) {
        try {
            Method[] allMethods = object.getClass().getDeclaredMethods();

            for (Method method : allMethods) {
                if (method.toString().contains("()")) {
                    method.setAccessible(true);
                    method.invoke(object);
                    method.setAccessible(false);
                    testListOfMethodsWithoutParam.add(method);
                }
            }
        } catch (ReflectiveOperationException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
        }
    }

    public void printAllFinalMethods(Object object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.toString().contains("final")) {
                testListOfFinalMethods.add(method);
                System.out.println(method);
            }
        }
    }

    public void printAllNotPublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (!method.toString().contains("public")) {
                testListOfNotPublicMethods.add(method);
                System.out.println(method);
            }
        }
    }

    public void printAllParentsAndImpInterfaces(Class clazz) {
        Class temp = clazz;

        while (!clazz.equals(Object.class)) {
            clazz = clazz.getSuperclass();
            testListOfParentClassesAndInterfaces.add(clazz);
            System.out.println(clazz.getName());
        }

        Class[] interfaces = temp.getInterfaces();
        for (Class aClass : interfaces) {
            testListOfParentClassesAndInterfaces.add(aClass);
            System.out.println(aClass);
        }
    }

    public static void changeAllPrivateFieldsValuesToNull(Object object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();

            for (Field AField : fields) {
                if (AField.toString().contains("private")) {

                    AField.setAccessible(true);

                    if (AField.getType().equals(byte.class) || AField.getType().equals(short.class) ||
                            AField.getType().equals(int.class) || AField.getType().equals(long.class) ||
                            AField.getType().equals(double.class) || AField.getType().equals(float.class)) {

                        AField.setByte(object, (byte) 0);
                    } else if (AField.getType().equals(boolean.class)) {
                        AField.setBoolean(object, false);
                    } else if (AField.getType().equals(char.class)) {
                        AField.setChar(object, ' ');
                    } else AField.set(object, null);

                    AField.setAccessible(false);
                }
            }
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }

    ArrayList<Method> getListOfMethodsWithoutParam() {
        return testListOfMethodsWithoutParam;
    }

    ArrayList<Method> getListOfFinalMethods() {
        return testListOfFinalMethods;
    }

    ArrayList<Method> getListOfNotPublicMethods() {
        return testListOfNotPublicMethods;
    }

    ArrayList<Class> getTestListOfParentClassesAndInterfaces() {
        return testListOfParentClassesAndInterfaces;
    }
}
