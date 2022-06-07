package javafoodapp.database.dao.transaksi;

import javafoodapp.model.Transaksi;

import java.sql.SQLException;
import java.util.List;

public interface ImplementasiTransaksi {

    public List<Transaksi> searchTransaksi(String nama);

    public List<Transaksi> getAllMenuTransaksi() throws SQLException;

    public void insert(Transaksi bahanBaku);

    public void update(Transaksi bahanBaku);

    public void delete(int id);
}
