package javafoodapp.database.dao.role;

import javafoodapp.database.ConnectionDatabase;
import javafoodapp.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDAO implements  ImplementRole {

    @Override
    public List<Role> getAllRole() throws SQLException {
        List<Role> list = new ArrayList<Role>();

        try {

            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM role");

            while (result.next()) {
                Role role = new Role();
                role.setId(result.getInt(1));
                role.setName(result.getString("name"));
                list.add(role);
            }
            statement.close();
            result.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}
