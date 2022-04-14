# HTML

## web概念概述

1. 软件架构

   1. C/S
   2. B/S

2. B/S架构详解

   1. 静态资源
      1. 使用静态页面开发技术发布的资源
      2. 特点
         1. 所有用户访问得到的结果是一样的
         2. 如文本，图片，音频，视频，HTML，CSS，JavaScript
         3. 如果用户请求的是静态资源，那么服务器会直接将静态资源发送个浏览器，浏览器中内置了静态资源的解析引擎，可以展示静态资源
   2. 动态资源
      1. 使用动态网页及时发布的资源
      2. 特点
         1. 所有用户访问，得到的结果不一样
         2. 如jsp/servlet,php,asp
         3. 如果用户请求的是动态资源，那么服务器会执行动态资源，转换为静态资源，在发送给浏览器
   3. 静态资源
      1. HTML
      2. CSS
      3. JavaScript

3. HTML

   1. 概念
      1. Hyper Text Markup Language 超文本标记语言
   2. 快速入门
      1. html文档后缀名 .thml 或者 .htm
      2. 标签分为
         1. 围堵标签：有开始标签和结束标签。如<html></html>
         2. 自闭和标签:开始标签和结束标签在一起如<br/>
      3. 标签可以嵌套
         1. 但不可你中有我，我中有你
            1. 错误<a><b></a></b>

