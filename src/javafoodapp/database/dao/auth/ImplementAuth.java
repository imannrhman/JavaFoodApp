package javafoodapp.database.dao.auth;

import javafoodapp.model.User;

import java.util.List;

public interface ImplementAuth {
    public void register(String username, String fullName, String password, String phone, int roleId);
    public User login(String username, String password);
}
