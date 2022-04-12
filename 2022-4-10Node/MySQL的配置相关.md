# MySQL的配置相关

### MySQL的启动

1. cmd启动 net start mysql
    	cmd停止 net stop mysql

2. cmd->services.msc 打开服务窗口

### MySQL的登录

1. mysql -uroot -p密码
2. mysql -hip  -uroot -p连接目标的密码
3. mysql --host=ip --user=root --password=连接目标的密码
   

### MySQL的退出

1. exit
2. quit

### SQL分裂

1. DDL(Data Definition Language)数据定义语言

   用来定义数据库对象：数据库，表，列等。关键字：create，drop，alter等

2. DML（Data Manipulation Language)数据操作语言

   用来对数据库中表的数据进行增删改。关键字：insert,delete,update等

3. DQL(Data Query Language)数据查询语句

   用来查询数据库中表的记录（数据）。关键字:select,where等

4. DCL(Data Control Language)数据控制语句（了解）

   用来定义数据库的防蚊权限和安全级别，及创建用户，关键字:GRANT,REVOKE等

   ![image-20220410203838132](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20220410203838132.png)

# 数据库的操作

1. 

   1. 创建

      create database 数据库名称

      create database if not exists 数据库名称

      create database if not exists 数据库名称 character set utf-8(字符集)

   1. 查询

      show databases;	查询所有数据名字

      show create database 数据库名称	查询某个数据库的创建语句 可以查看数据库的字符集

   1. 修改

      alter database 数据库名称 character set 字符集名称；

   1. 删除

      drop database 数据库名称

      drop database if exists 数据库名称

   1. 使用数据库

      select database();	查询当前正在使用的数据库的名称

      use 数据库名称	使用该数据库相当于进入到了这个数据库

2. 操作表

   1. 创建

      create table 表名(

      ​	列名 1 数据类型,

      ​	列名2 数据类型,

      ............

      ​	列名n 数据类型

      )

      *注意最后一列不需要加逗号（，）

      *数据类型

      ​	1.int,

      ​	2.double 

      ​	3.data	只有年月日	yyyy-MM-dd

      ​	4.datatime 	有年月日时分秒	yyyy-MM-dd HH:mm:ss

      ​	5.timestamp: 时间错类型 包含年月日时分秒 如果将来不给这份字段赋值，或赋值为null，则默认采用当前的系统时间

      ​	6.varchar

      ​		*name varchar(20)：姓名最大20个字符*

      ​		*zhangsan 8个字符 	张三	2个字符*

   2. 查询

      show tables;	查询某个数据库中所有的表的名称

      desc 表名;	查询表的结构

   3. 修改

      1.修改表名

      ​	alter table 表名 rename to 新的表名

      2.修改表的字符集

      ​	alter table 表名 character set 字符集名称

      3.添加一列

      ​	alter table 表名 add 列名 数据类型

      4.修改列名称 类型

      ​	alter table 表名 change 列名 新列名 新的数据类型

      ​	alter table 表名 modify 列名 新的数据类型

      5.删除列

      ​	alter table 表名 drop 列名;

      6.添加数据

      ​	insert into 表名(列名，列名，列名) values(值1，值2，值3);

      ​	*注意 *

      ​		1.列名和值要一一对应

      ​		2.如果表明后，不定义列名，则默认给所有列添加值 insert into 表名(值1，值2，值3);

      ​		3.除了数字类型，其他类型需要使用引号（单双都可以）引起来

   4. 删除

      delete from 表名 [where 条件]

      注意

      ​	如果不加条件 ，则删除表中的所有记录

      ​	如果要删除所有记录

      ​	delete from 表名;--不推荐使用，有多少条记录就会执行多少次删除操作

      ​	TRUNCATE TABLE表名； --推荐使用，效率更高，先删除表，然后再创建一张一样的表

   5. 修改数据

      update 表名 set 列名1 = 值1,列名2 = 值2.。。。。[where 条件];

   6. 去除重复的结果集

      ​	select distinct 列名 from 表名

   7. 其他

      如果有null参与的运算，计算结果都为null

      ​	解决 IFNULL(列名，值3) 如果这个值为NULL就把它替换为值3

      起别名

      ​	列名 AS 别名

      ​	列名  （空格)别名

      &&	and 

      ​	between 条件一 and 条件二 在条件一 和 条件二之间 并且包含条件一和条件二

      or

      ​	in（条件一，条件二，条件三) 

      null

      ​	null不可以用= 用 is null是null

      ​	is not null 不为null

      LIKE

      ​	单个任意字符__下划线

      ​	多个任意字符%

## MySQL的查询

1. 排序

   1. order by

      select *  from student order by math desc

      order by math asc,english asc	按照数学成绩排名，如果一样按照英语成绩排名

      order by 子句 

      ​	order by 排序字段1 	排序方式1，排序字段2	排序方式2.....

      ​	ASC升序默认的

      ​	DESC 降序

