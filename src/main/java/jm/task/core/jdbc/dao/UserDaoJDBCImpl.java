package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnectionWithJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS users (" +
                             "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                             "name VARCHAR(80), " +
                             "lastname VARCHAR(100), " +
                             "age TINYINT);")) {

            preparedStatement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnectionWithJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DROP TABLE IF EXISTS users;")) {

            preparedStatement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnectionWithJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users(name, lastname, age) VALUES (?, ?, ?);")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnectionWithJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM users WHERE id = ?;")) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.getConnectionWithJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM users;")) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastname");
                byte age = rs.getByte("age");

                userList.add(new User(id, name, lastName, age));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnectionWithJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "TRUNCATE TABLE users;")) {

            preparedStatement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
