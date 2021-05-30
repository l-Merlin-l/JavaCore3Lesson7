package com.company;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestMe {
    @BeforeSuite
    void beforeTest(){
        System.out.println("Выполняется метод с аннотацие @BeforeSuite");
    }

    @Test (priority = 2)
    void testThisMethodOne(){
        System.out.println("Выполняется метод 'testThisMethodOne' с приоритетом = 2");
    }


    @Test (priority = 1)
    void testThisMethodTwo(){
        System.out.println("Выполняется метод 'testThisMethodTwo' с приоритетом = 10");
    }


    @Test (priority = 4)
    void testThisMethodThree(){
        System.out.println("Выполняется метод 'testThisMethodThree' с приоритетом = 4");
    }

    @Test (priority = 6)
    void testThisMethodFour(){
        System.out.println("Выполняется метод 'testThisMethodFour' с приоритетом = 6");
    }

    @Test
    void testThisMethodFive(){
        System.out.println("Выполняется метод 'testThisMethodFive' с приоритетом по умолчанию");
    }

    @Test(priority = 3)
    void testThisMethodSix(){
        System.out.println("Выполняется метод 'testThisMethodSix' с приоритетом = 3");
    }

    @Test
    void testThisMethodSeven(){
        System.out.println("Выполняется метод 'testThisMethodSeven' с приоритетом по умолчанию");
    }

    @Test (priority = 5)
    void testThisMethodEight(){
        System.out.println("Выполняется метод 'testThisMethodEight' с приоритетом = 5");
    }

    @Test (priority = 2)
    void testThisMethodNine(){
        System.out.println("Выполняется метод 'testThisMethodNine' с приоритетом = 2");
    }

    @AfterSuite
    void afterTest(){
        System.out.println("Выполняется метод с аннотацией @AfterSuite");
    }

}
