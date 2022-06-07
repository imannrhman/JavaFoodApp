package javafoodapp.controller;

import javafoodapp.database.dao.bahanbaku.BahanBakuDAO;
import javafoodapp.database.dao.bahanbaku.ImplementBahanBaku;
import javafoodapp.database.tables.BahanBakuTable;
import javafoodapp.model.BahanBaku;
import javafoodapp.view.form.BahanBakuForm;
import javafoodapp.view.pages.BahanBakuPage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class BahanBakuController {

    private BahanBakuPage bahanBakuPage;
    private BahanBakuForm bahanBakuForm;


    private final ImplementBahanBaku implementBahanBaku;
    private List<BahanBaku> list;

    public BahanBakuController(BahanBakuPage bahanBakuPage) {
        this.bahanBakuPage = bahanBakuPage;
        this.implementBahanBaku = new BahanBakuDAO();
    }

    public BahanBakuController(BahanBakuForm bahanBakuForm) {
        this.bahanBakuForm = bahanBakuForm;
        this.implementBahanBaku = new BahanBakuDAO();
    }


    public void fillTable() {
        try {
            list = implementBahanBaku.getAllBahanBaku();
            bahanBakuPage.getTable().setModel(new BahanBakuTable(list));

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }


    public void inserData() {
        try {
            String bahanBaku = bahanBakuForm.getBahanField().getText();
            String kategori = bahanBakuForm.getKategoriField().getText();
            String stok = bahanBakuForm.getStokField().getText();
            String harga = bahanBakuForm.getHargaField().getText();

            if (bahanBaku != null && kategori != null && stok != null && harga != null) {
                BahanBaku last = implementBahanBaku.lastBahanBaku();
                BahanBaku bahan = new BahanBaku();

                String kodeBarang = last.getKodeBarang().substring(2);
                String AN = "" + (Integer.parseInt(kodeBarang) + 1);
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

                kodeBarang = "BR" + Nol + AN;
                bahan.setNamaBahan(bahanBaku);
                bahan.setKodeBarang(kodeBarang);
                bahan.setKategoriBarang(kategori);
                bahan.setHarga(Double.parseDouble(harga));
                bahan.setStok(Integer.parseInt(stok));
                implementBahanBaku.insert(bahan);


                JPanel mainPanel = bahanBakuForm.getMainPanel();
                mainPanel.removeAll();
                mainPanel.add(new BahanBakuPage(mainPanel));
                mainPanel.repaint();
                mainPanel.revalidate();

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void updateData() {
        try {
            int row = bahanBakuPage.getTable().getSelectedRow();
            String kodeBarang = String.valueOf(bahanBakuPage.getTable().getValueAt(row, 0));
            String namaBahan = String.valueOf(bahanBakuPage.getTable().getValueAt(row, 1));
            String kategori = String.valueOf(bahanBakuPage.getTable().getValueAt(row, 2));
            String harga = String.valueOf(bahanBakuPage.getTable().getValueAt(row, 3));
            String stok = String.valueOf(bahanBakuPage.getTable().getValueAt(row, 4));


            BahanBaku bahanBaku = new BahanBaku();


            bahanBaku.setKodeBarang(kodeBarang);
            bahanBaku.setNamaBahan(namaBahan);
            bahanBaku.setKategoriBarang(kategori);
            bahanBaku.setHarga(Double.parseDouble(harga));
            bahanBaku.setStok(Integer.parseInt(stok));



            JPanel mainPanel = bahanBakuPage.getMainPanel();
            mainPanel.removeAll();
            mainPanel.add(new BahanBakuForm(mainPanel, bahanBaku));
            mainPanel.repaint();
            mainPanel.revalidate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(bahanBakuPage, "Pilih item ! ",null, JOptionPane.ERROR_MESSAGE);
        }

    }

    public void editData(String kodeBahan) {
        try {
            String bahanBaku = bahanBakuForm.getBahanField().getText();
            String kategori = bahanBakuForm.getKategoriField().getText();
            String stok = bahanBakuForm.getStokField().getText();
            String harga = bahanBakuForm.getHargaField().getText();

            if (bahanBaku != null && kategori != null && stok != null && harga != null) {
                BahanBaku bahan = new BahanBaku();
                bahan.setNamaBahan(bahanBaku);
                bahan.setKategoriBarang(kategori);
                bahan.setHarga(Double.parseDouble(harga));
                bahan.setStok(Integer.parseInt(stok));
                implementBahanBaku.update(kodeBahan,bahan);

                JPanel mainPanel = bahanBakuForm.getMainPanel();
                mainPanel.removeAll();
                mainPanel.add(new BahanBakuPage(mainPanel));
                mainPanel.repaint();
                mainPanel.revalidate();

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeData() {
        try {
            int row = bahanBakuPage.getTable().getSelectedRow();
            String kodeBarang = String.valueOf(bahanBakuPage.getTable().getValueAt(row, 0));
            implementBahanBaku.delete(kodeBarang);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void checkForm(BahanBaku bahanBaku) {
        if (bahanBaku != null) {
            bahanBakuForm.getBahanField().setText(bahanBaku.getNamaBahan());
            bahanBakuForm.getKategoriField().setText(bahanBaku.getKategoriBarang());
            bahanBakuForm.getHargaField().setText(String.valueOf(bahanBaku.getHarga()));
            bahanBakuForm.getStokField().setText(String.valueOf(bahanBaku.getStok()));
        }

    }
}
