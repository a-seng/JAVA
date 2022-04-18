# Session&&Cookie

## 今日内容

1. 会话技术
   1. Cookie
   2. Session
2. JSP：入门技术

## 会话技术

1. 会话：一次会话中包含多次请求和响应

   一次会话：浏览器第一次给服务器资源发送请求，会话建立，直到有一方断开为止

2. 功能：在一次会话的范围内的多次请求间，共享数据

3. 方式：

   1. 客户端会话技术：Cookie
   2. 服务器端会话技术：Session

## Cookie：

1. 概念：客户端会话技术，将数据保存到客户端

    

2. 快速入门：

   1. 使用步骤：

      1. 创建Cookie对象，绑定数据

         new Cookie（String name，String value）

      2. 发送Cookie对象

         response.addCookie（Cookie cookie）

      3. 获取Cookie[]   request.getCookies()

      4. ```java
         //先获取生成的验证码
         HttpSession session = request.getSession();
         String  checkCode_session = (String)session.getAttribute("checkCode_session");
         //删除session中存储的验证码
         session.removeAttribute("checkCode_session");
         //设置Cookie
         request.getSession().setAttribute("checkCode_session",cCs);
         
         ```

3. 实验原理

   基于响应头set-cookie和请求头cookie实现

    

4. cookie的细节

   1. 一次可不可以发送多个cookie？

      可以

      可以创建多个Cookie对象，使用response调用多次addCookie方法发送cookie即可

   2. cookie在浏览器中保存多长时间

      1. 默认情况下，当浏览器关闭后，Cookie数据被销毁

      2. 持续化存储：

         setMaxAge（int seconds)

         ​	1.正数：将Cookie数据写到硬盘的文件中。持久化存储。并指定cookie存活时间，时间到后，cookie文件自动失效

         ​	2.负数：默认值

         ​	3.零：删除cookie信息

         ```java
         
         protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                 //1.创建Cookie对象
                 Cookie c1 = new Cookie("msg","setMaxAge");
                 //2.设置cookie的存活时间
                 //c1.setMaxAge(30);//将cookie持久化到硬盘，30秒后会自动删除cookie文件
                 //c1.setMaxAge(-1);
                 //c1.setMaxAge(300);
                 c1.setMaxAge(0);//删除Cookie
                 //3.发送Cookie
                 response.addCookie(c1);
             }
         ```

          

   3. cookie能不能存中文？

      1. tomcat8以前不可以直接存储
      2. tomcat8之后，中文可以，特殊字符不可以建议使用URL编码存储，URL解码解析

       

   4. cookie共享问题

      1. 假设在一个tomcat服务器中，部署了多个web项目，那么在这些web项目中cookie能不能共享？

         1. 默认情况下cookie不能共享

             

         2. setPath（String path）：设置cookie的获取范围，默认情况下，设置当前的虚拟目录

            1. 如果要共享，则可以将path设置为"/"

            2. ```java
               //1.创建Cookie对象
               Cookie c1 = new Cookie("msg","你好");
               //设置path，让当前服务器下部署的所有项目共享Cookie信息
               c1.setPath("/");
               ```

      2. 不同的tomcat服务器间cookie共享问题？

         1. setDomain(String path):如果设置一级域名相同，那么多个服务器之间cookie可以共享
            1. setDomain(".baidu.com"),那么tieba.baidu.com和news.baidu.com中的cookie可以共享

          

   5. Cookie的特点和作用

      1. cookie存储数据在客户端浏览器

      2. 浏览器对于单个cookie的大小有限制(4kb)以及对同一个域名下的总的cookie数量也有限制(20个)

          

      3. 作用：

         1. cookie一般用于存储少量的不太敏感的数据
         2. 在不登录的情况下，完成服务器对客户端的身份识别

   6. 案例：记住上一次访问时间

      1. 需求:
         1. 访问一个Servlet如果第一次，提示：您好，欢迎您首次访问
         2. 如果不是第一次：欢迎回来,您上次访问时间为：显示时间字符串
      2. 分析：
         1. 可以采用Cookie来完成
         2. 在服务器中的Servlet判断是否有一个名为lastTime的cookie
            1. 有：不是第一次
            2. 没有：是第一次
      3. 代码实现





