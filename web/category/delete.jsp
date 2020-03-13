<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 3/4/2020
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Library Management Application</title>
</head>
<body>
<h1>
    <a href="/categories">Category management</a>
</h1>
<h2>Delete Category</h2>
<form method="post">
    <c:if test="${category != null}">
        <input type="hidden" name="id" value="<c:out value='${category.id}' />"/>
    </c:if>
    <table>
        <tr>
            <td>Name</td>
            <td><c:out value='${category.name}'/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="delete"></td>
        </tr>
    </table>
</form>
</body>
</html>
