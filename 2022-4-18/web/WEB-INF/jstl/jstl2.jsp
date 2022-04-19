<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>choose标签</title>
</head>
<body>
    <%--
      使用choose标签取出数字  相当于switch声明
      使用when标签做数字判断   相当于case
      otherwise标签做其他情况的声明，相当于default

    --%>

    <c:choose>
      <c:when test="${number == 1}">星期一</c:when>
      <c:when test="${number ==2 }">星期二</c:when>

      <c:otherwise>数字输入有误</c:otherwise>

    </c:choose>
</body>
</html>
