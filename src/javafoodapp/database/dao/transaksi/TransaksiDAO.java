package javafoodapp.database.dao.transaksi;

import javafoodapp.database.ConnectionDatabase;
import javafoodapp.database.dao.bahanbaku.BahanBakuDAO;
import javafoodapp.model.MenuMakanan;
import javafoodapp.model.Transaksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransaksiDAO implements ImplementasiTransaksi{
    @Override
    public List<Transaksi> searchTransaksi(String nama) {
        return null;
    }

    @Override
    public List<Transaksi> getAllMenuTransaksi() throws SQLException {
        try {
            List<Transaksi> list = new ArrayList<Transaksi>();
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM transaksi");

            while (result.next()) {
                Transaksi menuMakanan = new Transaksi();
                menuMakanan.setId(result.getInt("id"));
                menuMakanan.setKodeTransaksi(result.getString("kode_transaksi"));
                menuMakanan.setTanggal(result.getDate("tanggal"));
                menuMakanan.setStatus(result.getString("status"));
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
    public void insert(Transaksi bahanBaku) {

    }

    @Override
    public void update(Transaksi bahanBaku) {

    }

    @Override
    public void delete(int id) {

    }
}
