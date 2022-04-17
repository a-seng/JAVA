package cn.aseng.test;

import cn.aseng.domain.User;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class BeanUtilsTest {

    @Test
    public void test(){
        User user=new User();

//        BeanUtils.setProperty(user,"username","zhangsan");

        System.out.println(user);
    }
}
