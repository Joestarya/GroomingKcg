package log;

import java.util.List;
import DAOgroom.catGroomDAO;
import DAOImplement.implementasi;
import model.*;
import view.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import connect.connector;

/**
 *
 * @author Snndita
 */
public class logControl {
    Connection conn = connector.connection();
    
    public void regis(ModelAdmin d){
        try{
            String q = "insert into login (username, password) values (?, ?)";
            PreparedStatement ps = conn.prepareStatement(q);
            
            ps.setString(1, d.getUsername());
            ps.setString(2, d.getPassword());
            ps.executeUpdate();
            JOptionPane.showMessageDialog (null, "Register berhasil, silakan login");
        }catch (Exception e){
            JOptionPane.showMessageDialog (null, "Error!" + e);
        }
    }
    
    public boolean cekLogin(String username, String password){
        boolean b = false;
        
        try{
            String q = "select username, password from login where username = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                b = true;
            }else{
                JOptionPane.showMessageDialog (null, "username atau password salah!");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog (null, "Error!" + e);
        } return b;
    }
    
    public String lupaSandi(String username) {
    String password = null;
    
    try{
        String q = "select password from login where username = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            password = rs.getString("password");
        }else{
            JOptionPane.showMessageDialog(null, "Username tidak terdaftar!");
        }
    }catch(Exception e) {
        JOptionPane.showMessageDialog(null, "Error! " + e);
    }
    return password;
    }
    
    public boolean gantiPassword(String username, String newPassword){
        try{
        String q = "update login set password = ? where username = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setString(1, newPassword);
        ps.setString(2, username);

        int updated = ps.executeUpdate();
        return updated > 0;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error!" + e);
            return false;
        }
    }
}