4. 标签

   1. 文本标签

      1. 注释：<!--注释内容-->

      2. *<h1>*to<h6>:标题标签
             	字体逐渐递减
         </h1>

      3. *<p>*段落标签

      4. *<br>:换行标签*

      5. *<hr>*展示一条水平线

         ​	属性

         ​	color ：颜色

         ​	width:宽度

         ​	size:高度

         ​	align:对齐方式

         ​		center：居中

         ​		left:左对齐

         ​		right:右对齐

         *<b>*字体加粗

         *<i>字体斜体*

         *<font>字体标签*

         <hr color"red" width="200" size="10" align="left"/>

   2. ```html
          <h1>h1~h6 标题标签  逐渐变小</h1>
          <p>段落标签</p>
          <hr color="red" width="200" size="10" align="left">一条线
          align对其方式
              center:居中
              left:左对齐
              right:右对齐
          <b>加粗标签</b>
          <i>斜体标签</i>
          <font color="red" size="5" face="楷体">字体标签</font>
          <br>换行标签
      
          属性定义:
              color:
                  1.英文单词
                  2.rgb(值1，值2，值3）：值的范围:0~255 如rgb(0,0,255)
                  3.#值1值2值3：值的范围:00~FF.如#FF00FF
              width:
                  1.数值：width=‘20’，数值的单位，默认是px（像素）
                  2.数值%：占比相对于父元素的比例
      
          图片标签
          <img src="填写路径" align="靠那边对齐" alt="图片加载失败显示的东西"/>
      
          路径
          相对路径
              以.开头的路径
                  ./代表当前目录
                  ../代表上一级目录
      
          有序列表ol
          <ol type="可以填的类型" start="可以从哪里开始">
                  <li>这里是每个列表</li>
          </ol>
          无序列表
          <ul>
              <li></li>
          </ul>
      
      超链接
          <a href="这里面填写点击后的超链接" target="这里田点击后是 新页面还是当前页面">_self是当前页面跳转
          ，_blank是重新打开一个页面</a>
      
      块标签
          <span>文本信息在一行展示，行内标签，内联标签</span>
          <div>每一个div占满一整行，块级标签</div>
      
      语义化标签：提高程序的可读性
          <header>
              页眉
          </header>:
          <footer>页脚</footer>
      
      表格标签
          <table border="边框" width="这个表格的宽度"
                 cellpadding="单元边沿与其内容之间的空白"
                 cellspacing="规定单元格之间的空白"
                 bgcolor="背景色"
                 align="对齐方式"
          ></table>
          <tr>
              定义行
              colspan:合并列
              rowspan：合并行
          </tr>
      
          <td>定义单元格</td>
          <th>定义表头单元格</th>
          <caption>表格标题</caption>
          <thead>表示表格的头部分</thead>
          <tbody>体部分</tbody>
          <tfoot>脚部分</tfoot>
      ```

   3. 表单标签

      ```html
      表单标签
          <form>用于定义表单的，可以定义一个范围，范围代表采集用户数据的范围</form>
          属性
              action：指定提交数据的URL
              method制定提交的方式
                  分类一共七种
                  get:
                      1.请求参数会在地址栏中
                      2.请求参数大小是由限制的
                      3.不太安全
                  post:
                      请求参数不会再地址栏中显示
                      请求参数大小没有限制
                      较为安全
              表单项中的数据想要被提交：必须指定其name属性
      <form action="提交到什么地址去" method="什么方式提交">
          用户名<input name="这里是name属性">
          <input type="这里可以指定 submit 提交" value="这里填写的值会显示出来">
      </form>
      ```

   4. 表单项标签：

      1. input：可以通过type属性值，改变元素展示的样式

         1. type属性
            1. text：文本输入框，默认值
               1. placeholder：指定输入框的提示信息，当输入框的内容发生变化，会自动清空提示信息
            2. password：密码输入框
            3. radio：单选框
               1. 注意
                  1. 想让多个单选框实现单选的效果，则多个单选框的name属性值必须一样
                  2. 一般会给每一个单选框提供value属性，指定其被选中后提交的值
                  3. checked属性，可以指定默认值
            4. checkbox：复选框
               1. 注意
                  1. 一般会给每一个单选框提供value属性，指定其被选中后提交的值
                  2. checked属性，可以指定默认值
            5. label 标签 for属性 和里面的id属性相同 点击label包围的东西，点击他光标就会转到对于id的地方
            6. file 文件选择框
            7. hidden :隐藏域，用于提交一些信息
            8. 按钮
               1. submit：普通提交按钮，可以提交表单
               2. button：普通按钮
               3. image：图片提交按钮
                  1. src属性指定图片的路径
            9. select:下拉列表
               1. 子元素：option，指定列表项
            10. textarea:文本域
                1. cols:指定列数，每一行有多少个字符
                2. rows：默认多少行

      2. ```html
         <from action="#" method="get">
             <label for="username">用户名：</label><input type="text" name="username" placeholder="请输入用户名" id="username"><br>
             密码:<input type="password" name="password" placeholder=""><br>
             性别:<input type="radio" name="gender" value="male">男
                 <input type="radio" name="gender" value="female">女
         
             爱好<input type="checkbox" name="hobby" value="这里是要提交的值">这里是显示出来的值
                 <input type="checkbox" name="hobby" value="java" checked 这里是默认选中>Java
                 <input type="checkbox" name="hobby" value="game">游戏
         
             <input type="submit" value="登录">
             图片:<input type="file" name="file"><br>这里可以选择一个文件
             隐藏域:<input type="hidden" name="id" value="aaa"><br>这个东西看不见
             取色器:<input type="color" name="color"><br>选一个颜色
             生日:<input type="date" name="birthday"><br>一个年月日日期
             生日:<input type="datatimee-local" name="birthday"><br>一个年月日时分秒日期
             邮箱:<input type="email" name="email"><br>一个正则表达式邮箱的
             年龄<input type="number" name="age"><br>一个只可以填数字
             省份：<select name="province">
             	<option value="">---请选择----</option>
               	<option value="1">---北京----</option>
               	<option value="2">---上海----</option>
               	<option value="3" selected>---陕西----</option>这里是选中默认值
             </select>
             自我描述:<textarea cols="20" rows="5" name="des"></textarea>
             
             
         </from>
         ```





# CSS

## css的使用

1. 内联样式

   1. ```html
      在标签内使用style属性指定的css代码
      如：<div style="color:red;">hello css</div>
      ```

2. 内部样式

   ```html
   在head标签内，定义style标签，style标签的标签体内容就是css代码
   如：
       <style>
           div{
               color:blue;
           }
       </style>
       <div>hello css</div>
   </body>
   </html>
   ```

