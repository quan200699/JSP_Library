package com.codegym.controller;

import com.codegym.dao.BookDao;
import com.codegym.dao.CategoryDao;
import com.codegym.model.Book;
import com.codegym.model.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookServlet", urlPatterns = "/books")
public class BookServlet extends HttpServlet {
    private BookDao bookDao;
    private CategoryDao categoryDao;

    public void init() {
        bookDao = new BookDao();
        categoryDao = new CategoryDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create": {
                    showCreateForm(req, resp);
                    break;
                }
                case "edit": {
                    showEditForm(req, resp);
                    break;
                }
                case "delete": {
                    showDeleteForm(req, resp);
                    break;
                }
                case "search": {
                    searchByName(req, resp);
                    break;
                }
                default: {
                    findAllBook(req, resp);
                    break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nameSearch");
        List<Book> books = bookDao.findByNameContaining(name);
        req.setAttribute("books", books);
        RequestDispatcher dispatcher = req.getRequestDispatcher("book/list.jsp");
        dispatcher.forward(req, resp);
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookDao.findById(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("book/delete.jsp");
        req.setAttribute("book", book);
        requestDispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookDao.findById(id);
        List<Category> categories = categoryDao.findAll();
        req.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("book/edit.jsp");
        req.setAttribute("book", book);
        requestDispatcher.forward(req, resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        List<Category> categories = categoryDao.findAll();
        req.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("book/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void findAllBook(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        List<Book> books = bookDao.findAll();
        req.setAttribute("books", books);
        RequestDispatcher dispatcher = req.getRequestDispatcher("book/list.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create": {
                    insertBook(req, resp);
                    break;
                }
                case "edit": {
                    updateBook(req, resp);
                    break;
                }
                case "delete": {
                    deleteBook(req, resp);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        bookDao.removeById(id);
        resp.sendRedirect(req.getContextPath() + "books");
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        int categoryId = Integer.parseInt(req.getParameter("category"));
        Book book = new Book(id, name, author, categoryId);
        bookDao.updateById(book);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("book/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void insertBook(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        int categoryId = Integer.parseInt(req.getParameter("category"));
        Book book = new Book(name, author, categoryId);
        bookDao.insert(book);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("book/create.jsp");
        requestDispatcher.forward(req, resp);
    }
}
