package cn.aseng2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class aseng2 {
    @Before
    public void init(){
        System.out.println("我被执行了 我是第一个。。。。。。。。。");
    }

    @Test
    public void test(){
        System.out.println("我是测试Test的 没想到吧!!!!!!!");
    }

    @After
    public void close(){
        System.out.println("我是最后被执行 。。。。");
    }



}
