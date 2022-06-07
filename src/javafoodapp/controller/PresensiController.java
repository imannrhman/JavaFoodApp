package javafoodapp.controller;

import javafoodapp.database.dao.presensi.ImplementasiPresensi;

import javafoodapp.database.dao.presensi.PresensiDAO;
import javafoodapp.database.tables.MenuMakananTable;
import javafoodapp.database.tables.PresensiTable;
import javafoodapp.model.Presensi;
import javafoodapp.view.pages.PresensiPage;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PresensiController {

    private final PresensiPage presensiPage;
    private final ImplementasiPresensi implementasiPresensi;
    private List<Presensi> list;


    public PresensiController(PresensiPage presensiPage) {
        this.presensiPage = presensiPage;
        this.implementasiPresensi = new PresensiDAO();
    }

    public void fillTable(int id) {
        try {
            list = implementasiPresensi.getAllPresensi(id);
            presensiPage.getTable().setModel(new PresensiTable(list));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void addPresensi(int id) {
      try {
          System.out.println("jalan");
          String status = "";
          Calendar c = Calendar.getInstance();

          int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

          if(timeOfDay >= 8 && timeOfDay < 12){
              status = "Jam Masuk";
          }
          else if(timeOfDay >= 13 && timeOfDay < 19){
              status = "Jam Keluar";
          }
          else {
              status = "Tidak Dianggap";
          }

          Presensi presensi = new Presensi();
          presensi.setTanggal(new Date());
          presensi.setStatus(status);
          System.out.println("jalan2");
          implementasiPresensi.insert(id,presensi);
      } catch (Exception e) {
          System.out.println(e);
      }
    }

    public void deleteData() {
        try {
            int row = presensiPage.getTable().getSelectedRow();
            implementasiPresensi.delete(list.get(row).getId());
            System.out.println(list.get(row).getId());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
