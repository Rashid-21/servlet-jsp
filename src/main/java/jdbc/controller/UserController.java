package jdbc.controller;

import jdbc.model.User;

import java.sql.*;

public class UserController {

    public void addUser(int id, String name, String surname, int age) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company",
                "root", "1234");

        String insertSql = "INSERT INTO student (id, name, surname, age) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertSql);

        statement.setInt(1, id);
        statement.setString(2, name);
        statement.setString(3, surname);
        statement.setInt(4, age);

        int affectedRows = statement.executeUpdate();

        System.out.println(affectedRows + " rows have been inserted.");

        statement.close();
        connection.close();
    }


    public User getUserById(Integer id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company",
                "root", "1234");

        String selectSql = "SELECT id, name, surname, age FROM student WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            int age = resultSet.getInt("age");
            user = new User(id, name, surname, age);
        } else {
            System.out.println("No user found with id " + id);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return user;
    }
}
