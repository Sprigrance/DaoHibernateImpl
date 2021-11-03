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
        Connection connection = Util.getConnectionWithJDBC();
        PreparedStatement preparedStatement = null;
        Savepoint savepoint = null;

        try {
            savepoint = connection.setSavepoint();
            preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                            "name VARCHAR(80), " +
                            "lastname VARCHAR(100), " +
                            "age TINYINT);");

            preparedStatement.execute();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                if (savepoint != null) {
                    connection.rollback(savepoint);
                } else {
                    connection.rollback();
                }
            } catch (SQLException exception) {
                System.out.println("Не удалось осуществить rollback");
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Не удалось закрыть соединение");
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnectionWithJDBC();
        PreparedStatement preparedStatement = null;
        Savepoint savepoint = null;

        try {
            savepoint = connection.setSavepoint();
            preparedStatement = connection.prepareStatement(
                    "DROP TABLE IF EXISTS users;");

            preparedStatement.execute();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                if (savepoint != null) {
                    connection.rollback(savepoint);
                } else {
                    connection.rollback();
                }
            } catch (SQLException exception) {
                System.out.println("Не удалось осуществить rollback");
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Не удалось закрыть соединение");
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnectionWithJDBC();
        PreparedStatement preparedStatement = null;
        Savepoint savepoint = null;

        try {
            savepoint = connection.setSavepoint();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(name, lastname, age) VALUES (?, ?, ?);");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                if (savepoint != null) {
                    connection.rollback(savepoint);
                } else {
                    connection.rollback();
                }
            } catch (SQLException exception) {
                System.out.println("Не удалось осуществить rollback");
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Не удалось закрыть соединение");
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnectionWithJDBC();
        PreparedStatement preparedStatement = null;
        Savepoint savepoint = null;

        try {
            savepoint = connection.setSavepoint();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE id = ?;");

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                if (savepoint != null) {
                    connection.rollback(savepoint);
                } else {
                    connection.rollback();
                }
            } catch (SQLException exception) {
                System.out.println("Не удалось осуществить rollback");
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Не удалось закрыть соединение");
            }
        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.getConnectionWithJDBC();
        PreparedStatement preparedStatement = null;
        Savepoint savepoint = null;

        List<User> userList = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            savepoint = connection.setSavepoint();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users;");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");

                userList.add(new User(id, name, lastName, age));
            }
            for (User user : userList) {
                System.out.println(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                if (savepoint != null) {
                    connection.rollback(savepoint);
                } else {
                    connection.rollback();
                }
            } catch (SQLException exception) {
                System.out.println("Не удалось осуществить rollback");
            }
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Не удалось закрыть соединение");
            }
        }
        return userList;
    }


    public void cleanUsersTable() {
        Connection connection = Util.getConnectionWithJDBC();
        PreparedStatement preparedStatement = null;
        Savepoint savepoint = null;

        try {
            savepoint = connection.setSavepoint();
            preparedStatement = connection.prepareStatement(
                    "TRUNCATE TABLE users;");

            preparedStatement.execute();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                if (savepoint != null) {
                    connection.rollback(savepoint);
                } else {
                    connection.rollback();
                }
            } catch (SQLException exception) {
                System.out.println("Не удалось осуществить rollback");
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Не удалось закрыть соединение");
            }
        }
    }
}

