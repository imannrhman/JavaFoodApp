package javafoodapp.controller;

import javafoodapp.database.dao.menumakanan.ImplementasiMenuMakanan;
import javafoodapp.database.dao.menumakanan.MenuMakananDAO;
import javafoodapp.database.dao.transaksi.ImplementasiTransaksi;
import javafoodapp.database.dao.transaksi.TransaksiDAO;
import javafoodapp.database.tables.MenuMakananTable;
import javafoodapp.database.tables.OrderTable;
import javafoodapp.database.tables.TransaksiTable;
import javafoodapp.model.MenuMakanan;
import javafoodapp.model.Order;
import javafoodapp.model.Transaksi;
import javafoodapp.swing.Button;
import javafoodapp.swing.ComboBoxSuggestion;
import javafoodapp.swing.MyPasswordField;
import javafoodapp.swing.MyTextField;
import javafoodapp.view.form.TransaksiForm;
import javafoodapp.view.pages.MenuMakananPage;
import javafoodapp.view.pages.TransaksiPage;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaksiController {


    private TransaksiPage transaksiPage;
    private TransaksiForm transaksiForm;
    private ImplementasiTransaksi implementasiTransaksi;
    private ImplementasiMenuMakanan implementasiMenuMakanan;
    private List<Transaksi> list;
    private List<MenuMakanan> menuMakananList;
    List<Order> orders = new ArrayList<Order>();

    public TransaksiController(TransaksiPage transaksiPage) {
        this.transaksiPage = transaksiPage;
        implementasiTransaksi = new TransaksiDAO();
    }

    public TransaksiController(TransaksiForm transaksiForm) {
        this.transaksiForm = transaksiForm;
        implementasiTransaksi = new TransaksiDAO();
        implementasiMenuMakanan = new MenuMakananDAO();
    }

    public void fillTable() {
        try {
            list = implementasiTransaksi.getAllMenuTransaksi();
            transaksiPage.getTable().setModel(new TransaksiTable(list));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    public void setInputTable() {
        orders = new ArrayList<Order>();
        transaksiForm.getTableInput().setModel(new OrderTable(orders));
    }


    public void tambahTransaksi() {
        try {
            JComboBox comboBox = new JComboBox();
            menuMakananList = implementasiMenuMakanan.getAllMenuMakanan();
            for(MenuMakanan menu: menuMakananList) {
                comboBox.addItem(menu.getMenuMakanan());
            }

            JTextField textField = new JTextField();

            textField.setText("1");
            JButton button = new JButton();

            comboBox.setEditable(true);
            button.setText("Tambah");

            //create a JOptionPane
            Object[] options = new Object[] {};
            JOptionPane jop = new JOptionPane("Pilih menu dan masukan Qty",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null,options, null);

            //add combos to JOptionPane
            jop.add(comboBox);
            jop.add(textField);
            jop.add(button);


            //create a JDialog and add JOptionPane to it
            JDialog diag = new JDialog();
            diag.getContentPane().add(jop);
            diag.pack();
            diag.setVisible(true);

            button.addActionListener( e -> {
                diag.setVisible(false);
                String menu = String.valueOf(comboBox.getSelectedItem());
                int qty = Integer.parseInt(textField.getText());
                Order order = new Order();
                order.setNamaBarang(menu);
                order.setQty(qty);
                orders.add(order);


                transaksiForm.getTableInput().setModel(new OrderTable(orders));

            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
