# Filter&&Listener

## Filter:过滤器

1. 概念：过滤器的作用：一般用于完成通用的操作，如登录验证，统一编码处理，敏感字符过滤。。。

    

2. 快速入门

   1. 步骤：
      1. 定义一个类，实现接口Filter
      2. 复写方法
      3. 配置拦截路径
         1. web.xml
         2. 注解
   2. 代码：

    

3. 过滤器细节：

   1. web.xml配置

      1.  

       

   2. 过滤器执行流程

      1. 执行过滤器
      2. 执行放行后的资源
      3. 回来执行过滤器放行代码下边的代码

       

   3. 过滤器生命周期方法

      1. init：在服务器启动后，会创建Filter对象，然后调用init方法。只执行一次，用于加载资源

      2. doFilter：每一次请求被拦截资源时，会执行，执行多次

      3. destory：在服务器关闭后，Filter对象被销毁。如果服务器是正常关闭，回执行destory方法，只执行一次，用于释放资源

          

   4. 过滤器配置注解

      1. 拦截路径配置：

         1. 具体资源路径：/index.jsp	只有访问index.jsp资源时，过滤器才会被执行
         2. 拦截目录:/user/* 访问/user下的所有资源时，过滤器都会被执行
         3. 后缀名拦截：*.jsp  访问所有后缀名为jsp资源时，过滤器都会被执行
         4. 拦截所有资源:/*   访问所有资源时，过滤器都会被执行

          

      2. 拦截方式配置：资源被访问的方式

         1. 注解配置：
            1. 设置dispatcherTypes属性
               1. REQUEST：默认值，浏览器直接请求资源
               2. FORWARD：转发访问资源
               3. INCLUDE：包含访问资源
               4. ERROR：错误跳转资源
               5. ASYNC：异步访问资源
         2. web.xml配置
            1. 设置<dispatcher></dispatcher>标签即可

       

   5. 过滤器链（配置多个过滤器）

      1. 执行顺序：如果有两个过滤器:过滤器1和过滤器2

         1. 过滤器1

         2. 过滤器2

         3. 资源执行

         4. 过滤器2

         5. 过滤器1

             

      2. 过滤器先后顺序问题：

         1. 注解配置：按照类名的字符串比较规则比较，值小的先执行

            1. 如：AFilter和BFilter，AFilter就先被执行

         2. web.xml配置：<filter-mapping>谁定义在上边，就先执行

             



## Listener:监听器

1. 概念：web的三大组件之一。

   1. 事件监听机制
      1. 事件	：一件事情
      2. 事件源：事件发生的地方
      3. 监听器：一个对象
      4. 注册监听：将事件，事件源，监听器绑定在一起。当事件源上发生某个时间后，执行监听器代码

2. ServletContextListener：监听ServletContext对象的创建和销毁

   1. 方法：

      void contextDestoryed(ServletContextEvent sce):ServletContext 对象被销毁之前会调用该方法

      void contextInitialized(ServletContextEvent sce):ServletContext对象创建后会调用该方法

