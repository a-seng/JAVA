package cn.aseng.reflect;

import cn.aseng.domain.Person;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo2 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class personClass = Person.class;

        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        Field name =personClass.getDeclaredField("name");
        Person p =new Person("aseng",19);
        //暴力反射 忽略访问权限修饰符的安全检查
        name.setAccessible(true);
        Object o = name.get(p);
        System.out.println(o);


        Method name1 = personClass.getMethod("eat");
//        name1.invoke(p);
//        Method name2 = personClass.getMethod("show");
//        name.setAccessible(true);
//        name2.invoke(p);
        Method name3 =personClass.getMethod("eat",String.class);
        name3.invoke(p,"鱼");
    }
}
