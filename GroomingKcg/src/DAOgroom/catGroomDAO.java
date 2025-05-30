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
    
    final String select = "select layanan.id_layanan, layanan.nama_pelanggan, layanan.jadwal, paket.paket as paket, "
    + "paket.paket, paket.harga, paket.durasi from layanan join paket on layanan.paket = paket.paket";
    
    final String insert = "INSERT INTO layanan (nama_pelanggan, paket, jadwal) VALUES (?, ?, ?)";
    final String edit = "UPDATE layanan SET nama_pelanggan=?, jadwal=?, id_paket=? WHERE id_layanan=?";
    final String delete = "delete from layanan where id_layanan=?";
    
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
    public void insert(ModelData d) {
    PreparedStatement statement = null;

    try {
        statement = connection.prepareStatement(insert, statement.RETURN_GENERATED_KEYS);
        statement.setString(1, d.getNama_pelanggan());
        statement.setString(2, d.getPaket());
        // Convert LocalDateTime to Timestamp for database
        statement.setTimestamp(3, Timestamp.valueOf(d.getJadwal()));

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

    @Override
    public void edit(ModelData d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id_layanan) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
