package com.codegym.dao;

import com.codegym.StaticVariable;
import com.codegym.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookDao implements IBookDao {
    @Override
    public void insert(Book object) throws SQLException {
        try (Connection connection = StaticVariable.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StaticVariable.INSERT_BOOK_SQL)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getAuthor());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public boolean removeById(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateById(Book book) throws SQLException {
        return false;
    }
}
