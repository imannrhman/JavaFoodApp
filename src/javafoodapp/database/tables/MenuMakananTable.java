package javafoodapp.database.tables;

import javafoodapp.model.BahanBaku;
import javafoodapp.model.MenuMakanan;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MenuMakananTable extends AbstractTableModel {

    List<MenuMakanan> list;

    public MenuMakananTable(List<MenuMakanan> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return rowIndex + 1;
            case 1 : return list.get(rowIndex).getKodeMenu();
            case 2 : return list.get(rowIndex).getMenuMakanan();
            case 3 : return list.get(rowIndex).getHarga();
            default:return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "NO";
            case 1:
                return "KODE";
            case 2:
                return "NAMA MAKANAN";
            case 3:
                return "HARGA";
            default:
                return null;
        }
    }
}
