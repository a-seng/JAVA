package cn.aseng.test;

import cn.aseng.dao.Userdao;
import cn.aseng.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("superbaby");
        loginuser.setPassword("123");

        Userdao dao=new Userdao();
        User user=dao.login(loginuser);
        System.out.println(user);

    }
}
