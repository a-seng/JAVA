# MySQL学习

## MySQL多表查询

1. #### 笛卡尔积

   有两个集合A,B，去这两个集合的所有组成情况

   要完成多表查询，需要消除无用的数据

2. #### 多表查询的分类

   1. ##### 内连接查询

      1. 隐式内连接：使用where条件消除无用数据

         SELECT 
         	t1.`name`,t1.`gender`,t2.`name`
         FROM
         	emp t1,dept t2
         WHERE
         	t1.`dept_id`=t2.`id`;

      2. 显示内连接

         语法 select 字段列表 from 表名1[inner] join 表名2 on 条件

         select *from emp inner join dept on emp.dept_id=dept.id;

         select *from emp join dept on emp.dept_id=dept.id;

   2. ##### 外连接查询

      1. 左外连接

         语法:select 字段列表 from 表1 left[outer] join 表2 on 条件;

         查询的是左表所有数据以及其交集部分

      2. 右外连接

         select 字段列表 from 表1 right[outer] join 表2 on 条件；

      3. select * from dept t2 left join emp t1 on t1.dept_id = t2.id;

   3. ##### 子查询

      1. 子查询是单行单列的

         *子查询可以作为条件，使用运算符去判断。运算符：>,<,>=,<=,=

         *查询员工工资小于平均工资的人

         ​	SELECT * from emp where emp.salary<(select avg(salary) from emp); 

      2. 子查询的结果是多行单列的：

         *子查询可以作为条件，使用运算符in来判断

         ​	查询‘财务部’和市场部所有的员工信息

         ​	select id from dept where name='财务部' or name='市场部';

         ​	select * from emp where dept_id=3 or dept_id=2;

         ​	子查询

         ​		select * from emp where dept_id in (select id from dept where name='财务部' or name='市场部');

      3. 子查询的结果是多行多列的:

         1. 子查询可以作为一张虚拟表

            查询员工入职日期是2011-11-11日之后的员工信息和部门信息

            select * from dept t1,(select * from emp where emp.'join_data' >'2011-11-11')t2 where t1.id=t2.dept.id;

      4. *案例*

         1.SELECT 
         	t1.ename,
         	t1,salary,
         	t2.jname,
         	t2.description,
         	t3,dname,
         	t3.loc,
         	t4.grade
         FROM 
         	emp t1,job t2,dept t3,salarygrade t4
         WHERE
         	t1.job_id=t2.id
         	AND t1.dept_id=t3.id
         	AND t1.salary BETWEEN t4.losalary AND t4.hisalary;

         2.SELECT 
         	t1.ename,
         	t1.mgr,
         	t2.id,
         	t2.ename
         FROM emp t1
         LEFT JOIN emp t2
         ON t1.`mgr` = t2.id;

## 事务

1. 事务的基本介绍

   1. 概念如果一个包含多个步骤的业务操作，被事务管理，那么这些操作要么同时成功，要么同时失败

   2. 操作

      1. 开启事务：start transaction；

      2. 回滚：rollback；

      3. 提交：commit；

         *案例

         ​	SELECT * FROM account;
         UPDATE account SET balance =1000;
         //张三给李四转账500元
         //开启事务
         START TRANSACTION;
         //张三-500
         UPDATE account SET balance =balance -500 WHERE NAME="zhangsan";
         //李四+500
         UPDATE account SET balance -balance +500 WHERE NAME="lisi";
         //发现执行没有问题，提交事务
         COMMIT;
         //发现问题，回滚事务
         ROLLBACK;

      4. MySQL数据库中事务默认自动提交

         1. 事务提交的两种方式
            1. 自动提交
               1. mysql就是自动提交
               2. 一条DML（增删改）语句会自动提交一次事务
            2. 手动提交
               1. oracle 数据库默认是手动提交事务
               2. 需要先开开启事务再提交
         2. 修改事务的默认提交方式
            1. 查看事务的默认提交方式：select @@autocommit;--1代表自动提交，0代表手动提交
            2. 修改默认提交方式： set @@autocommit=0;

2.  事务的四大特征

   1. 原子性：是补课分割的最小操作单位，要么同时成功，要么同时失效
   2. 持久性：当时吴提交或回滚后，数据库会持久化的保存数据
   3. 隔离性：多个事务之间，相互独立
   4. 一致性：实务操作前后，数据总量不变

   

3.  事务的隔离级别

   1. 脏读：一个事物，读取到另一个事务中没有提交的数据
   2. 不可重复读（虚读）：在同一个事务中，两次读取到的数据不一样
   3. 幻读：一个事务操作（DML）数据表中所有记录，另一个事物添加了一条数据，则第一个事务查询不到自己的修改
   4. 隔离级别
      1. read uncommitted：读未提交
         1. 产生的问题：脏读，不可重复读，幻读
      2. read committed：读已提交(Oracle)
         1. 产生的问题：不可重复读，幻读
      3. repeatable read：可重复读(MySQL)
         1. 产生的问题：幻读
      4. serializable：串行化
         1. 可以解决所有的问题
      5. 注意：隔离级别从下到大安全性越来越高，但是效率越来越低
      6. 数据库查询隔离级别
         1. select @@tx_isolation;
      7. 数据库设置隔离界别
         1. set global transaction isolation level 级别字符串;

4.  用户管理

   1. 添加用户
      1. create uesr '用户名' @'主机名' identified by '密码'
      2. CREATE USER 'zhangsna'@'localhost' IDENTIFIED BY '123';
   2. 删除用户
      1. drop user '用户名'@'主机名';
   3. 修改密码
      1. update user set password =password('新密码') where user ='用户名';
      2. set password for '用户名'@'主机名'=password('新密码');
      3. mysql忘记root用户密码
         1. cmd-->net stop mysql 停止mysql服务//需要管理员运行cmd
         2. 使用无验证方式启动mysql服务：mysql --skip-grant-tables
         3. 打开新的cmd窗口，直接数据mysql命令，敲回车。就可以登录成功
         4. use mysql
         5. update user set password = passw（'你的新密码') where user ='root';
         6. 关闭两个窗口
         7. 打开任务管理器，手动结束mysql.exe进程
         8. 启动mysql服务
         9. 使用新密码登录

5. 权限管理

   1. 授予权限
      1. grand 权限列表 on 数据库名.表名 to '用户名'@'主机名';
      2. 给张三用户授予所有权限，在任意数据库任意表上
         1. grant all on 心.心 to 'zhangsan'@'localhost';
   2. 查询权限:
      1. show grants for '用户名'@'主机名'
      2. show grants for 'lisi'@'%'
   3. 撤销权限
      1. revoke 权限列表 on 数据库名.表名 from '用户名'@'主机名';
      2. revoke update on db3.account from 'lisi'@'%'