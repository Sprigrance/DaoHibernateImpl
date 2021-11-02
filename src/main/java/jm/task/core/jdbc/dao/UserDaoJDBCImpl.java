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
                System.out.println("Executed rollback to savepoint...");

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                System.out.println("PreparedStatement closed successfully");

            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, мы там всё уронили");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, всё конкретно вообще");
            }
        }
//        try (Connection connection = Util.getConnectionWithJDBC();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "CREATE TABLE IF NOT EXISTS users (" +
//                             "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
//                             "name VARCHAR(80), " +
//                             "lastname VARCHAR(100), " +
//                             "age TINYINT);")) {
//
//            Savepoint savepoint1 = connection.setSavepoint();
//            try {
//                preparedStatement.execute();
//                connection.commit();
//            } catch (SQLException execution) {
//                System.out.println("SQLException during execution \"createUsersTable()\".\n" +
//                        "Executing rollback to savepoint1...");
//                connection.rollback(savepoint1);
//            }
//        } catch (SQLException exception) {
//            System.out.println("SQLException from \"createUsersTable()\" method.");
//        }
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
                System.out.println("Executed rollback to savepoint...");

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                System.out.println("PreparedStatement closed successfully");

            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, мы там всё уронили");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, всё конкретно вообще");
            }
        }
//            try (Connection connection = Util.getConnectionWithJDBC();
//                 PreparedStatement preparedStatement = connection.prepareStatement(
//                         "DROP TABLE IF EXISTS users;")) {
//
//                Savepoint savepoint2 = connection.setSavepoint();
//                try {
//                    preparedStatement.execute();
//                    connection.commit();
//                } catch (SQLException execution) {
//                    System.out.println("SQLException during execution \"dropUsersTable()\".\n" +
//                            "Executing rollback to savepoint2...");
//                    connection.rollback(savepoint2);
//                }
//
//            } catch (SQLException exception) {
//                System.out.println("SQLException during \"dropUsersTable()\" method");
//            }
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
                System.out.println("Executed rollback to savepoint...");

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                System.out.println("PreparedStatement closed successfully");

            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, мы там всё уронили");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, всё конкретно вообще");
            }
        }
//            try (Connection connection = Util.getConnectionWithJDBC();
//                 PreparedStatement preparedStatement = connection.prepareStatement(
//                         "INSERT INTO users(name, lastname, age) VALUES (?, ?, ?);")) {
//
//                Savepoint savepoint3 = connection.setSavepoint();
//                try {
//                    preparedStatement.setString(1, name);
//                    preparedStatement.setString(2, lastName);
//                    preparedStatement.setInt(3, age);
//                    preparedStatement.executeUpdate();
//                    connection.commit();
//                    System.out.println("User с именем – " + name + " добавлен в базу данных");
//                } catch (SQLException execution) {
//                    System.out.println("SQLException during execution \"saveUser()\".\n" +
//                            "Executing rollback to savepoint3...");
//                    connection.rollback(savepoint3);
//                }
//
//            } catch (SQLException exception) {
//                exception.printStackTrace();
//            }
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
                System.out.println("Executed rollback to savepoint...");

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                System.out.println("PreparedStatement closed successfully");

            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, мы там всё уронили");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, всё конкретно вообще");
            }
        }
//        try (Connection connection = Util.getConnectionWithJDBC();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "DELETE FROM users WHERE id = ?;")) {
//
//            Savepoint savepoint4 = connection.setSavepoint();
//            try {
//                preparedStatement.setLong(1, id);
//                preparedStatement.executeUpdate();
//                connection.commit();
//            } catch (SQLException execution) {
//                System.out.println("SQLException during execution \"removeUserById()\".\n" +
//                        "Executing rollback to savepoint4...");
//                connection.rollback(savepoint4);
//            }
//
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
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
            for (User user:userList) {
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
                System.out.println("Executed rollback to savepoint...");

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                System.out.println("PreparedStatement closed successfully");

                if (resultSet != null) {
                    resultSet.close();
                }

            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, мы там всё уронили");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, всё конкретно вообще");
            }
        }
        return userList;
        //        List<User> userList = new ArrayList<>();
//
//        try (Connection connection = Util.getConnectionWithJDBC();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "SELECT * FROM users;")) {
//
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                long id = rs.getLong("id");
//                String name = rs.getString("name");
//                String lastName = rs.getString("lastname");
//                byte age = rs.getByte("age");
//
//                userList.add(new User(id, name, lastName, age));
//            }
//
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//        return userList;
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
                System.out.println("Executed rollback to savepoint...");

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                System.out.println("PreparedStatement closed successfully");

            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, мы там всё уронили");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Наташ, вставай, всё конкретно вообще");
            }
        }
//        try (Connection connection = Util.getConnectionWithJDBC();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "TRUNCATE TABLE users;")) {
//
//            Savepoint savepoint5 = connection.setSavepoint();
//            try {
//                preparedStatement.execute();
//                connection.commit();
//            } catch (SQLException execution) {
//                System.out.println("SQLException during execution \"cleanUsersTable()\".\n" +
//                        "Executing rollback to savepoint5...");
//                connection.rollback(savepoint5);
//            }
//
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
    }
}

