package com.codegym.dao;

import com.codegym.model.StaticVariable;
import com.codegym.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.codegym.model.StaticVariable.DELETE_BOOK_BY_ID_SQL;
import static com.codegym.model.StaticVariable.getConnection;

public class BookDao implements IBookDao {
    @Override
    public void insert(Book object) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StaticVariable.INSERT_BOOK_SQL)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getAuthor());
            preparedStatement.setInt(3, object.getCategory_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book findById(int id) {
        Book book = new Book();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StaticVariable.SELECT_BOOK_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                int categoryId = Integer.parseInt(resultSet.getString("category_id"));
                book = new Book(id, name, author, categoryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StaticVariable.SELECT_ALL_BOOK_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                int category_id = Integer.parseInt(resultSet.getString("category_id"));
                books.add(new Book(id, name, author, category_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean removeById(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = StaticVariable.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateById(Book book) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StaticVariable.UPDATE_BOOK_BY_ID_SQL)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getCategory_id());
            preparedStatement.setInt(4, book.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public List<Book> findByNameContaining(String name) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StaticVariable.FIND_BOOK_BY_NAME_CONTAINING_SQL)) {
            preparedStatement.setString(1, name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                name = resultSet.getString("name");
                int categoryId = Integer.parseInt(resultSet.getString("category_id"));
                books.add(new Book(id, name, author, categoryId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
