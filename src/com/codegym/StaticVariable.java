package com.codegym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StaticVariable {
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/library";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "123456";
    private static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public static final String INSERT_BOOK_SQL = "INSERT INTO books (name, author) VALUES (?, ?)";
    public static final String SELECT_ALL_BOOK_SQL = "SELECT * FROM books";
    public static final String SELECT_BOOK_BY_ID_SQL = "SELECT * FROM books WHERE id = ?";
    public static final String DELETE_BOOK_BY_ID_SQL = "DELETE FROM books WHERE id = ?";
    public static final String UPDATE_BOOK_BY_ID_SQL = "UPDATE books SET name = ?, author = ? WHERE id = ?";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
