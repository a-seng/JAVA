package cn.aseng.jdbc;

import cn.aseng.util.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo2 {
    public static void main(String[] args) throws SQLException {
        List<Emp>list = new JDBCDemo2().findAll2();
        System.out.println(list);
        System.out.println(list.size());
    }

    private List<Emp> findAll2() throws SQLException {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        List<Emp>list=null;

        conn= JDBCUtils.getConnection();
        String sql="select * from emp";
        stmt=conn.createStatement();
        rs=stmt.executeQuery(sql);
        Emp emp=null;
        list=new ArrayList<Emp>();
        return list;
    }

}
