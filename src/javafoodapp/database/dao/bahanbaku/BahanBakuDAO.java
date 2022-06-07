package javafoodapp.database.dao.bahanbaku;

import javafoodapp.database.ConnectionDatabase;
import javafoodapp.database.dao.presensi.PresensiDAO;
import javafoodapp.model.BahanBaku;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BahanBakuDAO implements ImplementBahanBaku{

    @Override
    public List<BahanBaku> searchBahanBaku(String nama) {
        return null;
    }

    @Override
    public List<BahanBaku> getAllBahanBaku() throws SQLException {

        try {
            List<BahanBaku> list = new ArrayList<BahanBaku>();
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM bahan_baku");

            while (result.next()) {
                BahanBaku bahanBaku = new BahanBaku();

                bahanBaku.setId(result.getInt("id"));
                bahanBaku.setKodeBarang(result.getString("kode_bahan"));
                bahanBaku.setNamaBahan(result.getString("nama_bahan"));
                bahanBaku.setHarga(result.getDouble("harga"));
                bahanBaku.setKategoriBarang(result.getString("kategori_barang"));
                bahanBaku.setStok(result.getInt("stok"));
                list.add(bahanBaku);
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
    public BahanBaku lastBahanBaku() throws SQLException {
        try {
            List<BahanBaku> list = new ArrayList<BahanBaku>();
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM bahan_baku order by id desc ");

            while (result.next()) {
                BahanBaku bahanBaku = new BahanBaku();
                bahanBaku.setId(result.getInt("id"));
                bahanBaku.setKodeBarang(result.getString("kode_bahan"));
                bahanBaku.setNamaBahan(result.getString("nama_bahan"));
                bahanBaku.setHarga(result.getDouble("harga"));
                bahanBaku.setKategoriBarang(result.getString("kategori_barang"));
                bahanBaku.setStok(result.getInt("stok"));
                list.add(bahanBaku);
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
    public void insert(BahanBaku bahanBaku) throws SQLException {
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "insert into bahan_baku ( kode_bahan, nama_bahan, harga, kategori_barang, stok)  values ( ? , ? , ?, ? ,?  )");


            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            statement.setString(1, bahanBaku.getKodeBarang());
            statement.setString(2, bahanBaku.getNamaBahan());
            statement.setDouble(3, bahanBaku.getHarga());
            statement.setString(4, bahanBaku.getKategoriBarang());
            statement.setInt(5, bahanBaku.getStok());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PresensiDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void update(String kodeBahan ,BahanBaku bahanBaku) throws SQLException {
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "UPDATE bahan_baku t SET t.nama_bahan  =  ?, t.harga = ?, t.kategori_barang = ?, t.stok = ? WHERE t.kode_bahan = '" + kodeBahan + "'");
            statement.setString(1, bahanBaku.getNamaBahan());
            statement.setDouble(2, bahanBaku.getHarga());
            statement.setString(3, bahanBaku.getKategoriBarang());
            statement.setInt(4, bahanBaku.getStok());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PresensiDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void delete(String kodeBahan) throws SQLException {
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "DELETE FROM bahan_baku WHERE kode_bahan = ?");

            statement.setString(1, kodeBahan);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PresensiDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}
