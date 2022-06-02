/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafoodapp.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDB {
    public static Connection koneksi;
    public static Connection configDB() throws SQLException{
        
        try {
            String url = "jdbc:mysql://localhost/db_restaurant";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            koneksi = DriverManager.getConnection(url,"root","");            
            //JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Koneksi Gagal! \n"+e.getMessage());
            System.err.println("Koneksi Gagal "+e.getMessage());
        }
        
        return koneksi;
    }
    
    public static void main(String[] args) throws SQLException {
          Connection C = (Connection)KoneksiDB.configDB();
        
    }
}
