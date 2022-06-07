package javafoodapp.database.dao.menumakanan;

import javafoodapp.model.BahanBaku;
import javafoodapp.model.MenuMakanan;

import java.sql.SQLException;
import java.util.List;

public interface ImplementasiMenuMakanan {

    List<MenuMakanan> searchMenuMaknan(String nama);

    List<MenuMakanan> getAllMenuMakanan() throws SQLException;

    MenuMakanan getLastMenu() throws SQLException;

    void insert(MenuMakanan menuMakanan) throws SQLException;

    void insertRelation(int idMenu, String namaMakanan) throws  SQLException;

    void update(String kodeMenu,MenuMakanan menuMakanan) throws SQLException;

    void delete(String kodeMenu) throws SQLException;
}
