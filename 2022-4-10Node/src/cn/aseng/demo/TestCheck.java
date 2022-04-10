package cn.aseng.demo;

import cn.aseng.annotation.Check;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestCheck {
    public static void main(String[] args) throws IOException {
        //创建计算器对象
        Calculator c = new Calculator();

        //获取字节码文件
        Class cls = c.getClass();

        //获取所有方法
        Method[] methods = cls.getMethods();

        int number=0;
        BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));

        for(Method method : methods){
            //判断方法上是否有Check注解
            if(method.isAnnotationPresent(Check.class)){
                try {
                    method.invoke(c);
                } catch (Exception e) {
                    number++;
                    bw.write(method.getName() + "方法出现异常");
                    bw.newLine();
                    bw.write("异常的名称"+e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常的原因"+e.getCause().getMessage());
                    bw.write("-----------------------------------");
                    bw.newLine();
                }
            }
        }

        bw.write("本次一共出现"+number+"次异常");
        bw.flush();
        bw.close();
    }
}
