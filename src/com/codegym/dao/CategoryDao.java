package com.codegym.dao;

import com.codegym.StaticVariable;
import com.codegym.model.Book;
import com.codegym.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.codegym.StaticVariable.*;

public class CategoryDao implements ICategoryDao{
    @Override
    public void insert(Category object) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StaticVariable.INSERT_CATEGORY_SQL)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category findById(int id) {
        Category category = new Category();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StaticVariable.SELECT_CATEGORY_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StaticVariable.SELECT_ALL_CATEGORY_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean removeById(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = StaticVariable.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateById(Category category) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StaticVariable.UPDATE_CATEGORY_BY_ID_SQL)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
