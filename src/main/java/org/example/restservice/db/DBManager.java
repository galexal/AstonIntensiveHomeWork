package org.example.restservice.db;

import org.example.restservice.model.Post;
import org.example.restservice.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DBManager {

    private static Statement statement;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/users_posts" +
                    "?user=testDbUser&password=testDbUserPassword");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT id, username, email FROM users");
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setUserName(result.getString("username"));
                user.setEmail(result.getString("email"));

                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User getUserById(String id) {
        User user = new User();
        try {
            ResultSet result = statement.executeQuery(String.format("SELECT id, username, email FROM users WHERE id='%s';", id));
            if (result.next()) {
                user.setId(result.getInt("id"));
                user.setUserName(result.getString("username"));
                user.setEmail(result.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<Post> getPostsByUserId(String userId) {
        User user = getUserById(userId);
        List<Post> posts = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT id, title, content FROM posts " +
                    "WHERE user_id = '%s';", userId));
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                post.setUser(user);
                posts.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static void modifyUser(String id, String userName, String email) {
        try {
            statement.execute(String.format(
                    "UPDATE users SET username = '%s', email = '%s' WHERE id = %s;",
                    userName, email, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createUser(String userName, String email) {
        try {
            statement.execute(String.format(
                    "INSERT INTO users (username, email) VALUES ('%s', '%s');",
                    userName, email));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUsers(String[] s) {
        String ids = Arrays.stream(s).map(str -> "'" + str + "'").collect(Collectors.joining(", "));
        try {
            statement.execute(String.format("DELETE FROM users WHERE id IN (%s);",
                    ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
