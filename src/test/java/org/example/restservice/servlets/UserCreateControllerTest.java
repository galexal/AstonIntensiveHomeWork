package org.example.restservice.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import org.example.restservice.db.DBManager;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserCreateControllerTest extends TestCase {

    private UserCreateController userCreateController;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;

    protected void setUp() {
        userCreateController = new UserCreateController();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        requestDispatcher = Mockito.mock(RequestDispatcher.class);
    }

    public void testDoGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("WEB-INF/view/user_create.jsp")).thenReturn(requestDispatcher);
        userCreateController.doGet(request, response);
        verify(request).getRequestDispatcher("WEB-INF/view/user_create.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    public void testDoPost_Success() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("testUser  ");
        when(request.getParameter("email")).thenReturn("test@example.com");

        try (MockedStatic<DBManager> mockedDBManager = Mockito.mockStatic(DBManager.class)) {
            userCreateController.doPost(request, response);
            verify(response).sendRedirect("/users");
            mockedDBManager.verify(() -> DBManager.createUser("testUser  ", "test@example.com"));
        }
    }
}