package com.codegym.dao;

import com.codegym.model.Book;

import java.util.List;

public interface IBookDao extends IGeneralDao<Book> {
    List<Book> findByNameContaining(String name);
}
