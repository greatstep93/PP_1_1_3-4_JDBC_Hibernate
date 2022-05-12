package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT,"
                    + "firstName VARCHAR(255),lastName VARCHAR(255), age INT, PRIMARY KEY (id));").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка создания таблицы");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ignore) {

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();
            session.persist(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("User с именем " + name + " " + lastName + " сохранен в базу данных");
        } catch (Exception e) {
            System.out.println("Ошибка сохранения");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();

            session.createQuery("DELETE FROM User WHERE id = " + id).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления пользователя по id");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();

            userList = session.createQuery("SELECT a FROM User a", User.class).getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Ошибка получения списка пользователей");
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();

            session.createQuery("DELETE FROM User").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {

            System.out.println("Ошибка очистки таблицы");
        }
    }
}