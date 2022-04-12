package cn.aseng.util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    /*
    文件的读取，只需要读取一次即可拿到这些值，使用静态代码块
    静态代码块只能拿去静态修饰的值
     */
    static{
        try {
            //1.创建Properties集合类
            Properties pro=new Properties();

            //获取src路径下的文件方式-->ClassLoader类加载器
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            String path =res.getPath();

            //2.加载文件
            pro.load(new FileReader(path));

            //3.获取数据，赋值
            url=pro.getProperty("url");
            user=pro.getProperty("user");
            password=pro.getProperty("password");
            driver = pro.getProperty("driver");

            //4.注册驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }



    public static void close(Statement stmt, Connection conn, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void close(Statement stmt, Connection conn){
        close(stmt,conn,null);
    }
}
