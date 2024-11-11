package org.example.restservice.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.restservice.db.DBManager;
import org.example.restservice.model.Post;
import org.example.restservice.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserPostsController", urlPatterns = "/user_posts")
public class UserPostsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("idForPosts");

        User user = DBManager.getUserById(userId);

        List<Post> posts = DBManager.getPostsByUserId(userId);

        req.setAttribute("user", user);
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("WEB-INF/view/user_posts.jsp").forward(req, resp);
    }
}

