# Maven基础

## 依赖传递冲突问题

1. 路径优先：当依赖中出现相同的资源时，层级越深，优先级越低，层级越浅，优先级越高
2. 声明优先：当资源在相同层级被依赖时，配置顺序靠前的覆盖配置顺序靠后的
3. 特殊优先：当同级配置了相同资源的不同版本，后配置的覆盖先配置的

## 可选依赖

1. 可选依赖对外隐藏当前所依赖的资源--不透明

2. ```java
   <dependency>
     <groupId>junit</groupId>
     <artifactId>junit</artifactId>
     <version>4.11</version>
     <optional>true</optional>
     <scope>test</scope>
   </dependency>
   ```



## 排除依赖

排除依赖指主动断开依赖的资源，被排除的资源无需指定版本--不需要

```java
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.11</version>
  <exclusions>
    <exclusion>
      <groupId>org.hamcrest</groupId>
      <artifactId>hamcrest-core</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```





## 依赖范围

依赖的jar默认情况下可以在任意地方使用，可以通过scope标签设定其作用范围

作用范围

1. 主程序范围有效（main文件夹范围内）
2. 测试程序范围有效（test文件夹范围内）
3. 是否参与打包（package指令范围内）
4. ![image-20220425225632969](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20220425225632969.png)

## 依赖管理

1. 依赖范围传递性
   1. 带有依赖范围的资源在进行传递时，作用范围将受到影响![image-20220425225739998](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20220425225739998.png)

## 分模块开发

1. 模块中仅包含的那个当前模块对应的功能类与配置文件
2. spring核心配置根据模块功能不同进行独立制作
3. 当前模块所依赖的模块通过导入坐标的形式加入当前模块后才可以使用
4. web.xml需要加载所有的spring核心配置文件



## 聚合

作用：聚合用于快速构建maven工程，一次性构建多个项目/模块。

就是管理多个模块

制作方式：

​	创建一个空模块，打包类型定义为pom

```java
<packing>pom</packing>
```

​	定义当前模块进行构建操作时关联的其他模块名称

```
<modules>
  <module>../ssm_controller</module>
  <module>../ssm_serivce</module>
  <module>../ssm_dao</module>
  <module>../ssm_pojo</module>
</modules>
```

​		注意事项：参与集合操作的模块最终执行顺序与模块间的依赖关系有关，与配置顺序无关

模块的类型

​	pom,war,jar





## 继承

作用：通过继承可以实现在子工程中沿用父工程中的配置

​	maven中的继承与java中的继承相似，在子工程中配置继承关系

制作方式：

​	在子工程中声明其父工程坐标与对应的位置

```java
<parent>
  <groupId>com.aseng</groupId>
  <artifactId>ssm</artifactId>
  <version>1.0-SNAPSHOT</version>
  <!-- 填写父工程的pom文件  -->
  <relativePath>../ssm/pom.xml</relativePath>
</parent>
```



### 继承依赖定义

在父工程中定义依赖管理	

```java
<dependencyManagement>
  <!--具体的依赖-->
  <dependencies>
    <!--spring环境-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <artifactId>spring-core</artifactId>
      <version>5.2.12.RELEASE</version>
    </dependency>
  </dependencies>
</dependencyManagement>
```





### 继承依赖使用

在子工程中定义依赖关系，无需声明依赖版本，版本参照父工程中的依赖的版本

```java
<dependencies>
  <!--spring环境-->
  <dependency>
    <groupId>org.springframeword</groupId>
    <artifactId>spring-context</artifactId>
  </dependency>
</dependencies>
```





### 继承的资源

groupId:项目组ID，项目坐标的核心元素

version：项目版本，项目坐标的核心元素

description：项目的描述信息

organization：项目的组织信息

inceptionYear：项目的创始年份

url：项目的URL地址

developers：项目的开发者信息

contributors：项目的贡献者信息

distributionManagement：项目的部署信息

issueManagement：项目的缺陷跟踪系统消息

![image-20220426091259019](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20220426091259019.png)



### 继承与聚合

作用

1. 聚合用于快速构建项目
2. 继承用于快速配置

相同点

1. 聚合与继承的pom.xml文件打包方式均为pom，可以将两种关系制作到同一个pom文件中
2. 聚合与继承均属于设计模块，并无实际的模块内容

不容点：

1. 聚合是在当前模块中配置关系，聚合可以感知到参与聚合的模块有哪些
2. 继承是在子模块中配置关系，父模块无法感知哪些子模块继承了自己





## 属性

### 版本统一的重

### 属性类别：自定义属性

作用

​	等用于定义变量，方便统一维护

定义格式：

```java
<!-- 定义自定义属性-->
<properties>
  <spring.version>5.1.9.RELEASE</spring.version>
  <junit.version>4.12</junit.version>
</properties>
```



调用格式：

```java
<dependency>
  <groupId>org.springframeword</groupId>
  <artifactId>spring-context</artifactId>
  <version>${spring.version}</version>
</dependency>
```



### 属性类别：内置属性

作用

​	使用maven内置属性，快速配置

调用格式

${basedir}

${version}



### 属性类别：Setting属性

作用

​	使用maven配置文件setting.xml中的标签属性，用于动态配置

调用格式：

${settings.localRespository}

### 属性类别：java系统属性

作用

​	读取java系统属性

调用格式

​	${user.home}

系统属性查询方式

mvn help:system

### 属性类别：环境变量属性

作用

​	使用Maven配置文件setting.xml中的标签属性，用于动态配置

调用格式

${env.JAVA_HOME}

mvn help:system





## 版本管理

