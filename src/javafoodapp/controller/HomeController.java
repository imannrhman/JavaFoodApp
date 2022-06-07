package javafoodapp.controller;

import javafoodapp.component.CardItem;
import javafoodapp.database.dao.bahanbaku.BahanBakuDAO;
import javafoodapp.database.dao.bahanbaku.ImplementBahanBaku;
import javafoodapp.database.dao.menumakanan.ImplementasiMenuMakanan;
import javafoodapp.database.dao.menumakanan.MenuMakananDAO;
import javafoodapp.database.dao.transaksi.ImplementasiTransaksi;
import javafoodapp.database.dao.transaksi.TransaksiDAO;
import javafoodapp.view.pages.HomePage;

import javax.swing.*;
import java.sql.SQLException;

public class HomeController {
    private final HomePage homePage;
    private final ImplementBahanBaku implementBahanBaku;
    private final ImplementasiMenuMakanan implementasiMenuMakanan;
    private final ImplementasiTransaksi implementasiTransaksi;


    public HomeController(HomePage homePage) {
        this.homePage = homePage;
        implementasiMenuMakanan = new MenuMakananDAO();
        implementBahanBaku = new BahanBakuDAO();
        implementasiTransaksi = new TransaksiDAO();
    }

    public void initCount() {
        try {
            int totalBahanBaku = implementBahanBaku.getAllBahanBaku().size();
            int totalMenuMakanan = implementasiMenuMakanan.getAllMenuMakanan().size();
            int totalTransaksi = implementasiTransaksi.getAllMenuTransaksi().size();


            homePage.getCard1().setData(new CardItem(new ImageIcon(getClass().getResource("/javafoodapp/icon/stock.png")), "Total Bahan", String.valueOf(totalBahanBaku) , ""));
            homePage.getCard2().setData(new CardItem(new ImageIcon(getClass().getResource("/javafoodapp/icon/flag.png")), "Total Menu",  String.valueOf(totalMenuMakanan), ""));
            homePage.getCard3().setData(new CardItem(new ImageIcon(getClass().getResource("/javafoodapp/icon/profit.png")), "Total Transaksi", String.valueOf(totalTransaksi), ""));

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
