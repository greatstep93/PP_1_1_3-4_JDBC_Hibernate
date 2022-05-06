package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() throws SQLException, ClassNotFoundException {
        UserDao ud = new UserDaoJDBCImpl();
        ud.createUsersTable();
    }

    public void dropUsersTable() throws ClassNotFoundException {
        UserDao ud = new UserDaoJDBCImpl();
        ud.dropUsersTable();

    }

    public void saveUser(String firstName, String lastName, byte age) throws SQLException, ClassNotFoundException {

        UserDao ud = new UserDaoJDBCImpl();
        ud.saveUser(firstName, lastName, age);
    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        UserDao ud = new UserDaoJDBCImpl();
        ud.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {

        UserDao ud = new UserDaoJDBCImpl();
        return ud.getAllUsers();

    }

    public void cleanUsersTable() throws SQLException, ClassNotFoundException {
        UserDao ud = new UserDaoJDBCImpl();
        ud.cleanUsersTable();
    }
}
