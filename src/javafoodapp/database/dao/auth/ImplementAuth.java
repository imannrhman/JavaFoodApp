package javafoodapp.database.dao.auth;

import javafoodapp.model.Auth;
import javafoodapp.model.User;

import java.sql.SQLException;
import java.util.List;

public interface ImplementAuth {
     void register(Auth auth) throws SQLException;
     User login(String username, String password) throws SQLException;
}