3. 外部样式

   ```html
   1.定义css资源文件
   2.在head标签内，定义link标签，引入外部的资源文件
       如：
       a.css文件
       div{
       color:green;
   }
   <link rel="stylesheet" href="css/a.css">
   <div>hello css</div>
   <div>hello css</div>
   ```

4. css语法

   1. 格式

      1. 选择器{

         ​	属性名1：属性值1；

         ​	属性名2：属性值2；

         ...............

         }

      2. 选择器：筛选具有相似特征的元素

      3. 注意

         1. 每一对属性需要使用；隔开，最后一对属性可以不加

5. 选择器

   1. 基础选择器

      1. id选择器：选择具体的id属性值的元素，建议在一个html页面中id值唯一
         1. 语法，#id属性值{}
      2. 元素选择器：选择具有相同标签名称的元素
         1. 语法：标签名称{}
         2. 注意：id选择器优先级高于元素选择器
      3. 类选择器：选择具有相同的class属性值的元素
         1. 语法：.class属性值{}
         2. 类选择器优先级大于元素

      ```html
      <style>
          #div1{
              color: red;
          }
          div{
              color: green;
          }
          .cls1{
              color: blue;
          }
      </style>
      
      <div id="div1">这里是id选择器 </div>
      <div>这里是元素选择器</div>
      <p class="cls1">这里是类选择器</p>
      ```

   2. 拓展选择器

      1. 选择所有元素
         1. 语法：*{}
      2. 并集选择器
         1. 选择器1，选择器2{}
      3. 子选择器：筛选选择器1元素下的选择器2
         1. 选择器1 选择器2{}
      4. 父选择器：筛选选择器2的父元素选择器1
         1. 选择器1>选择器2{}
      5. 属性选择器：选择元素名称，属性名=属性值的元素
         1. 属性名称[属性名="属性值"]{}
      6. 伪类选择器：选择一些元素具有的状态
         1. 语法 ： 元素：状态{}
         2. 如：<a>
            1. 状态：
               1. link：初始化的状态
               2. visited：被访问过的状态
               3. active：正在被访问的状态
               4. hover：鼠标悬浮的状态
   
6. 属性

   1. 字体，文本

      1. font-size:字体大小
      2. color：文本颜色
      3. text-align：对其方式
      4. line-height：行高

   2. 背景

      1. background

   3. 尺寸

      1. width：宽度
      2. height：高度

   4. 边框

      1. border:设置边框

   5. 盒子模型

      1. margin：外边距
      2. padding：内边距
         1. 默认情况下内边距会影响整个盒子大小
         2. box-sizing：border-box； 设置盒子的属性，让width和height就是最终盒子的大小
      3. float浮动
         1. left
         2. right

   6. 

   7. ```html
      
      <style>
      	p{
      		color:#FF0000;
      		font-size:30px;
      		text-align:center;
      		line-height:200px;
      		
      		border:1px solid red;
      	}
      	div{
      		border:1px solid red;
      		height: 200px;
      		width: 200px;
      		background: url("背景图片路径") no-repeat center;//no-repeat不重复
      	}
      
      </style>
      
      <p>传智播客</p>
      <div>
      	黑马程序员
      </div>
      ```

      <style>
      	div{
      		border:1px solid red;
      		width:1;
      	}
      	.div1{
      		width:100px;
      		height:100px;
      		margin:50px;//外边距
      	}

      ```html
      <style>
      	div{
      		border:1px solid red;
      		width:1;
      	}
      	.div1{
      		width:100px;
      		height:100px;
      		margin:50px;//外边距
      	}
      .div2{
      	width: 200px;
      	height: 200px;
      	padding: 50px;
      	/*
      	设置盒子的属性，
      	*/
      	box_sizing: border-box;//让盒子的大小不随外边距的大小变化
      }
      
      .div3{
      	float:left;
      }
      .div4{
      	float:left;
      }
      .div5{
      	float:right;//浮动
      }
      </style>
      <div class="div3">aaaa</div>
      <div class="div4">aaaa</div>
      <div class="div5">aaaa</div>
      ```

      

      <div class="div2">
      	<div class="div1"></div>
      </div>
      

