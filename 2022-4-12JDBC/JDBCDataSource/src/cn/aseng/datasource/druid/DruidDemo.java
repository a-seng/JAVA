package cn.aseng.datasource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {
    public static void main(String[] args) throws Exception {
        Properties pro = new Properties();
        InputStream resourceAsStream = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(resourceAsStream);

        //获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);

        //获取连接
        Connection conn=ds.getConnection();
        System.out.println(conn);

    }
}
