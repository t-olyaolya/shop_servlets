<%--
  Created by IntelliJ IDEA.
  User: tyuly
  Date: 08.02.2017
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.company.Service.LangBundle"%>
<html>
<head>
    <title><%=LangBundle.bundle.getString("info")%></title>
</head>
<body>
<h1>${info}</h1>
<a href="registry.jsp"><%=LangBundle.bundle.getString("reg")%></a>
<a href="login.jsp"><%=LangBundle.bundle.getString("log")%></a>
</body>
</html>