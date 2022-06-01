package javafoodapp.database;

import javafoodapp.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDatabase {

    private static Connection connection;

    public static Connection getConnection() {

        if (connection == null) {

            try {
                Class.forName("com.mysql.jdbc.Driver");

                connection = DriverManager.getConnection("jdbc:" + Config.host + ":" + Config.port + "/" + Config.database + "", Config.username, Config.password);
                System.out.println("=============> KONEKSI BERHASIL");
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return connection;
    }

    public static void main(String[] args) {
        ConnectionDatabase.getConnection();
    }
}
