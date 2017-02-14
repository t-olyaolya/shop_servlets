<%--
  Created by IntelliJ IDEA.
  User: tyuly
  Date: 09.02.2017
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.company.Service.LangBundle"%>
<html>
<head>
    <title><%LangBundle.bundle.getString("myAcc");%></title>
</head>
<body>
<a href="account?action=mybids"><%=LangBundle.bundle.getString("purchases")%></a>
<a href="account?action=myitems"><%=LangBundle.bundle.getString("itemMy")%></a>
<a href="account?action=allitems"><%=LangBundle.bundle.getString("items")%></a>
<a href="account?action=edituser"><%=LangBundle.bundle.getString("editProf")%></a>
<a href="account?action=dltuser"><%=LangBundle.bundle.getString("deleteProf")%></a>
<a href="account?action=logout"><%=LangBundle.bundle.getString("logOut")%></a>
</body>
</html>
