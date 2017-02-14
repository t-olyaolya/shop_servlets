<%@ page import="com.company.Service.LangBundle" %><%--
  Created by IntelliJ IDEA.
  User: tyuly
  Date: 08.02.2017
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><%=LangBundle.bundle.getString("shop")%></title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<a href="account.jsp">${user}</a>
<table border="1">
    <thead>
    <h1><%=LangBundle.bundle.getString("sale")%></h1>
    <tr>
        <th>Id</th>
        <th><%=LangBundle.bundle.getString("item")%></th>
        <th><%=LangBundle.bundle.getString("desc")%></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="item">
        <tr>
            <td><c:out value="${item.id}" /></td>
            <td><c:out value="${item.name}" /></td>
            <td><c:out value="${item.description}" /></td>
            <td><a
                    href="buy?action=buy&item.id=<c:out value="${item.id }"/>"><%=LangBundle.bundle.getString("buy")%></a></td>
            </c:forEach>
        </tr>
    </tbody>
</table>
<p>
</p>
</body>
</html>
