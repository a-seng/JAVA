package cn.aseng.jdbc;

import cn.aseng.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCDemo3 {
    public static void main(String[] args) {
        //1.键盘录入，接受用户名和密码
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username=sc.nextLine();
        System.out.println("请输入密码：");
        String password=sc.nextLine();
        //调用方法
        boolean flag =new JDBCDemo3().login2(username,password);
        if(flag){
            System.out.println("登陆成功");
        }else{
            System.out.println("登陆失败");
        }
    }

    //登录方法使用PreparedStatement
    public boolean login2(String username ,String password){
        if(username == null || password ==null){
            return false;
        }
        //连接数据库判断是否登录成功
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn= JDBCUtils.getConnection();
            //定义sql
            String sql="select * from user where username=? and password = ?";
            //获取执行sql的对象
            pstmt=conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            //执行查询
            rs=pstmt.executeQuery();

            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn,rs);
        }

        return false;


    }
}
