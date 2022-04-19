<%@ page import="cn.itcast.domain.User" %>
<%@ page import="sun.rmi.server.UnicastServerRef" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el获取值</title>
</head>
<body>
<%
  User user=new User();
  user.setAge(19);
  user.setBirthday(new Date());
  user.setName("aseng");

  request.setAttribute("u",user);

  List list=new ArrayList();
  list.add("aaa");
  list.add("bbb");
  list.add(user);

  request.setAttribute("list",list);

  Map map=new HashMap<>();
  map.put("sname","李四");
  map.put("gender","男");
  map.put("user",user);

  request.setAttribute("map",map);



%>

<h3>el获取对象中的值</h3>
${requestScope.u}<br>
<%--
  *通过的是对象的属性来获取
    setter或getter方法，去掉set或get，在剩余部分，将首字母变为小写
    setName-->Name-->name

--%>
  ${requestScope.u.name}<br>
  ${u.age}
${u.birthday}
${u.birthday.month}

${u.birStr}
<h3>el获取List值</h3>

${list}<br>
${list[0]}<br>
${list[1]}<br>

<h3>el获取Map'值</h3>

${map.gender}
${map["gender"]}
${map.user.name}


</body>
</html>
