package dao;

import connection.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImp implements UserDAO{
    private String tableName;

    public UserDAOImp(String tableName){
        this.tableName = "user";
    }

    public UserDAOImp() {
        this.tableName = "user";
    }

    @Override
    public void createUser(User user) throws SQLException, ClassNotFoundException {
        if(user==null) return;
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO " + tableName + "(user, password) VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, user.getUser());
        ps.setString(2, user.getPassword());
        ps.execute();
    }

    @Override
    public User readUser(int id) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "SELECT user, password FROM " + tableName + " WHERE userid = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        if (rs.next()) {
            user.setUser(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setId(id);
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws SQLException, ClassNotFoundException {
        if(user==null) return;
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "UPDATE "+tableName+" SET user = ? WHERE userid = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, user.getUser());
        ps.setInt(2, user.getId());
        ps.executeUpdate();
    }

    @Override
    public void deleteUser(User user) throws SQLException, ClassNotFoundException {
        if(user==null) return;
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "DELETE FROM "+ tableName +" WHERE userid = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, user.getId());
        ps.executeUpdate();
    }
}
