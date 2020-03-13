package com.codegym.dao;

import java.sql.SQLException;
import java.util.List;

public interface IGeneralDao<T> {
    void insert(T object) throws SQLException;

    T findById(int id);

    List<T> findAll();

    boolean removeById(int id) throws SQLException;

    boolean updateById(T t) throws SQLException;
}
