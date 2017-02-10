<%--
  Created by IntelliJ IDEA.
  User: tyuly
  Date: 08.02.2017
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title></title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="itemaction" method="get">
    <div style="padding: 100px 0 0 250px;">
        <div id="login-box">
            <h2>Заполните поля</h2>
            <br>
            <br>
            <div id="login-box-name" style="margin-top:20px;">Название:</div>
            <div id="login-box-field" style="margin-top:20px;">
                <input name="name" class="form-login" title="name" value="" size="30" maxlength="50" />
            </div>
            <div id="login-box-name" style="margin-top:20px;">Описание:</div>
            <div id="login-box-field" style="margin-top:20px;">
                <input name="description" class="form-login" title="description" value="" size="30" maxlength="50" />
            </div>
            <br />
            <input style="margin-left:100px;" type="submit" value="Сохранить" />
        </div>
    </div>
</form>
</body>
</html>
