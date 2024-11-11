package org.example.restservice.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.restservice.db.DBManager;
import org.example.restservice.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersController", urlPatterns = "/users")
public class UsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = DBManager.getAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("WEB-INF/view/users.jsp").forward(req, resp);
    }
}
