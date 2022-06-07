package javafoodapp.database.dao.auth;

import javafoodapp.database.ConnectionDatabase;
import javafoodapp.model.Auth;
import javafoodapp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthDAO implements ImplementAuth {

    @Override
    public void register(Auth auth) throws SQLException {
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "CALL registerUser( ? , ? , ? , ? , ? )");

            statement.setString(1, auth.getFullName());
            statement.setString(2, auth.getUsername());
            statement.setString(3, auth.getPassword());
            statement.setString(4, auth.getPhoneNumber());
            statement.setString(5, String.valueOf(auth.getRoleName()));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex ;
        }
    }

    @Override
    public User login(String username, String password) throws SQLException{
        try {
            User user = null;
            System.out.println(username);
            System.out.println(password);
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE username LIKE '%" + username + "%' AND password LIKE '%" + password + "%'");
            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setFullName(result.getString("full_name"));
                user.setUsername(result.getString("username"));
                user.setPhone(result.getString("phone"));
            }
            statement.close();
            result.close();
            return user;
        } catch (SQLException sqlException) {
            Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            throw sqlException;
        }
    }
}
