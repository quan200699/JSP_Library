package com.codegym.dao;

import com.codegym.StaticVariable;
import com.codegym.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        List<Book> books = new ArrayList<>();
        try (Connection connection = StaticVariable.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StaticVariable.SELECT_ALL_BOOK_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                books.add(new Book(id, name, author));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
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
