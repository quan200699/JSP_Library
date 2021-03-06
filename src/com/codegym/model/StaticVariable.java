package com.codegym.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StaticVariable {
    public static final String INSERT_BOOK_SQL = "INSERT INTO books (name, author, category_id) VALUES (?, ?, ?)";
    public static final String SELECT_ALL_BOOK_SQL = "SELECT * FROM books";
    public static final String SELECT_BOOK_BY_ID_SQL = "SELECT * FROM books WHERE id = ?";
    public static final String DELETE_BOOK_BY_ID_SQL = "DELETE FROM books WHERE id = ?";
    public static final String UPDATE_BOOK_BY_ID_SQL = "UPDATE books SET name = ?, author = ?, category_id = ? WHERE id = ?";
    public static final String FIND_BOOK_BY_NAME_CONTAINING_SQL = "SELECT * FROM books WHERE name LIKE ?";

    public static final String INSERT_CATEGORY_SQL = "INSERT INTO categories (name) VALUES (?)";
    public static final String SELECT_ALL_CATEGORY_SQL = "SELECT * FROM categories";
    public static final String SELECT_CATEGORY_BY_ID_SQL = "SELECT * FROM categories WHERE id = ?";
    public static final String DELETE_CATEGORY_BY_ID_SQL = "DELETE FROM categories WHERE id = ?";
    public static final String UPDATE_CATEGORY_BY_ID_SQL = "UPDATE categories SET name = ? WHERE id = ?";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String jdbcDriver = "com.mysql.cj.jdbc.Driver";
            Class.forName(jdbcDriver);
            String jdbcUrl = "jdbc:mysql://localhost:3306/library";
            String jdbcUsername = "root";
            String jdbcPassword = "123456";
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
