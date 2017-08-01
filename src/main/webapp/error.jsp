<%--
  Created by IntelliJ IDEA.
  User: cvter
  Date: 2017/7/31
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>异常处理页面</title>
</head>
<body>
<% Exception ex = (Exception) request.getAttribute("message");%>
<h2>Exception:<%=ex.getMessage()%></h2>
</body>
</html>
