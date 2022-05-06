package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = null;


        try {
            connection = Util.getMySQLConnection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT," +
                    "firstName VARCHAR(255),lastName VARCHAR(255), age INT, PRIMARY KEY (id))");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void dropUsersTable() throws ClassNotFoundException {
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            Statement statement = connection.createStatement();
            String execute = "DROP TABLE users";
            statement.execute(execute);
        } catch (SQLException ignore) {

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void saveUser(String firstName, String lastName, byte age) throws SQLException, ClassNotFoundException {

        Connection connection = Util.getMySQLConnection();
        Statement statement = connection.createStatement();
        String execute = "INSERT INTO users(firstName,lastName,age) VALUES ("
                + "\'" + firstName + "\',\'" + lastName + "\',\'" + age + "\')";
        statement.execute(execute);

        connection.close();
    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        Connection connection = Util.getMySQLConnection();
        Statement statement = connection.createStatement();
        String execute = "DELETE FROM users WHERE id = \'" + id + "\';";
        statement.execute(execute);

        connection.close();
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {

        List<User> userList = new ArrayList<>();

        Connection connection = Util.getMySQLConnection();
        Statement statement = connection.createStatement();
        String execute = "SELECT * FROM users";
        ResultSet rs = statement.executeQuery(execute);
        for (int i = 0; rs.next(); i++) {
            long id = rs.getInt(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            byte age = (byte) rs.getInt(4);
            userList.add(new User(firstName, lastName, age));
            userList.get(i).setId(id);
        }
        connection.close();
        return userList;

    }


    public void cleanUsersTable() throws SQLException, ClassNotFoundException {
        Connection connection = Util.getMySQLConnection();
        Statement statement = connection.createStatement();
        String execute = "DELETE FROM users";
        statement.execute(execute);

        connection.close();
    }
}
