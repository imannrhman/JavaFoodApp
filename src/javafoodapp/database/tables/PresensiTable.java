package javafoodapp.database.tables;

import javafoodapp.model.BahanBaku;
import javafoodapp.model.Presensi;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class PresensiTable extends AbstractTableModel {
    List<Presensi> list ;

    public PresensiTable(List<Presensi> list) {
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (columnIndex) {
            case 0 : return rowIndex + 1;
            case 1 : return simpleDateFormat.format(list.get(rowIndex).getTanggal());
            case 2 : return list.get(rowIndex).getStatus();
            default:return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "NO";
            case 1 : return "TANGGAL";
            case 2 : return "STATUS";
            default:return null;
        }
    }
}
