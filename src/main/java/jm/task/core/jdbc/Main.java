package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserServiceImpl userService = new UserServiceImpl();

        userService.dropUsersTable();

        userService.createUsersTable();

        userService.saveUser("Дмитрий", "Ткаченко", (byte) 29);
        System.out.println("Пользователь \"" + userService.getAllUsers().get(0).getName() + "\" добавлен в базу данных");

        userService.saveUser("Анна", "Тришкина", (byte) 29);
        System.out.println("Пользователь \"" + userService.getAllUsers().get(1).getName() + "\" добавлен в базу данных");

        userService.saveUser("Михаил", "Баранов", (byte) 29);
        System.out.println("Пользователь \"" + userService.getAllUsers().get(2).getName() + "\" добавлен в базу данных");

        userService.saveUser("Андрей", "Богданов", (byte) 34);
        System.out.println("Пользователь \"" + userService.getAllUsers().get(3).getName() + "\" добавлен в базу данных");


        System.out.println(userService.getAllUsers().toString()
                                                    .replaceAll("^\\[|\\]$", "")
                                                    .replaceAll(",", ""));

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
