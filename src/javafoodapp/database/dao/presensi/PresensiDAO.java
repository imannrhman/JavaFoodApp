package javafoodapp.database.dao.presensi;



import javafoodapp.database.ConnectionDatabase;
import javafoodapp.database.dao.auth.AuthDAO;
import javafoodapp.database.dao.bahanbaku.BahanBakuDAO;
import javafoodapp.model.BahanBaku;
import javafoodapp.model.Presensi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PresensiDAO implements ImplementasiPresensi{
    @Override
    public List<Presensi> getAllPresensi(int id) throws SQLException {
        try {
            List<Presensi> list = new ArrayList<Presensi>();
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM presensi where id_user = "+ id + "");

            while (result.next()) {
                Presensi presensi = new Presensi();
                presensi.setId(result.getInt("id"));
                presensi.setTanggal(result.getDate("tanggal"));
                presensi.setStatus(result.getString("status"));
                list.add(presensi);
            }

            statement.close();
            result.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BahanBakuDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void insert(int idUser,Presensi presensi) throws SQLException {
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "insert into presensi ( id_user, tanggal, status)  values ( ? , ? , ? )");


            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            statement.setInt(1,idUser);
            statement.setString(2, format.format(presensi.getTanggal()));
            statement.setString(3, presensi.getStatus());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PresensiDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex ;
        }
    }

    @Override
    public void update(Presensi presensi) {

    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "DELETE FROM presensi WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PresensiDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}
