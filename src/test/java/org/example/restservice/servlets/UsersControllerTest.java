package org.example.restservice.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import org.example.restservice.db.DBManager;
import org.example.restservice.model.User;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UsersControllerTest extends TestCase {

    public void testDoGet() throws ServletException, IOException {
        UsersController usersController = new UsersController();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User(1, "testName1", "testMail1"));
        mockUsers.add(new User(2, "testName2", "testMail2"));

        try (MockedStatic<DBManager> dbManagerMockedStatic = mockStatic(DBManager.class)) {
            dbManagerMockedStatic.when(DBManager::getAllUsers).thenReturn(mockUsers);
            RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
            when(request.getRequestDispatcher("WEB-INF/view/users.jsp")).thenReturn(dispatcher);
            usersController.doGet(request, response);
            verify(request).setAttribute("users", mockUsers);
            verify(dispatcher).forward(request, response);
        }
    }
}