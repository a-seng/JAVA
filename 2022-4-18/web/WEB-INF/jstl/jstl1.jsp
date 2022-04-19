<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>if标签</title>
</head>
<body>
<%--
c:if标签
  属性：
  test必须属性，接收boolean表达式


--%>
<c:if test="true">
  <h1>我是真的1...............</h1>

</c:if>

<%
  List list=new ArrayList<>();
  list.add("aaaa");
  request.setAttribute("list",list);

  request.setAttribute("number",4);

%>
<c:if test="${not empty list}">
  遍历集合
</c:if>

<c:if test="${number%2 !=0}">
  ${number}为奇数
</c:if>


</body>
</html>
