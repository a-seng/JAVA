package cn.aseng.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo2 {
    public static void main(String[] args) throws Exception{
        Statement stmt=null;
        Connection conn=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String sql="insert into account values(null,'王五',3000)";
            conn= DriverManager.getConnection("jdbc:mysql///db3","root","root");
            stmt=conn.createStatement();

            int count =stmt.executeUpdate(sql);
            System.out.println(count);
            if(count>0){
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(stmt!=null){
                stmt.close();
            }

            if(conn!=null){
                conn.close();
            }
        }
    }
}
