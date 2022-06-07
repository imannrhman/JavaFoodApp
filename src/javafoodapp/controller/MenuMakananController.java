package javafoodapp.controller;

import javafoodapp.database.dao.bahanbaku.BahanBakuDAO;
import javafoodapp.database.dao.bahanbaku.ImplementBahanBaku;
import javafoodapp.database.dao.menumakanan.ImplementasiMenuMakanan;
import javafoodapp.database.dao.menumakanan.MenuMakananDAO;
import javafoodapp.database.tables.BahanBakuTable;
import javafoodapp.database.tables.MenuMakananTable;
import javafoodapp.model.BahanBaku;
import javafoodapp.model.MenuMakanan;
import javafoodapp.view.form.BahanBakuForm;
import javafoodapp.view.form.MenuMakanForm;
import javafoodapp.view.pages.BahanBakuPage;
import javafoodapp.view.pages.MenuMakananPage;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class MenuMakananController {

    private MenuMakananPage menuMakananPage;
    private MenuMakanForm menuMakanForm;


    private ImplementasiMenuMakanan implementasiMenuMakanan;
    private ImplementBahanBaku implementBahanBaku;

    private List<MenuMakanan> list;

    public MenuMakananController(MenuMakananPage bahanBakuPage) {
        this.menuMakananPage = bahanBakuPage;
        implementasiMenuMakanan = new MenuMakananDAO();
    }

    public MenuMakananController(MenuMakanForm menuMakanForm) {
        this.menuMakanForm = menuMakanForm;
        implementasiMenuMakanan = new MenuMakananDAO();
        implementBahanBaku = new BahanBakuDAO();
    }

    public void getBahan() {
        try {
            List<BahanBaku> bahanBaku = implementBahanBaku.getAllBahanBaku();

            String[] item = new String[bahanBaku.size()];
            int i = 0;
            for (BahanBaku bahan : bahanBaku) {
                item[i] = bahan.getNamaBahan();
                i++;
            }
            menuMakanForm.getListBahan().setListData(item);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void fillTable() {
        try {
            list = implementasiMenuMakanan.getAllMenuMakanan();
            menuMakananPage.getTable().setModel(new MenuMakananTable(list));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void insertData() {
        try {
            String menu = menuMakanForm.getMenuField().getText();
            String harga = menuMakanForm.getHargaField().getText();
            List<String> data = menuMakanForm.getListBahan().getSelectedValuesList();
            if (menu != null && harga != null) {

                MenuMakanan last = implementasiMenuMakanan.getLastMenu();
                MenuMakanan menuMakanan = new MenuMakanan();

                String kodeMenu = last.getKodeMenu().substring(2);
                String AN = "" + (Integer.parseInt(kodeMenu) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "0";
                } else if (AN.length() == 4) {
                    Nol = "";
                }

                kodeMenu = "MN" + Nol + AN;

                menuMakanan.setMenuMakanan(menu);
                menuMakanan.setHarga(Double.parseDouble(harga));
                menuMakanan.setKodeMenu(kodeMenu);
                implementasiMenuMakanan.insert(menuMakanan);
                last = implementasiMenuMakanan.getLastMenu();

                for (String d: data) {
                    implementasiMenuMakanan.insertRelation(last.getId(), d);
                }


                JPanel mainPanel = menuMakanForm.getMainPanel();
                mainPanel.removeAll();
                mainPanel.add(new MenuMakananPage(mainPanel));
                mainPanel.repaint();
                mainPanel.revalidate();


            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateData() {
        try {
            int row = menuMakananPage.getTable().getSelectedRow();
            String kodeMenu = String.valueOf(menuMakananPage.getTable().getValueAt(row, 1));
            String namaMakanan = String.valueOf(menuMakananPage.getTable().getValueAt(row, 2));
            String harga = String.valueOf(menuMakananPage.getTable().getValueAt(row, 3));


            MenuMakanan menuMakanan = new MenuMakanan();
            menuMakanan.setMenuMakanan(namaMakanan);
            menuMakanan.setHarga(Double.parseDouble(harga));

            JPanel mainPanel = menuMakananPage.getMainPanel();
            mainPanel.removeAll();
            mainPanel.add(new MenuMakanForm(mainPanel, menuMakanan));
            mainPanel.repaint();
            mainPanel.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(menuMakananPage, "Pilih item ! ",null, JOptionPane.ERROR_MESSAGE);
        }

    }

    public void deleteData() {
        try {
            int row = menuMakananPage.getTable().getSelectedRow();
            String kodeMenu = String.valueOf(menuMakananPage.getTable().getValueAt(row, 1));
            implementasiMenuMakanan.delete(kodeMenu);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void checkForm(MenuMakanan menuMakanan) {
        if (menuMakanan != null) {
            menuMakanForm.getMenuField().setText(menuMakanan.getMenuMakanan());
            menuMakanForm.getHargaField().setText(String.valueOf(menuMakanan.getHarga()));
        }

    }

    public void editData(String kodeMenu) {
        try {
            String menuMakan = menuMakanForm.getMenuField().getText();
            String harga = menuMakanForm.getHargaField().getText();

            if (menuMakan != null && harga != null) {
                MenuMakanan menuMakanan = new MenuMakanan();

                menuMakanan.setMenuMakanan(menuMakan);
                menuMakanan.setHarga(Double.parseDouble(harga));

                implementasiMenuMakanan.update(kodeMenu, menuMakanan);

                JPanel mainPanel = menuMakanForm.getMainPanel();
                mainPanel.removeAll();
                mainPanel.add(new BahanBakuPage(mainPanel));
                mainPanel.repaint();
                mainPanel.revalidate();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
