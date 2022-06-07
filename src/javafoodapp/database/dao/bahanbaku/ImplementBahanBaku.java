package javafoodapp.database.dao.bahanbaku;

import javafoodapp.model.BahanBaku;
import javafoodapp.view.pages.BahanBakuPage;

import java.sql.SQLException;
import java.util.List;

public interface ImplementBahanBaku {

    public List<BahanBaku> searchBahanBaku(String nama);

    public List<BahanBaku> getAllBahanBaku() throws SQLException;

    public BahanBaku lastBahanBaku() throws SQLException;

    public void insert(BahanBaku bahanBaku) throws SQLException;

    public void update(String kodeBahan ,BahanBaku bahanBaku) throws SQLException;

    public void delete(String kodeBarang) throws SQLException;

}
