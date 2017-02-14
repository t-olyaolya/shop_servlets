<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tyuly
  Date: 10.02.2017
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.company.Service.LangBundle"%>
<html>
<head>
    <title><%=LangBundle.bundle.getString("items")%></title>
</head>
<body>
<a href="account.jsp">${user}</a>
<table border="1">
    <thead>
    <h1><%=LangBundle.bundle.getString("items")%></h1>
    <tr>
        <th>Id</th>
        <th><%=LangBundle.bundle.getString("item")%></th>
        <th><%=LangBundle.bundle.getString("desc")%></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="item">
    <tr>
        <td><c:out value="${item.id}" /></td>
        <td><c:out value="${item.name}" /></td>
        <td><c:out value="${item.description}" /></td>
        </c:forEach>
    </tr>
    </tbody>
</table>
<p>
</p>
</body>
</html>