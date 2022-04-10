package cn.aseng.reflect;

import cn.aseng.annotation.MyAnno;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
@MyAnno
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        //加载配置文件
        Properties pro = new Properties();
        //转换为一个集合
        //获取class目录下的配置文件
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        pro.load(is);

        //获取配置文件中定义的数据
        String className=pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //加载该类进内存
        Class cls = Class.forName(className);
        //创建对象
        Object obj = cls.newInstance();

        Method method = cls.getMethod(methodName);
        Object invoke = method.invoke(obj);
        System.out.println(invoke);

    }
}