1. 工程版本
   1. SNAPSHOT（快照版本）
      1. 项目开发过程中，为方便团队成员合作，解决模块间项目依赖和时时更新的问题，开发者对每个模块进行构建的时候，输出的临时性版本叫快照版本（测试阶段版本）
      2. 快照版本会随着开发的进展不断更新
   2. RELEASE（发布版本）
      1. 项目开发倒进入阶段里程碑后，想团队外部发布较为稳定的版本，这种版本所应对的构件文件是稳定的，即便进行功能的后续开发，也不会改变当前发布版本的内容，这种版本成为发布版本



### 工程版本号约定

约定规范：

1. <主版本>.<次版本>.<增量版本>.<里程碑版本>
2. 主版本：表示项目重大架构的变更，如spring5相较于spring4的迭代
3. 次版本：表示有较大的功能增加和变化，或者全面系统地修复漏洞
4. 增量版本：表示有重大漏洞的修复
5. 里程碑版本：表明一个版本的里程碑（版本内部）。这样的版本同下一个正式版相比，先对来说不是很稳定，有带更多的测试

范例：

​	5.1.9.RELEASE



## 资源配置

### 资源配置多文件维护![image-20220426101648027](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20220426101648027.png)



### 配置文件应用pom属性

作用

​	在任意配置文件中加载pom文件中定义的属性

调用格式

​	${jdbc.url}	

开启配置文件加载pom属性

```java
<!-- 配置资源文件对应的信息 -->
<resources>
  <resource>
    <!-- 设定配置文件对应的位置目录，支持使用属性动态设定路径-->
    <directory>${project.basedir}/src/main/resources</directory>
    <!-- 开启配置文件的资源加载过滤-->
    <filtering>true</filtering>
  </resource>
</resources>
```

配置文件中读取pom属性值

1. 在pom文件中设定配置文件路径
2. 开启加载pom属性过滤功能
3. 使用${属性名}格式引用pom属性





## 多环境开发配置

### 多环境配置

```java
<!--创建多环境-->
<profiles>
  <!--定义具体的环境：生产环境-->
  <profile>
    <!--定义环境对应的唯一名称-->
    <id>pro_env</id>
    <!--定义环境中专用的属性值-->
    <properties>
      <jdbc.url>jdbc:mysql://127.0.0.1:3306/ssm_db</jdbc.url>
    </properties>
    <!-- 设置默认启动-->
    <activation>
      <activeByDefault>true</activeByDefault>
    </activation>
  </profile>

  <!--定义具体的环境：开发环境-->
  <profile>
    <id>dev_env</id>
    ...
  </profile>
</profiles>
```



加载指定环境

作用

​	加载指定环境配置

调用格式

​	mvn 指令 -P 环境定义id

范例

​	mvn install -P pro-env

多环境开发配置

​	配置多环境

​	执行构建命令并指定加载对应环境配置信息



## 跳过测试（了解）

### 跳过测试环境的应用常见

整体模块功能未开发

模块中某个功能未开发完毕

单个功能更新调试导致其他功能失败

快速打包

........

#### 使用命令跳过测试

命令

mvn 指令 -D skipTests

注意事项

​	执行的指令生命周期必须包含测试环节

![image-20220426111333552](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20220426111333552.png)	

### 使用配置跳过测试

```java
<plugin>
  <artifactId>maven-surfire-plugin</artifactId>
  <version>2.22.1</version>
  <configuration>
    <!-- 设置跳过测试-->
    <skipTests>true</skipTests>
    <!--包含指定的测试用例-->
    <includes>
      <include>**/User*Test.java</include>
    </includes>
    <!-- 排除指定的测试用例-->
    <excludes>
      <exclude>**/User*TestCase.java</exclude>
    </excludes>
  </configuration>
</plugin>
```

## 私服

### 分模块合作开发

Nexus安装，启动与配置，Sonatype公司的一款maven私服产品

启动服务器（命令行启动）

​	nexus.exe /run nexus

访问服务器（默认端口：8081）

​	http://localhost:8081

修改基础配置信息

​	安装路径下etc目录中nexus-default.properties文件保持有nexus基础配置信息，列如默认访问端口

修改服务器运行配置信息

​	安装路径下bin目录中nexus.vmoptions文件保存有nexus服务器启动对应的配置信息，列如默认占用内存空间



### 仓库分类

宿主仓库hosted

​	保存无法从中央仓库获取的资源

​		自主研发

​		第三方非开源项目

代理仓库proxy

​	代理远程仓库，通过nexus访问其他公共仓库，列如中央仓库

仓库组group

​	将若干个仓库组成一个群组，简化配置

​	仓库组不能保存资源，属于设计型仓库



### 资源上传

上传资源时提供对应的信息

​	保存的位置（宿主仓库）

​	资源文件

​	对应坐标



### 访问私服配置（本地仓库访问私服）

配置本地仓库访问的权限（setting.xml）

```java
<servers>
  <server>
    <id>heima-release</id>
    <username>admin</username>
    <password>admin</password>
  </server>
  <server>
    <id>heima-snapshots</id>
    <username>admin</username>
    <password>admin</password>
  </server>
</servers>
```



配置本地仓库资源来源（setting.xml）

```java
<mirrors>
  <mirror>
    <id>nexus-heima</id>
    <mirrorOf>*</mirrorOf>
    <url>http://localhost:8081/repository/maven-public/</url>
  </mirror>
</mirrors>
```



### （项目工程访问私服）

配置当前项目访问私服上传资源的保存位置(pom.xml)

```java
<distributionManagement>
  <repository>
    <id>heima-release</id>
    <url>http://localhost:8081/repository/heima-release</url>
  </repository>
  
  <snapshotRepository>
    <id>heima-snapshots</id>
    <url>http://localhost:8081/repository/heima-snapshots</url>
  </snapshotRepository>
</distributionManagement>
```



发布资源到私服

mvn deploy