2. 聚合· 将一列数据作为一个整体，进行纵向的计算

   1. count：计算个数

      select count(name) from student;

      一般选择非空的列，主键

      解决null

      ​	IFNULL函数

   2. max：计算最大值

   3. min:计算最小值

   4. sum:计算和

   5. avg:计算平均值

3. 分组 

   select sex,AVG(math) from student group by sex;

   ​	分组后查询的字段：分组字段，聚合函数

   select sex,avg(math) ,count(id) from student where 	math>70 gtoup by sex having count(id) >2; 

   注意

   ​	where 和 having的区别

   	*	where在分组之前进行限定，如果不满足条件，则不参与分组，having在分组后进行限定，如果不满足结果则不会被查询出来
   	*	where后不可以跟聚合函数，having可以进行聚合函数的判断

4. 分页

   select * from student limit 0,3;
   语法：limit 开始的索引,每页查询的条数

   公式：：开始的索引=（当前页码-1)*每页显示条数

## MySQL的约束

​	概念:对表中的数据进行限定，保证数据的准确性，有效性和完整性

​	分类

​		*主键约束：primary key

​		*非空约束：not null

​		*唯一约束：unique

​		*外键约束：foreign key

1. 非空约束 not null

   1. 创建表示添加约束

      create table stu(

      ​	id int,

      ​	name varchar(20) not null

      );

   2. 创建表后，添加非空约束

      alter table stu modify name varchar(20) not null;

   3. 创建表后，删除非空约束

      alter table stu modify name varchar(20);

2.  唯一约束：unique，某一列的值不能重复

   1. 注意：

      唯一约束可以有NULL值，但是只能有一条记录为null

   

3. 主键约束：primary key

   1. 注意

      含义：非空且唯一

      一张表只能有一个字段为主键

      主键就是表中记录的唯一标识

   2. 删除主键 （和其他不一样）

      alter table stu drop primary key;
      
   3. 联合主键

      create table tab(rid int ,

      ​		uid int.

      ​		primary key (rid,uid)//他们两个不能同时相同

      )

4. 自动增长(一般和主键一起使用)

   1. 概念：如果某一列是数值类型的，使用auto_increment 可以来完成值的自动增长

   2. create table stu(

      ​	id int primary key auto_increment

      )

5. 外键:foreign key ,让表与表产生关系，从而保证数据的正确性。

   1. create table employee(

      ​	id int primary key auto_increment,

      ​	dep_id int,

      ​	constraint emp_dept_fk(名字) foreign key (dep_id)(本表的外键) references department(id) (其他表的其他字段)

      );

   2. 删除外键

      alter table 表名 drop foreign key 外键名称;

   3. 创建表后添加外键

      alter table 表名 add constraint 外键名称 foreign key (外键字段名称) deferences 主表名称 (主表列名称);

   4. 级联更新 级联删除

      on update cascade on delete cascade;

      语法 alter table 表名 add constraint 外键名称 foreign key (外键字段名称) references 主表名称(主表列名称) on update cascade on delete cascade;

   5. 简写

      foreign key (cid) references tab_category(cid)	(constraint 外键名称 可以不用写 系统会默认非配一个唯一的)

   ## MySQL的范式

   1. 第一范式：每一列都是不可分割的原子数据项

   2. 第二范式：在第一范式的基础上，非码属性必须完全依赖于码（在第一范式基础上消除非主属性对主码的部分函数依赖）

      1. 函数依赖：A-->B，如果通过A属性（属性组）的值，可以唯一确定B属性的值，则称B依赖于A

         *列如：学号-》姓名，（学号，课程名称）-》分数

      2. 完全函数依赖：A-->B，如果A是一个属性组，则B属性值的确定需要依赖于A属性组中所有的属性值。

         *列如：（学号，课程）--》分数

      3. 部分函数依赖A--》B，如果A是一个属性组，则B属性值的确定只需要依赖于A属性组中某一些值即可

         *列如：（学号，课程名称）--》姓名

      4. 传递函数依赖：A-->B，B-->C，如果通过A属性（属性组）的值，可以确定唯一B属性的值，再通过B属性（属性组）的值可以唯一确定C属性的值，则称C传递函数依赖于A

         *列如：学号--》系名，系名--》系主任

      5. 码：如果在一张表中，一个属性或属性组，被其他所有属性所完全依赖，则称这个属性（属性组）为改表的码

         *列如：该表中的码为：（学号，课程名称）

         *主属性：码属性组中的所有属性

         *非主属性：除过码属性组的属性

   3. 第三范式：在第二范式基础上，任何非主属性不以依赖于其他非主属性（在第二范式基础上消除传递依赖）

## 数据库的备份和还原

1. 命令行：

   备份:mysqldump -u用户名 -p密码 >保存路径

   还原：source 文件路径

