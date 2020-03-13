<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 3/4/2020
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new book</title>
</head>
<body>
<h1>
    <a href="/books">Book Management</a>
</h1>
<h1>Create new book</h1>
<form method="post">
    <table>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" id="name"></td>
        </tr>
        <tr>
            <td>Author</td>
            <td><input type="text" name="author" id="author"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="save"></td>
        </tr>
    </table>
</form>
</body>
</html>
