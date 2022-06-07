package javafoodapp.database.dao.role;

import javafoodapp.model.Role;

import java.sql.SQLException;
import java.util.List;

public interface ImplementRole {
    public List<Role> getAllRole() throws SQLException;
}
