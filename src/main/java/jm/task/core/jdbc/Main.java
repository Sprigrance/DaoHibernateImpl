package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Ivan", "Petrov", (byte) 19);
        service.saveUser("Vladimir", "Ivanov", (byte) 21);
        service.saveUser("Elena", "Petrova", (byte) 23);
        service.saveUser("Michail", "Sidorov", (byte) 27);
        service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
