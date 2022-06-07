package javafoodapp.database.tables;

import javafoodapp.model.MenuMakanan;
import javafoodapp.model.Order;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class OrderTable extends AbstractTableModel {


    List<Order> list;

    public OrderTable(List<Order> list) {
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
        switch (columnIndex) {
            case 0 : return rowIndex + 1;
            case 1 : return list.get(rowIndex).getNamaBarang();
            case 2 : return list.get(rowIndex).getQty();
            default:return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "NO";
            case 1:
                return "NAMA BARANG";
            case 2:
                return "QTY";
            default:
                return null;
        }
    }
}
