package org.example.restservice.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import org.example.restservice.db.DBManager;
import org.example.restservice.model.Post;
import org.example.restservice.model.User;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserPostsControllerTest extends TestCase {

    public void testDoGet() throws ServletException, IOException {
        UserPostsController userPostsController = new UserPostsController();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        String userId = "1";
        User mockUser = new User();
        mockUser.setId(Integer.parseInt(userId));
        mockUser.setUserName("TestName");

        List<Post> mockPosts = new ArrayList<>();
        mockPosts.add(new Post(1, "Post 1", "Content 1", new User()));
        mockPosts.add(new Post(2, "Post 2", "Content 2", new User()));

        try (MockedStatic<DBManager> dbManagerMockedStatic = mockStatic(DBManager.class)) {
            dbManagerMockedStatic.when(() -> DBManager.getUserById(userId)).thenReturn(mockUser);
            dbManagerMockedStatic.when(() -> DBManager.getPostsByUserId(userId)).thenReturn(mockPosts);

            RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
            when(request.getRequestDispatcher("WEB-INF/view/user_posts.jsp")).thenReturn(dispatcher);
            when(request.getParameter("idForPosts")).thenReturn(userId);

            userPostsController.doGet(request, response);

            verify(request).setAttribute("user", mockUser);
            verify(request).setAttribute("posts", mockPosts);

            verify(dispatcher).forward(request, response);
        }
    }
}