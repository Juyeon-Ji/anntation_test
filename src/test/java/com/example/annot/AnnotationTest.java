package com.example.annot;

import org.junit.jupiter.api.Test;

import java.lang.reflect.*;

class AnnotationTest {

    @Test
    public void override_test() {
        ParentClass parentClass = new ParentClass();
        ParentClass childClass = new ChildClass();

        System.out.println(parentClass.speak());
        System.out.println(childClass.speak());
    }

    @Test
    public void functionalInterface_test(){
        FunctionalInterface functionalInterface = (text) -> System.out.println(text);

        functionalInterface.doSomething("test");
    }

    @Test
    public void reflection_test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        //private 함수를 가져오기 위해 Method 변수 선언
        Add add = new Add();
        Method method = Add.class.getDeclaredMethod("add", int.class, int.class);
        method.setAccessible(true); // private  메서드 접근 가능을 위해 true
        int ret = (int) method.invoke(add,1, 2); // 메서드 실행
        System.out.println(ret);

        Field field = Add.class.getDeclaredField("c");
        field.setAccessible(true);
        field.set(add, 20);
        ret = (int) method.invoke(add,1, 2); // 메서드 실행
        System.out.println(ret);

    }

    @Test
    public void reflection_print_test() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Node 클래스 타입을 찾아 Class 객체를 생성
//        com/example/annot/Node.java
        Class<?> cls = Class.forName("com.example.annot.Node");
        // Node 클래스의 생성자를 취득
        Constructor<?> constructor = cls.getConstructor();
        //생성자를 통해 newInstance 함수를 호출하여 Node 인스턴스 생성
        Object node = constructor.newInstance();
        // Node 클래스의 print함수를 취득
        Method method = cls.getMethod("print");
        // 취득한 함수에 생성한 인스턴스를 넣고 실행
        method.invoke(node);

    }

}