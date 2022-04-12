# JDBC学习

概念：JDBC本质：其实就是官方（sun公司）定义的一套操作所有关系型数据库的规则，即接口。各个数据库厂商去实现这套接口，提供数据库驱动jar报，我们可以使用这套接口（JDBC）编程，真正执行的代码是驱动jar包中的实现类

## JDBC快速入门

1.   

   1. 导入驱动jar包 mysql-connector-java-5.1.37-bin.jar

      1. 复制mysql-connector-javaxxx到项目的libs目录下（哪里都可以)
      2. 右键--》Add As Library

   2. 注册驱动

   3. 获取数据库连接对象Connection

   4. 定义sql

   5. 获取执行sql语句的对象 Statement

   6. 执行sql，接收返回结果

   7. 处理结果

   8. 释放资源

   9. 代码实现

      ```java
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3","root","root");
      String sql ="update dept set loc ='贵阳' where id =10";
      
      
      Statement stmt = conn.createStatement();
      int count =stmt.executeUpdate(sql);
      System.out.println(count);
      
      stmt.close();
      conn.close();
      ```

   

2. 详解各个对象

   1. DriverManager:驱动管理对象

      1. 功能

         1. ```java
            static void registerDriver(Driver driver):注册与给定的驱动程序DriverManager
            写代码使用：Class.forName("com.mysql.jdbc.Driver");
            通过查看源码发现：在"com.mysql.jdbc.Driver"类中存在静态代码块
            static {
                    try {
                    DriverManager.registerDriver(new Driver());
                    } catch (SQLException var1) {
                    throw new RuntimeException("Can't register driver!");
                    }
                    }
                    //注意mysql5之后的驱动jar包可以省掉注册驱动的步骤
            ```

         2. 获取数据库连接

            1. 方法：static Connection getConnection(String url,String user,String password)
            2. 参数
               1. url:指定连接的路径
                  1. 语法:jdbc:mysql://ip地址(域名):端口号/数据库名称
                  2. 例子:jdbc:musql://localhost:3306/db3
                  3. 细节如果连接的是本机的mysql服务器，并且mysql服务器默认端口是3306，则url可以简写为:jdbc:mysql:///数据库名称
               2. user:用户名
               3. password：密码

   2. Connection：数据库连接对象

      1. 功能
         1. 获取执行sql的对象
            1. Statement createStatement（）
            2. PreparedStatement prepareStatement（String sql）
         2. 管理事务：
            1. 开启事务：setAutoCommit(boolean autoCommit): 调用该方法设置参数为false，即开启事务
            2. 提交事务：commit（）
            3. 回滚事务：rollback（）

   3. Statement：执行sql的对象

      1. 执行sql

         1. boolean execute(String sql) 可以执行任意的sql 了解

         2. int executeUpdate(String sql):执行DML(insert,update,delete)语句，DDL（create,alter,drop)语句

            ​	*返回值：影响的行数，可以通过这个影响的行数判断DML语句是否执行成功，返回值》0成功

         3. ResultSet executeQuery(String sql):执行DQL（select)语句
      
   4. ResultSet:结果集对象，封装查询结果

      1. *boolean next():游标向下移动一行，判断当前行是否是最后一行末尾（是否有数据），如果是，则返回false，如果不是则返回true

      2. getXxx（参数）：获取数据

         1. Xxx代表数据类型 如int	getInt(),String getString()
         2. 参数
            1. int	代表列的编号，从1开始 如getString（1）
            2. String 代表列的名称， 如：getDouble（“balance”）

      3. 注意

         1. 使用步骤

            1. 游标向下移动一行

            2. 判断是否有数据

            3. 获取数据

               while(rs.next()){

               ​	int id=rs.getInt(1);

               ​	String name=rs.getString("name");

               ​	double balance = rs.getDouble(3);

               ​	System.out.println(id+"   "+name+"---"+balance);	

               }

3. 