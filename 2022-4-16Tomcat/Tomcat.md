# Tomcat

## Tomcat的基本使用

1. 关闭
   1. 正常关闭
      1. bin/shutdown.bat
      2. ctrl+c
   2. 强制关闭
      1. 点击窗口x
2. 配置
   1. 部署项目的方式
      1. 直接将项目放倒webapps目录下即可
         1. /hello:项目的访问路径-->虚拟目录
         2. 简化部署：将项目打成一个war包，再将wat包放置到webapps目录下。
            1. war包会自动解压
      2. 配置conf/server.xml文件
         1. 在<Host>标签体中配置
         2. <Context docBase="D:\hello" path="/hehe"/>
         3. docBase:项目存放的路径
         4. path:虚拟目录
      3. 在conf\Catalina\localhost创建任意名称的xml文件，在文件中编写
         1. <Context docBase="D:\hello"/>
            1. 虚拟目录:xml文件名称
   2. 静态项目和动态项目
      1. 目录结构
         1. java动态项目的目录结构：
            1. 项目的根目录
               1. WEB-INF目录：
                  1. web.xml:web目录的核心配置文件
                  2. classes目录:放置字节码文件的目录
                  3. lib目录：放置依赖的jar包

## Tomcat结合IDEA

1. 快速入门

   1. 创建JavaEE项目

   2. 定义一个类，实现Servlet接口

      1. public class ServletDemo1 **implements Servlet**

   3. 实现接口中的抽象方法

   4. 配置Servlet

      1. 在web.xml中配置：

      2. ```xml
         <servlet>
             <servlet-name>demo1</servlet-name>
             <servlet-class>cn.aseng.web.servlet.servletDemo1</servlet-class>
         </servlet>
         
         <servlet-mapping>
             <servlet-name>demo1</servlet-name>
             <url-pattern>/demo1</url-pattern>
         </servlet-mapping>
         ```

2. 执行原理

   1. 当服务器接受到客户端浏览器的请求后，会解析请求的URL路径，获取访问Servlet的资源路径
   2. 查找web.xml文件，是否有对应的<url-pattern>标签体内容
   3. 如果有，则在找到对应的<servlet-class>全类名
   4. tomcat会将字节码文件加载静内存，并创建其对象
   5. 调用其方法

3. Servlet中的生命周期方法：

   1. 被创建：执行init方法，只执行一次
      1. Servlet什么时候被创建？	
         1. 默认情况下，第一次访问时，Servlet被创建
         2. 可以配置执行Servlet的创建时机
            1. 在<servlet>标签下配置
               1. <load-on-startup>的值为负数
            2. 在服务器启动时创建
               1. <load-on-startup>的值为0或正整数
      2. Servlet的init方法，只执行一次，说明一个Servlet在内存中存在一个对象，Servlet是单列的
         1. 多个用户同时访问时，可能存在线程安全问题，
         2. 解决：尽量不要在Servlet中定义成员变量，即使定义了成员变量，也不要修改值
   2. 提供服务：执行service方法，执行多次
      1. 每次访问Servlet时，Service方法都会被调用一次。
   3. 被销毁：执行destroy方法，只执行一次
      1. Servlet被销毁时执行，服务器关闭时，Servlet被销毁
      2. 只有服务器正常关闭时，才会执行destroy方法。
      3. destroy方法在Servlet被销毁前执行，一般用于释放资源

4. Servlet3.0：

   1. 好处

      1. 支持注解配置，可以不需要web.xml了

   2. 步骤

      1. 创建JavaEE项目，选择Servlet的版本3.0以上，可以不创建web.xml

      2. 定义一个类，实现Servlet接口

      3. 复写方法

      4. 在类上使用@WebServlet注解，进行配置

         1. @WebServlet("资源路径")

         2. @Retention(RetentionPolicy.RUNTIME)

         3. @Documented

         4. ```xml
            		@Target({ElementType.TYPE})
               		@Retention(RetentionPolicy.RUNTIME)
               		@Documented
               		public @interface WebServlet {
               		    String name() default "";//相当于<Servlet-name>
               		
               		    String[] value() default {};//代表urlPatterns()属性配置
               		
               		    String[] urlPatterns() default {};//相当于<url-pattern>
               		
               		    int loadOnStartup() default -1;//相当于<load-on-startup>
               		
               		    WebInitParam[] initParams() default {};
               		
               		    boolean asyncSupported() default false;
               		
               		    String smallIcon() default "";
               		
               		    String largeIcon() default "";
               		
               		    String description() default "";
               		
               		    String displayName() default "";
               		}
                      
            ```

5. IDEA与tomcat配置

   1. IDEA会为每一个tomcat部署的项目单独创建一份配置文件
      1. 查看控制台的log:Using CATALINA_BASE:"路径"
   2. 工作空间项目 和 tomcat部署的web项目
      1. tomcat真正访问的是"tomcat部署的web项目","tomcat部署的web项目"对应着"工作空间项目"的web目录下的所有资源
   3. 断点调试:使用"小虫子"启动debug启动

