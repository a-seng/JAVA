package cn.aseng.reflect;

import cn.aseng.domain.Person;

public class ReflectDemo {

    /**
     * 获取Class对象的方式
     *  1.Class.forName（“全类名”），将字节码文件加载进内存，放回Class对象
     *  2.类名.class； 通过类名的属性class获取
     *  3.对象。getClass（）：getClass（）方法在Object类中定义着
     */

    public static void main(String[] args) throws ClassNotFoundException {
        //1.Class。forName（“全类名")
        Class cls1= Class.forName("cn.aseng.domain.Person");
        System.out.println(cls1);
        String name=cls1.getName();
        System.out.println(name);
//        cls1.getAnnotations()

        //2.类名.class
        Class cls2 = Person.class;
        System.out.println(cls2);

        //3.对象。getClass（）
        Person p = new Person();
        Class cls3 = p.getClass();
        System.out.println(cls3);
    }
}
