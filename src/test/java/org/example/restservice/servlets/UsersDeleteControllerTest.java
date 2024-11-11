package org.example.restservice.servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import org.example.restservice.db.DBManager;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsersDeleteControllerTest extends TestCase {

    private UsersDeleteController usersDeleteController;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public void setUp() {
        usersDeleteController = new UsersDeleteController();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
    }

    public void testDoPost_Success() throws IOException {
        String ids = "1 2 3";
        when(request.getParameter("idsForDelete")).thenReturn(ids);
        usersDeleteController.doPost(request, response);
        DBManager.deleteUsers(ids.split(" "));
        verify(response).sendRedirect("/users");
    }
}
