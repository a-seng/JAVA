# JDBC 操作

1.   
   1. PrepareStatement 执行sql的对象
      1. sql注入问题，在拼接sql时，有一些sql的特殊关键字会参与字符串的拼接，会造成安全性问题
         1. 输入用户随便，输入密码a'or'a'='a
         2. sql:select*from user where name='fasdf' and password = 'a'or'a'='a'
      2. 解决sql注入问题，使用PreparedStatement对象来解决
      3. 预编译的SQL，参数使用？作为占位符
      4. 步骤
         1. 导入驱动jar包，mysql-connector-java-5.1.37-bin-jar
         2. 导入驱动
         3. 获取数据库连接对象Connection
         4. 定义sql
            1. 注意sql的参数使用？作为占位符，如select * from user where username = ? and password = ?
         5. 获取sql语句的对象 PreparedStatement Connection。prepareStatement（String sql）
         6. 给？赋值：
            1. 方法： setXxx（参数1，参数2）
               1. 参数1，？的位置编号 从1开始
               2. 参数2：？的位置编号
         7. 执行sql，接收返回结果，不需要传递sql语句
         8. 处理结果
         9. 释放资源
      5. 后期都会使用PreparedStatement

# JDBC连接池

## 数据库连接池

1. 概念：其实就是一个容器（集合），存放数据库连接的容器

   当系统初始化好后，容器被创建，容器中会申请一些连接诶对象，当用户来访问数据库时，从容器中获取连接对象，用户访问完之后，会将连接对象归还容器

2. 好处

   节约资源

   用户访问高效

3. 实现

   1. 标准结构：DataSource javax.sql包下的
      1. 方法：
         1. 获取连接：getConnection（）
         2. 归还连接：如果连接诶对象Connection是从连接池中获取的，那么调用Connection.close（）方法，则不会在关闭连接了。而是归还连接
   2. 有数据库厂商来实现
      1. C3P0数据库连接池技术
      2. Druid：数据库连接池实现技术，由阿里巴巴提供的

4. C3P0：数据库连接池技术

   1. 步骤

      1. 导入jar包，不要忘记导入数据库驱动jar包

   2. 定义配置文件

      1. 名称：c3p0.properties 或者 c3p0-config.xml
      2. 路径：直接将文件放在src目录下即可。

   3. 创建核心对象 数据库连接池对象 ComboPooledDataSource

   4. 获取连接：getConnection

      ```java
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
      ```

5. Druid:数据库连接池实现技术，由阿里提供

   1. 步骤

      1. 导入jar包

      2. 定义配置文件：

         1. 是properties形式的
         2. 可以叫任意名称，可以放在任意目录下

      3. 加载配置文件 Properties

      4. 获取数据库连接池对象：通过工厂来获取 DruidDataSourceFactory

      5. 获取连接

         ```java
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
         ```

6. Spring JDBC

   1. Spring框架对JDBC的简易封装。提供一个JDBCTemplate对象简化JDBC的开发

   2. 步骤

      1. 导入jar包

      2. 创建JdbcTemplate对象，依赖于数据源DataSource

         JdbcTemplate template = new JdbcTemplate (ds);

      3. 调用JdbcTemplate的方法来完成CRUD的操作

         1. update（）；执行DML语句，增删改

         2. queryForMap（）；查询结果将结果集封装为map集合

            注意：这个方法查询结果集长度只能是1

         3. queryForList（）；查询结果将结果封装为list集合

            注意：将每一条记录封装为一个Map集合，再将Map集合装载到List集合中

         4. query（）；查询结果，将结果封装为JavaBean对象

            query的参数：RowMapper

            ​	一般我们使用BeanPropertyRowMapper实现类，可以完成数据到JavaBean的自动封装

            ​	new BeanPeopertyRowMapper<类型>(类型.class)

         5. queryForObject；查询结果，将结果封装为对象

            1. 一般用于聚合函数的查询

      ```java
      /*
      查询所有记录，将其封装为List
       */
      private JdbcTemplate template=  new JdbcTemplate((JDBCUtils.getDataSource()));
      
      @Test
      public void test4(){
          String sql="insert into emp(id,ename,dept_id) values(?,?,?)";
          int count =template.update(sql, 1015, "郭靖", 10);
          System.out.println(count);
      }
      
      @Test
      public void test5(){
          String sql="select * from emp";
          List<Map<String,Object>> list= template.queryForList(sql);
      
          for(Map<String,Object>stringObjectMap:list){
              System.out.println(stringObjectMap);
          }
      
      }
      
      @Test
      public void test6(){
          String sql ="select * from emp";
          List<Emp>list =template.query(sql, new RowMapper<Emp>() {
              @Override
              public Emp mapRow(ResultSet resultSet, int i) throws SQLException {
                  Emp emp=new Emp();
                  return emp;
              }
          });
      }
      
      @Test
      public void test7(){
          String sql="select *from emp";
          List<Emp> list=template.query(sql,new BeanPropertyRowMapper<Emp>(Emp.class));
          for(Emp emp:list){
              System.out.println(emp);
          }
      }
      
      @Test
      public void test8(){
          String sql ="select count(id) from emp";
          Long total = template.queryForObject(sql,Long.class);
          System.out.println(total);
      }
      ```