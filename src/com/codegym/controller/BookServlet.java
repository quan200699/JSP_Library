package com.codegym.controller;

import com.codegym.dao.BookDao;
import com.codegym.model.Book;

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

    public void init() {
        bookDao = new BookDao();
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

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookDao.findById(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("book/edit.jsp");
        req.setAttribute("book", book);
        requestDispatcher.forward(req, resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
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
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        Book book = new Book(id, name, author);
        bookDao.updateById(book);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("book/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void insertBook(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        Book book = new Book(name, author);
        bookDao.insert(book);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("book/create.jsp");
        requestDispatcher.forward(req, resp);
    }
}
