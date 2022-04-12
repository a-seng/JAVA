package cn.aseng.jdbc;

import cn.aseng.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCDemo4 {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement pstmt1=null;
        PreparedStatement pstmt2=null;
        try {
            //获取连接
            conn= JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);

            //定义sql
            String sql1="update account set balance = balance -? where id =?";
            String sql2="update account set balance = balance +? where id =?";

            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);

            preparedStatement1.setDouble(1,500);
            preparedStatement1.setInt(2,1);

            preparedStatement2.setDouble(1,500);
            preparedStatement2.setInt(2,2);


            pstmt1.executeUpdate();
            int i=3/0;
            pstmt2.executeUpdate();
            //提交事务
            conn.commit();


        } catch (SQLException throwables) {
            if(conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt1,conn);
            JDBCUtils.close(pstmt2,null);
        }
    }
}
