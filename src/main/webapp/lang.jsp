<%@ page import="com.company.Service.LangBundle" %><%--
  Created by IntelliJ IDEA.
  User: tyuly
  Date: 09.02.2017
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=LangBundle.bundle.getString("choose")%></title>
</head>
<body>
<a href="lang?action=ru"><%=LangBundle.bundle.getString("ru")%></a>
<a href="lang?action=en"><%=LangBundle.bundle.getString("en")%></a>
</body>
</html>
