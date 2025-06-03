package DAOgroom;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import connect.connector;
import model.*;
import DAOImplement.implementasi;

/**
 *
 * @author Snndita
 */
public class catGroomDAO implements implementasi {
    Connection connection;
    LocalDateTime now = LocalDateTime.now();
    Timestamp timestamp = Timestamp.valueOf(now);
    
    final String select = "SELECT layanan.id_layanan, layanan.nama_pelanggan, layanan.jadwal, layanan.jumlah_kucing, layanan.harga_total," +
                      "datapaket.paket AS paket, datapaket.harga, datapaket.durasi " +
                      "FROM layanan JOIN datapaket ON layanan.paket = datapaket.paket";

    final String select1 = "SELECT paket, harga, durasi FROM datapaket";

    final String insert  = "INSERT INTO layanan (nama_pelanggan, paket, jadwal, jumlah_kucing) VALUES (?, ?, ?, ?)";

    final String edit    = "UPDATE layanan SET nama_pelanggan=?, paket=?, jadwal=?, jumlah_kucing=? WHERE id_layanan=?";

    final String delete  = "DELETE FROM layanan WHERE id_layanan=?";

    
    public catGroomDAO() {
        connection = connector.connection();
    }

   

    @Override
    public List<ModelData> getAll() {
        List<ModelData> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try{
            statement = connection.prepareStatement(select);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                ModelData md = new ModelData();
                md.setId_layanan(resultSet.getInt("id_layanan"));
                md.setNama_pelanggan(resultSet.getString("nama_pelanggan"));
                md.setJadwal(resultSet.getTimestamp("jadwal").toLocalDateTime());
                md.setPaket(resultSet.getString("paket"));
                md.setHarga(resultSet.getDouble("harga"));
                md.setDurasi(resultSet.getInt("durasi"));
                md.setJumlah_kucing(resultSet.getInt("jumlah_kucing"));
                md.setHarga_total(resultSet.getFloat("harga_total"));
                list.add(md);
            }
        }catch (SQLException ex){
            Logger.getLogger(catGroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } return list;
    }
    
    @Override
        public List<TableDataPaket> getTablePaket() {
        List<TableDataPaket> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try{
            statement = connection.prepareStatement(select1);
            resultSet = statement.executeQuery();
                    
            while(resultSet.next()){
                TableDataPaket tp = new TableDataPaket();
                tp.setPaket(resultSet.getString("paket"));
                tp.setHarga(resultSet.getDouble("harga"));
                tp.setDurasi(resultSet.getInt("durasi"));
                list.add(tp);
            }
        }catch (SQLException ex){
            Logger.getLogger(catGroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } return list;
    }

    

    @Override
    public void insert(ModelData d) {
    PreparedStatement statement = null;

    try {
        statement = connection.prepareStatement(insert, statement.RETURN_GENERATED_KEYS);
        statement.setString(1, d.getNama_pelanggan());
        statement.setString(2, d.getPaket());
        // Convert LocalDateTime to Timestamp for database
        statement.setTimestamp(3, Timestamp.valueOf(d.getJadwal()));
        statement.setInt(4, d.getJumlah_kucing());
        int executeUpdate = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        while (rs.next()) {
            d.setId_layanan(rs.getInt(1));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (statement != null) statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
}
}

    public void edit(ModelData p) {
    PreparedStatement statement = null;

    try {
        statement = connection.prepareStatement(edit);
        statement.setString(1, p.getNama_pelanggan());
        statement.setString(2, p.getPaket());
        statement.setTimestamp(3, Timestamp.valueOf(p.getJadwal()));
        statement.setInt(4, p.getId_layanan());
        statement.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (statement != null) statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

    

    @Override
    public void delete(int id_layanan) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id_layanan);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try {
                statement.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
