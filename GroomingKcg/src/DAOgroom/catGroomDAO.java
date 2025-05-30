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
    
    final String select = "select layanan.id_layanan, layanan.nama_pelanggan, layanan.jadwal, paket.id_paket as id_paket, "
    + "paket.paket, paket.harga, paket.durasi from layanan join paket on layanan.id_paket = paket.id_paket";
    final String insert = "INSERT INTO layanan (nama_pelanggan, jadwal, id_paket) VALUES (?, ?, ?)";
    final String edit = "UPDATE layanan SET nama_pelanggan=?, jadwal=?, id_paket=? WHERE id_layanan=?";
    final String delete = "delete from layanan where id_layanan=?";
    
    public catGroomDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(ModelData d) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, d.getNama_pelanggan());
            statement.setTimestamp(2, Timestamp.valueOf(d.getJadwal()));
            statement.setInt(3, d.getId_paket());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                d.setId_layanan(rs.getInt(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
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
                md.setId_paket(resultSet.getInt("id_paket"));
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
    
}
