package com.codegym.controller;

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

@WebServlet(name = "CategoryServlet", urlPatterns = "/categories")
public class CategoryServlet extends HttpServlet {
    private CategoryDao categoryDao;

    public void init() {
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
                default: {
                    findAllCategory(req, resp);
                    break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category category = categoryDao.findById(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("category/delete.jsp");
        req.setAttribute("category", category);
        requestDispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category category = categoryDao.findById(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("category/edit.jsp");
        req.setAttribute("category", category);
        requestDispatcher.forward(req, resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("category/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void findAllCategory(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        List<Category> categories = categoryDao.findAll();
        req.setAttribute("categories", categories);
        RequestDispatcher dispatcher = req.getRequestDispatcher("category/list.jsp");
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
                    insertCategory(req, resp);
                    break;
                }
                case "edit": {
                    updateCategory(req, resp);
                    break;
                }
                case "delete": {
                    deleteCategory(req, resp);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        categoryDao.removeById(id);
        resp.sendRedirect(req.getContextPath() + "categories");
    }

    private void updateCategory(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Category category = new Category(id, name);
        categoryDao.updateById(category);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("category/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void insertCategory(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String name = req.getParameter("name");
        Category category = new Category(name);
        categoryDao.insert(category);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("category/create.jsp");
        requestDispatcher.forward(req, resp);
    }
}
