package com.codegym.model;

public class Book {
    private int id;

    private String name;

    private String author;

    private int category_id;

    public Book() {
    }

    public Book(int id, String name, String author, int category_id) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category_id = category_id;
    }

    public Book(String name, String author, int category_id) {
        this.name = name;
        this.author = author;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
