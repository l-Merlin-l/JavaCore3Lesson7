package com.company;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DoTests {
    private static List<Method> methodList = new ArrayList<>();
    private static Method beforeSuiteMethod = null;
    private static Method afterSuiteMethod = null;

    public static void start(Class classForTest){
        getMethodList(classForTest);
        doMethods(classForTest);
    }

    private static void getMethodList(Class classForTest){
        for (Method method : classForTest.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if(beforeSuiteMethod == null){
                    beforeSuiteMethod = method;
                } else {
                    throw new RuntimeException("В классе несколько методов с аннотацией @BeforeSuite");
                }
            }else if(method.isAnnotationPresent(AfterSuite.class)){
                if(afterSuiteMethod == null) {
                    afterSuiteMethod = method;
                }else {
                    throw new RuntimeException("В классе несколько методов с аннотацией @AfterSuite");
                }
            } else if(method.isAnnotationPresent(Test.class)){
                methodList.add(method);
            }
        }
    }

    private static void doMethods(Class classForTest){
        Object testThisObject = null;
        Constructor constructor = null;
        try{
            constructor = classForTest.getConstructor();
            testThisObject = constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if(beforeSuiteMethod != null){
            beforeSuiteMethod.setAccessible(true);
            try{
                beforeSuiteMethod.invoke(testThisObject);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        methodList.sort((method1, method2) -> {
            int priority1 = method1.getAnnotation(Test.class).priority();
            int priority2 = method2.getAnnotation(Test.class).priority();
            return priority1 - priority2;
        });

        for(Method method : methodList){
            method.setAccessible(true);
            try{
                method.invoke(testThisObject);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(afterSuiteMethod != null){
            afterSuiteMethod.setAccessible(true);
            try {
                afterSuiteMethod.invoke(testThisObject);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
