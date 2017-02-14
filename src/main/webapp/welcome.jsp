<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tyuly
  Date: 10.02.2017
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${shop}</title>
</head>
<body>
<a href="lang.jsp">${choose}</a>
<h1>${info}</h1>
<a href="registry.jsp">${reg}</a>
<a href="login.jsp">${log}</a>
<table border="1">
    <thead>
    <h1>${shop1}</h1>
    <tr>
        <th>Id</th>
        <th>${item}</th>
        <th>${desc}</th>
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