## JSP：入门学习

1. 概念：

   1. Java Server Pages： java服务器端页面
      1. 可以理解为：一个特殊的页面，其中既可以指定定义的html标签，又可以定义java代码
      2. 用于简化书写！！！

2. 原理

   1. JSP本质上就是一个Servlet

    

3. JSP的脚本：JSP定义Java代码的方式

   1. <%  代码  %>:定义的java代码，在service方法中，service方法中可以定义什么，该脚本中就可以定义什么
   2. <%!  代码  %>:定义的java代码，在jsp转换后的java类的成员位置。
   3. <%=  代码  %>：定义的java代码，会输出到页面上，输出语句中可以定义什么，该脚本中就可以定义什么。

4. JSP的内置对象:

   1. 在jsp页面中不需要获取和创建，可以直接使用的对象
   2. jsp一共9个内置对象
   3. 今天学3个
      1. request
      2. response
      3. out:字符输出流对象，可以将数据输出到页面上，和response.getWriter()类似
         1. response.getWriter()和out.writer()的区别：
            1. tomcat服务器真正给客户端做出响应之前，会先找response缓冲区数据，再找out缓冲区数据
            2. response.getWriter()数据输出永远在out.writer()之前

5. 案例，改造Cookie案例





## Session：主菜

1. 概念：服务器端会话技术，在一次会话的多次请求间共享数据，将数据保存在服务器的对象中。，HttpSession

2. 快速入门：

   1. 获取HttpSession对象：

      1. HttpSession session = request.getSession();

   2. 使用HttpSession对象：

      1. Object getAttribute(String name)

      2. ```java
         //1.获取session
         HttpSession session = request.getSession();
         //2.获取数据
         Object msg = session.getAttribute("msg");
         System.out.println(msg);
         ```

      3. void setAttribute(String name,Object value)

      4. ```java
         //2.存储数据
         session.setAttribute("msg","hello session");
         ```

      5. void removeAttribute(String name)

3. 原理

   1. Session的实现是依赖于Cookie的。

    

4. 细节：

   1. 当客户机关闭后，服务器不关闭，两次获取session是否为同一个？

      1. 默认情况下，不是。

      2. 如果需要相同，则可以创建Cookie，键为JSESSIONID，设置最大存活时间，让cookie持久化保存

      3. 代码

      4. ```java
         //1.获取session
         HttpSession session = request.getSession();
         System.out.println(session);
         //期望客户端关闭后，session也能相同
         Cookie c = new Cookie("JSESSIONID",session.getId());
         c.setMaxAge(60*60);
         response.addCookie(c);
         ```

       

   2. 客户端不关闭，服务器关闭后，两次获取的session使用一个吗？

      1. 不是同一个，但是要确保数据不丢失,tomcat自动完成以下工作

         1. session的钝化：
            1. 在服务器正常关闭前,将session对象序列化到硬盘上
         2. session的活化
            1. 在服务器启动后，将session文件转化为内存中的session对象即可。

          

   3. session什么时候被销毁？

      1. 服务器关闭
      2. session对象调用invalidate().
      3. session默认失效时间 30分钟
         1. 选择配置修改////////////////////////////////////

    

5. session的特点

   1. session用于存储一次会话的多次请求的数据，存在服务器端

   2. session用于存储任意类型，任意大小的数据

       

      session与Cookie的区别

      ​	1.session存储数据在服务器端，Cookie在客户端

      ​	2.session没有数据大小限制，Cookie有

      ​	3.session数据安全，Cookie相对不安全