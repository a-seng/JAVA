<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  ${3>4}
  \{3>4}
<hr>

    <h3>算术运算符</h3>
    ${3+4}<br>
<%
    String str="";
    request.setAttribute("str",str);

    List list=new ArrayList();
    request.setAttribute("list",list);

%>

${not empty str}//判断是否为空
${not empty list}




</body>
</html>
