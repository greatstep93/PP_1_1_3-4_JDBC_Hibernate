package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("�������", "��������", (byte) 29);
        userService.saveUser("����", "��������", (byte) 29);
        userService.saveUser("������", "�������", (byte) 29);
        userService.saveUser("������", "��������", (byte) 34);

        System.out.println(userService.getAllUsers().toString()
                .replaceAll("^\\[|]$", "")
                .replaceAll(",", ""));

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
