package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Иван", "Петров", (byte) 19);
        service.saveUser("Петр", "Иванов", (byte) 21);
        service.saveUser("Елена", "Петрова", (byte) 23);
        service.saveUser("Михаил", "Петров", (byte) 27);
        service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
