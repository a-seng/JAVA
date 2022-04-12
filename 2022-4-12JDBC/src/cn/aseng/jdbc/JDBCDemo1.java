package cn.aseng.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo1 {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:musql//localhost:3306/db3"
        ,"root","root");
        String sql ="update dept set loc='贵州大学' where id=10";

        Statement stmt=conn.createStatement();
        int count = stmt.executeUpdate(sql);
        System.out.println(count);

        stmt.close();
        conn.close();
    }

}
