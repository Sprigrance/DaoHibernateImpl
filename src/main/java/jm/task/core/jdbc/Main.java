package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
w
public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Ivan", "Petrov", (byte) 19);
        service.saveUser("Vladimir", "Ivanov", (byte) 21);
        service.saveUser("Elena", "Petrova", (byte) 23);
        service.saveUser("Michail", "Sidorov", (byte) 27);
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
