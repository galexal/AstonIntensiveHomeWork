package org.example.restservice.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.restservice.db.DBManager;
import org.example.restservice.model.User;

import java.io.IOException;

@WebServlet(name = "UserModifyController", urlPatterns = "/user_modify")
public class UserModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idForModify");
        User user = DBManager.getUserById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/view/user_modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String email = req.getParameter("email");
        String id = req.getParameter("id");

        if (userName.isEmpty() || email.isEmpty()) {
            req.setAttribute("error", 1);
            req.getRequestDispatcher("WEB-INF/view/user_modify.jsp").forward(req, resp);
            return;
        }
        DBManager.modifyUser(id, userName, email);
        resp.sendRedirect("/users");
    }
}
