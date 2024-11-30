package org.example.restservice.servlets;

import com.google.gson.Gson;
import org.example.restservice.dao.UserDao;
import org.example.restservice.model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/users")
public class UserController extends HttpServlet {
    private final UserDao userDao = new UserDao();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = userDao.getAllUsers();
        String json = gson.toJson(users);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = gson.fromJson(req.getReader(), User.class);
        userDao.createUser (user);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = gson.fromJson(req.getReader(), User.class);
        userDao.modifyUser (user);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int userId = Integer.parseInt(req.getParameter("id"));
        userDao.deleteUser (userId);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}