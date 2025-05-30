package DAOgroom;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import connect.connector;
import model.*;
import DAOImplement.implementasi;

/**
 *
 * @author Snndita
 */
public class catGroomDAO implements implementasi {
    Connection connection;
    
    final String select = "select * from paket";
    final String insert = "insert into paket (nama_pelanggan, paket, jadwal) values (?, ?, ?)";
    final String edit = "update buku set nama_pelanggan=?, paket=?, jadwal=? where id_layanan=?";
    final String delete = "delete from buku where id_layanan=?";
    
    public catGroomDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(ModelData d) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, d.getNama_pelanggan());
            statement.setString(2, d.getPaket());
            statement.setTimestamp(3, Timestamp.valueOf(d.getJadwal()));
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ModelData data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
