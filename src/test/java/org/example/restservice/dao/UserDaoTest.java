package org.example.restservice.dao;

import org.example.restservice.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDaoTest {

    private UserDao userDao;
    private SessionFactory sessionFactory;
    private Session session;

    @BeforeEach
    public void setUp() {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        userDao = new UserDao();
        userDao.setSessionFactory(sessionFactory); // Предположим, что вы добавили метод setSessionFactory в UserDao
        when(sessionFactory.openSession()).thenReturn(session);
    }

    @AfterEach
    public void tearDown() {
        userDao = null;
        sessionFactory = null;
        session = null;
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1);
        user1.setUserName("testUser 1");
        user1.setEmail("test1@example.com");

        User user2 = new User();
        user2.setId(2);
        user2.setUserName("testUser 2");
        user2.setEmail("test2@example.com");

        when(session.createQuery("FROM User", User.class)).thenReturn(mock(org.hibernate.query.Query.class));
        when(session.createQuery("FROM User", User.class).list()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userDao.getAllUsers();
        assertEquals(2, users.size());
        assertEquals("testUser 1", users.get(0).getUserName());
        assertEquals("testUser 2", users.get(1).getUserName());
    }

    @Test
    public void testCreateUser () {
        User user = new User();
        user.setUserName("testUser");
        user.setEmail("test@example.com");

        Transaction transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);

        userDao.createUser (user);

        verify(session).save(user);
        verify(transaction).commit();
    }

    @Test
    public void testDeleteUser () {
        User user = new User();
        user.setId(1);
        when(session.get(User.class, 1)).thenReturn(user);
        Transaction transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);

        userDao.deleteUser (1);

        verify(session).delete(user);
        verify(transaction).commit();
    }
}