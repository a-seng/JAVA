package cn.aseng.datasource.druid;

import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DruidDemo2 {

    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        PreparedStatement pstmt=null;

        conn= JDBCUtils.getConnection();
        String sql="insert into account values(null,?,?)";
        pstmt=conn.prepareStatement(sql);

        pstmt.setString(1,"王五");
        pstmt.setDouble(2,3000);

        int count =pstmt.executeUpdate();
        System.out.println(count);

        JDBCUtils.close(pstmt,conn);


    }

}
