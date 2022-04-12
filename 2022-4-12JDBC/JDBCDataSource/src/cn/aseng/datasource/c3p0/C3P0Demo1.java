package cn.aseng.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo1 {
    public static void main(String[] args) throws SQLException {
        //创建数据库连接池对象
        //什么都没传 使用默认配置
        //其他配置ComboPooledDataSource("otherc30p);
        DataSource ds= new ComboPooledDataSource();
        //获取连接对象
        Connection conn=ds.getConnection();

        //打印
        System.out.println(conn);
    }
}
