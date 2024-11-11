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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserModifyControllerTest extends TestCase {

    private UserModifyController userModifyController;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public void setUp() {
        userModifyController = new UserModifyController();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
    }

    public void testDoGet_UserFound() throws ServletException, IOException {
        String userId = "1";
        User mockUser = new User();
        mockUser.setId(Integer.parseInt(userId));
        mockUser.setUserName("testUser");
        mockUser.setEmail("test@example.com");

        try (MockedStatic<DBManager> dbManagerMocked = Mockito.mockStatic(DBManager.class)) {
            when(request.getParameter("idForModify")).thenReturn(userId);
            dbManagerMocked.when(() -> DBManager.getUserById(userId)).thenReturn(mockUser);
            RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
            when(request.getRequestDispatcher("WEB-INF/view/user_modify.jsp")).thenReturn(requestDispatcher);
            userModifyController.doGet(request, response);
            verify(request).setAttribute("user", mockUser);
            verify(requestDispatcher).forward(request, response);
        }
    }

    public void testDoGet_UserNotFound() throws ServletException, IOException {
        String userId = "1";

        try (MockedStatic<DBManager> dbManagerMocked = Mockito.mockStatic(DBManager.class)) {
            when(request.getParameter("idForModify")).thenReturn(userId);
            dbManagerMocked.when(() -> DBManager.getUserById(userId)).thenReturn(null);
            RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
            when(request.getRequestDispatcher("WEB-INF/view/user_modify.jsp")).thenReturn(requestDispatcher);
            userModifyController.doGet(request, response);
            verify(request).setAttribute("user", null);
            verify(requestDispatcher).forward(request, response);
        }
    }

    public void testDoPost_Success() throws ServletException, IOException {
        String userId = "1";
        String userName = "updatedUser ";
        String email = "updated@example.com";

        when(request.getParameter("username")).thenReturn(userName);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("id")).thenReturn(userId);

        try (MockedStatic<DBManager> mockedDBManager = Mockito.mockStatic(DBManager.class)) {
            userModifyController.doPost(request, response);
            verify(response).sendRedirect("/users");
            mockedDBManager.verify(() -> DBManager.modifyUser(userId, userName, email));
        }
    }
}