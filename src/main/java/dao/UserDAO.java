package dao;

import model.User;

import java.sql.SQLException;


public interface UserDAO {
    public void createUser(User user) throws SQLException, ClassNotFoundException;
    public User readUser(int id) throws SQLException, ClassNotFoundException;
    public void updateUser(User user) throws SQLException, ClassNotFoundException;
    public void deleteUser(User user) throws SQLException, ClassNotFoundException;
}
