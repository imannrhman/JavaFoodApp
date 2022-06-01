package javafoodapp.database.dao.auth;

import javafoodapp.database.ConnectionDatabase;
import javafoodapp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthDAO implements ImplementAuth {

    @Override
    public void register(String fullName, String username , String password, String phone, int roleId) {
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "INSERT INTO users (full_name, username, password, phone, role_id)" +
                    " VALUES ( ?, ?, ?, ?, ?)");

            statement.setString(1, fullName);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, phone);
            statement.setString(5, String.valueOf(roleId));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User login(String username, String password) {
        try {
            User user = new User();
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE username LIKE '%" + username + "%' AND password LIKE '%" + password + "%'");
            while (result.next()) {
                user.setFullName(result.getString("full_name"));
                user.setUsername(result.getString("username"));
                user.setPhone(result.getString("phone"));
                user.setPhone(result.getString("phone"));
            }
            statement.close();
            result.close();
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
