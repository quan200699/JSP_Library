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

import static com.codegym.StaticVariable.getConnection;

public class CategoryDao implements ICategoryDao{
    @Override
    public void insert(Category object) throws SQLException {
    }

    @Override
    public Category findById(int id) {
        return null;
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
        return false;
    }

    @Override
    public boolean updateById(Category category) throws SQLException {
        return false;
    }
}
