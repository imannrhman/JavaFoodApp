package javafoodapp.database.tables;

import javafoodapp.model.MenuMakanan;
import javafoodapp.model.Transaksi;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class TransaksiTable  extends AbstractTableModel {

    List<Transaksi> list ;

    public TransaksiTable(List<Transaksi> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getKodeTransaksi();
            case 1 : return format.format(list.get(rowIndex).getTanggal());
            case 2 : return list.get(rowIndex).getStatus();
            default:return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "KODE";
            case 1:
                return "Tanggal";
            case 2:
                return "Status";
            default:
                return null;
        }
    }
}
