package com.glushkov;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReflectionTest {
    private List<String> expectedMethodsListInvoke = new ArrayList<>();
    private List<String> expectedMethodsListFinal = new ArrayList<>();
    private List<String> expectedNotPublicMethodsList = new ArrayList<>();
    private List<String> expectedParentClassesAndInterfacesList = new ArrayList<>();

    @Before
    public void setUp() {
        List<String> expectedMethodsListInvoke = new ArrayList<>();
        List<String> expectedMethodsListFinal = new ArrayList<>();
        List<String> expectedNotPublicMethodsList = new ArrayList<>();
        List<String> expectedParentClassesAndInterfacesList = new ArrayList<>();


        expectedMethodsListInvoke.add("void java.util.ArrayList.checkInvariants()");
        expectedMethodsListInvoke.add("public java.util.ListIterator java.util.ArrayList.listIterator()");
        expectedMethodsListInvoke.add("private java.lang.Object[] java.util.ArrayList.grow()");
        expectedMethodsListInvoke.add("public int java.util.ArrayList.hashCode()");
        expectedMethodsListInvoke.add("public java.lang.Object java.util.ArrayList.clone()");
        expectedMethodsListInvoke.add("public void java.util.ArrayList.clear()");
        expectedMethodsListInvoke.add("public boolean java.util.ArrayList.isEmpty()");
        expectedMethodsListInvoke.add("public int java.util.ArrayList.size()");
        expectedMethodsListInvoke.add("public java.lang.Object[] java.util.ArrayList.toArray()");
        expectedMethodsListInvoke.add("public java.util.Iterator java.util.ArrayList.iterator()");
        expectedMethodsListInvoke.add("public java.util.Spliterator java.util.ArrayList.spliterator()");
        expectedMethodsListInvoke.add("public void java.util.ArrayList.trimToSize()");


        expectedMethodsListFinal.add("static final long java.util.Date.getMillisOf(java.util.Date)");
        expectedMethodsListFinal.add("private final sun.util.calendar.BaseCalendar$Date java.util.Date.getCalendarDate()");
        expectedMethodsListFinal.add("private static final sun.util.calendar.BaseCalendar java.util.Date.getCalendarSystem(sun.util.calendar.BaseCalendar$Date)");
        expectedMethodsListFinal.add("private static final sun.util.calendar.BaseCalendar java.util.Date.getCalendarSystem(long)");
        expectedMethodsListFinal.add("private static final sun.util.calendar.BaseCalendar java.util.Date.getCalendarSystem(int)");
        expectedMethodsListFinal.add("private final long java.util.Date.getTimeImpl()");
        expectedMethodsListFinal.add("private static final java.lang.StringBuilder java.util.Date.convertToAbbr(java.lang.StringBuilder,java.lang.String)");
        expectedMethodsListFinal.add("private static final synchronized sun.util.calendar.BaseCalendar java.util.Date.getJulianCalendar()");
        expectedMethodsListFinal.add("private final sun.util.calendar.BaseCalendar$Date java.util.Date.normalize()");
        expectedMethodsListFinal.add("private final sun.util.calendar.BaseCalendar$Date java.util.Date.normalize(sun.util.calendar.BaseCalendar$Date)");

        expectedNotPublicMethodsList.add("static int java.lang.Integer.getChars(int,int,byte[])");
        expectedNotPublicMethodsList.add("static int java.lang.Integer.stringSize(int)");
        expectedNotPublicMethodsList.add("private static java.lang.String java.lang.Integer.toStringUTF16(int,int)");
        expectedNotPublicMethodsList.add("private static java.lang.String java.lang.Integer.toUnsignedString0(int,int)");
        expectedNotPublicMethodsList.add("private static void java.lang.Integer.formatUnsignedInt(int,int,byte[],int)");
        expectedNotPublicMethodsList.add("private static void java.lang.Integer.formatUnsignedIntUTF16(int,int,byte[],int)");

        expectedParentClassesAndInterfacesList.add("class java.util.AbstractList");
        expectedParentClassesAndInterfacesList.add("class java.util.AbstractCollection");
        expectedParentClassesAndInterfacesList.add("class java.lang.Object");
        expectedParentClassesAndInterfacesList.add("interface java.util.List");
        expectedParentClassesAndInterfacesList.add("interface java.util.RandomAccess");
        expectedParentClassesAndInterfacesList.add("interface java.lang.Cloneable");
        expectedParentClassesAndInterfacesList.add("interface java.io.Serializable");
    }


    @Test
    public void getObjectFromClass() {
        String expected = "class java.util.ArrayList";

        String actual = Reflection.getObjectFromClass(ArrayList.class).getClass().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void invokeAllMethodsWithOutParam() {
        Reflection reflection = new Reflection();
        List testObject = new ArrayList();
        reflection.invokeAllMethodsWithOutParam(testObject);

        List<Method> listOfMethodsWithoutParam = reflection.getListOfMethodsWithoutParam();

        for (int i = 0; i < expectedMethodsListInvoke.size(); i++) {
            assertEquals(expectedMethodsListInvoke.get(i), listOfMethodsWithoutParam.get(i).toString());
        }
    }

    @Test
    public void printAllFinalMethods() {
        Date testObject = new Date();

        Reflection reflection = new Reflection();
        reflection.printAllFinalMethods(testObject);
        List<Method> listOfFinalMethods = reflection.getListOfFinalMethods();

        for (int i = 0; i < expectedMethodsListFinal.size(); i++) {
            assertEquals(expectedMethodsListFinal.get(i), listOfFinalMethods.get(i).toString());
        }
    }

    @Test
    public void printAllNotPublicMethods() {
        Reflection reflection = new Reflection();
        reflection.printAllNotPublicMethods(Integer.class);

        List<Method> listOfNotPublicMethods = reflection.getListOfNotPublicMethods();
        for (int i = 0; i < expectedNotPublicMethodsList.size(); i++) {
            assertEquals(expectedNotPublicMethodsList.get(i), listOfNotPublicMethods.get(i).toString());
        }
    }

    @Test
    public void printAllParentsAndImpInterfaces() {
        Reflection reflection = new Reflection();
        reflection.printAllParentsAndImpInterfaces(ArrayList.class);

        List<Class> actualListOfParentsAndInterfaces = reflection.getTestListOfParentClassesAndInterfaces();

        for (int i = 0; i < expectedParentClassesAndInterfacesList.size(); i++) {
            assertEquals(expectedParentClassesAndInterfacesList.get(i), actualListOfParentsAndInterfaces.get(i).toString());
        }
    }

    @Test
    public void changeAllPrivateFieldsValuesToNull() {
        PersonForTest person = new PersonForTest(50, "Nikola");
        Reflection.changeAllPrivateFieldsValuesToNull(person);

        int expectedAge = 0;
        String expectedName = null;

        int actualAge = person.getAge();
        String actualName = person.getName();

        assertEquals(expectedAge, actualAge);
        assertEquals(expectedName, actualName);
    }
}