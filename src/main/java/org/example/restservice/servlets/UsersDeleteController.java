package org.example.restservice.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.restservice.db.DBManager;

import java.io.IOException;

@WebServlet(name = "UsersDeleteController", urlPatterns = "/users_delete")
public class UsersDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ids = req.getParameter("idsForDelete");
        DBManager.deleteUsers(ids.split(" "));
        resp.sendRedirect("/users");
    }
}
