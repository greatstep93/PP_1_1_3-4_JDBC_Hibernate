package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("Дмитрий", "Ткаченко", (byte) 29);
        userService.saveUser("Анна", "Тришкина", (byte) 29);
        userService.saveUser("Михаил", "Баранов", (byte) 29);
        userService.saveUser("Андрей", "Богданов", (byte) 34);

        System.out.println(userService.getAllUsers().toString()
                .replaceAll("^\\[|]$", "")
                .replaceAll(",", ""));

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
