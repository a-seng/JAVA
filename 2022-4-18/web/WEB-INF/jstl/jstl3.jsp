<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>foreach标签</title>
</head>
<body>
<c:forEach begin="1" end="10" var ="i" step="2" varStatus="s">
  ${i} <h3>${s.index}</h3> <h4>${s.count}</h4><br>
</c:forEach>

<hr>
  <%
    List list=new ArrayList<>();
    list.add("aaaa");
    list.add("bbbb");
    list.add("cccc");

    request.setAttribute("list",list);


  %>

<c:forEach items="${list}" var="str" varStatus="s">
  ${s.index}
  ${s.count}
  ${str}
  <br>
</c:forEach>
  <hr>
<%
  List list=new ArrayList();
  list.add("aaaa");
  list.add("bbb");
  list.add("ccc");
%>

<c:forEach items="$list}" var="str" varStatus="s">
  ${s.index} ${s.count} ${str}<br>

</c:forEach>

</body>
</html>
