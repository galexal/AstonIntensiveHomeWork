package org.example.restservice.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.restservice.db.DBManager;

import java.io.IOException;

@WebServlet(name = "UserCreateController", urlPatterns = "/user_create")
public class UserCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/user_create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String email = req.getParameter("email");

        if (userName.isEmpty() || email.isEmpty()) {
            req.setAttribute("error", 1);
            req.getRequestDispatcher("WEB-INF/view/user_create.jsp").forward(req, resp);
            return;
        }
        DBManager.createUser(userName, email);
        resp.sendRedirect("/users");
    }
}
