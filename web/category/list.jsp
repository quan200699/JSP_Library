<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 3/4/2020
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Library Management Application</title>
</head>
<body>
<h1>Category Management</h1>
<h2>
    <a href="/categories?action=create">Add new category</a>
</h2>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Categories</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td><c:out value="${category.id}"/></td>
                <td><c:out value="${category.name}"/></td>
                <td>
                    <a href="/categories?action=edit&id=${category.id}">Edit</a>
                    <a href="/categories?action=delete&id=${category.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
