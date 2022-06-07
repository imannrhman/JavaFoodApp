package javafoodapp.database.dao.presensi;


import javafoodapp.model.Presensi;

import java.sql.SQLException;
import java.util.List;

public interface ImplementasiPresensi {

    public List<Presensi> getAllPresensi(int id) throws SQLException;

    public void insert(int idUser,Presensi presensi) throws SQLException;

    public void update(Presensi presensi);

    public void delete(int id) throws SQLException;
}
