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
<h1>Book Management</h1>
<form method="get">
    <input hidden name="action" value="search"/>
    <div>
        <input type="text" name="nameSearch" class="form-control" placeholder="input name">
    </div>
    <button class="btn btn-primary mb-2" type="submit" value="search">Search
    </button>
</form>
<h2>
    <a href="/books?action=create">Add new book</a>
</h2>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Books</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td><c:out value="${book.id}"/></td>
                <td><c:out value="${book.name}"/></td>
                <td><c:out value="${book.author}"/></td>
                <td>
                    <a href="/books?action=edit&id=${book.id}">Edit</a>
                    <a href="/books?action=delete&id=${book.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
