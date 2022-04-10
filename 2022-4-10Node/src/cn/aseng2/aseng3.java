package cn.aseng2;

import cn.aseng.reflect.ReflectTest;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class aseng3 {
    @Test
    public void test() throws Exception, NoSuchFieldException, NoSuchMethodException {
        Class<?> aClass = Class.forName("cn.aseng2.Person");
        System.out.println(aClass);

        Class<Person> bClass = Person.class;
        System.out.println(bClass);

        Person pp = new Person();
        Class<? extends Person> aClass1 = pp.getClass();
        System.out.println(aClass1);

        Field name = aClass.getField("name");
        System.out.println(name);
        Field age = aClass.getDeclaredField("age");
        System.out.println(age);

        Constructor<?> constructor = aClass.getConstructor(int.class, String.class);
        Object aseng = constructor.newInstance(18, "aseng");
        System.out.println(aseng);

        Method eat = aClass.getMethod("eat",String.class);
        eat.invoke(pp,"大龙虾");
//        System.out.println(method);
    }

    @Test
    public void test2()throws  Exception{
        Properties pro = new Properties();
        ClassLoader cl = ReflectTest.class.getClassLoader();
        InputStream ras = cl.getResourceAsStream("pro.properties");
        pro.load(ras);

        String className=pro.getProperty("className");
        String methodName=pro.getProperty("methodName");

        Class cls= Class.forName(className);
        Object obj=cls.newInstance();

        Method method = cls.getMethod(methodName,String.class);
        Object invoke = method.invoke(obj,"大龙虾啦啦啦");
        System.out.println(invoke);


    }
}
