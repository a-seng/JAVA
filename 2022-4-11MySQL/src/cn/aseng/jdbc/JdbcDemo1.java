package cn.aseng.jdbc;

import javax.management.StandardEmitterMBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3","root","root");
        String sql ="update dept set loc ='贵阳' where id =10";


        Statement stmt = conn.createStatement();
        int count =stmt.executeUpdate(sql);
        System.out.println(count);

        stmt.close();
        conn.close();
    }
}
//static void registerDriver(Driver driver):注册与给定的驱动程序DriverManager
//写代码使用：Class.forName("com.mysql.jdbc.Driver");
//通过查看源码发现：在"com.mysql.jdbc.Driver"类中存在静态代码块
//static {
//        try {
//        DriverManager.registerDriver(new Driver());
//        } catch (SQLException var1) {
//        throw new RuntimeException("Can't register driver!");
//        }
//        }
//        //注意mysql5之后的驱动jar包可以省掉注册驱动的步骤