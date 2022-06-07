package javafoodapp.database.tables;

import javafoodapp.model.BahanBaku;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BahanBakuTable extends AbstractTableModel {

    List<BahanBaku> list ;

    public BahanBakuTable(List<BahanBaku> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getKodeBarang();
            case 1 : return list.get(rowIndex).getNamaBahan();
            case 2 : return list.get(rowIndex).getKategoriBarang();
            case 3 : return list.get(rowIndex).getHarga();
            case 4 : return list.get(rowIndex).getStok();
            default:return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "KODE BAHAN";
            case 1 : return "BAHAN BAKU";
            case 2 : return "KATEGORI";
            case 3 : return "HARGA";
            case 4 : return "STOK";
            default:return null;
        }
    }
}
