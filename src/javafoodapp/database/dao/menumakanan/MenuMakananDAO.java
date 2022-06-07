package javafoodapp.database.dao.menumakanan;

import javafoodapp.database.ConnectionDatabase;
import javafoodapp.database.dao.bahanbaku.BahanBakuDAO;
import javafoodapp.database.dao.presensi.PresensiDAO;
import javafoodapp.model.BahanBaku;
import javafoodapp.model.MenuMakanan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuMakananDAO implements ImplementasiMenuMakanan{

    @Override
    public List<MenuMakanan> searchMenuMaknan(String nama) {
        return null;
    }

    @Override
    public List<MenuMakanan> getAllMenuMakanan() throws SQLException {
        try {
            List<MenuMakanan> list = new ArrayList<MenuMakanan>();
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM menu_makanan");

            while (result.next()) {
                MenuMakanan menuMakanan = new MenuMakanan();
                menuMakanan.setId(result.getInt("id"));
                menuMakanan.setKodeMenu(result.getString("kode_menu"));
                menuMakanan.setMenuMakanan(result.getString("menu_makanan"));
                menuMakanan.setHarga(result.getDouble("harga"));
                list.add(menuMakanan);
            }

            statement.close();
            result.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BahanBakuDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public MenuMakanan getLastMenu() throws SQLException {
        try {
            List<MenuMakanan> list = new ArrayList<MenuMakanan>();
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM menu_makanan order by id desc ");

            while (result.next()) {
                MenuMakanan menuMakanan = new MenuMakanan();
                menuMakanan.setId(result.getInt("id"));
                menuMakanan.setKodeMenu(result.getString("kode_menu"));
                menuMakanan.setHarga(result.getDouble("harga"));
                list.add(menuMakanan);
            }

            statement.close();
            result.close();
            return list.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(BahanBakuDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void insert(MenuMakanan menuMakanan) throws SQLException {
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "insert into menu_makanan ( kode_menu, menu_makanan, harga)  values ( ? , ? , ? )");

            statement.setString(1, menuMakanan.getKodeMenu());
            statement.setString(2, menuMakanan.getMenuMakanan());
            statement.setDouble(3, menuMakanan.getHarga());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PresensiDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void insertRelation(int idMenu, String namaMakanan) throws SQLException {
        try {

            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "call inputMenuBahan(?,?)");

            statement.setInt(1, idMenu);
            statement.setString(2, namaMakanan);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PresensiDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void update(String kodeMenu,MenuMakanan menuMakanan) throws SQLException {
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "UPDATE menu_makanan t " +
                    "SET t.menu_makanan = ?," +
                    "    t.harga        = ?" +
                    "WHERE t.kode_menu = '" + kodeMenu +"'" );
            statement.setString(1, menuMakanan.getMenuMakanan());
            statement.setDouble(2, menuMakanan.getHarga());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PresensiDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void delete(String kodeMenu) throws SQLException {
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "DELETE FROM menu_makanan WHERE kode_menu = ?");
            statement.setString(1, kodeMenu);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PresensiDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}
