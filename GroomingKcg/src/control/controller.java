package control;

import java.util.List;
import DAOgroom.catGroomDAO;
import DAOImplement.implementasi;
import model.*;
import view.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import connect.connector;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class controller {
    MainView frame;    
    TablePaket frame1;
    AdminLog frame2;
    LupaSandi frame3;
    implementasi impDAO;
    List<ModelData> md;
    List<TableDataPaket> tp;
    
    public controller(MainView frame) {
        this.frame = frame;
        impDAO = new catGroomDAO();
        md = impDAO.getAll();
    }

    public controller(TablePaket frame1){
        this.frame1 = frame1;
        impDAO = new catGroomDAO();
        tp = impDAO.getTablePaket();
    }
    
    public void isitabel(){    
        //md = impDAO.getAll();
        //ModelTableData m = new ModelTableData(md);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id_layanan");
        model.addColumn("nama_pelanggan");
         
        model.addColumn("paket");
        model.addColumn("jadwal");
        model.addColumn("harga");
        model.addColumn("jumlah_kucing");
        model.addColumn("harga_total");
        try {
            Connection conn = connector.connection();
            String sql = "SELECT layanan.*, datapaket.harga " +
             "FROM layanan JOIN datapaket ON layanan.paket = datapaket.paket ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                model.addRow(new Object[]{
                    rs.getInt("id_layanan"),
                    rs.getString("nama_pelanggan"),
                    
                    rs.getString("paket"),
                    rs.getString("jadwal"), 
                    rs.getInt("harga"),
                    rs.getInt("jumlah_kucing"),
                    rs.getInt("harga_total")
                });
            }
            frame.getTableData().setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
    }
    
    public void isitablepaket(){
        tp = impDAO.getTablePaket();
        ModelTablePaket mtp = new ModelTablePaket(tp);
        frame1.getTablePaket().setModel(mtp);
    }

    public void insert(){
        ModelData data = new ModelData();
        data.setNama_pelanggan(frame.getNama_pelanggan().getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime jadwal = LocalDateTime.parse(frame.getJadwal().getText().trim(), formatter);
        data.setJadwal(jadwal);
        data.setJumlah_kucing(Integer.valueOf(frame.getJumlah_kucing().getText()));
        
        data.setPaket(frame.getPaket().getText());
                impDAO.insert(data);
    }

    public void edit(){
        ModelData data = new ModelData();
        data.setNama_pelanggan(frame.getNama_pelanggan().getText());
        data.setPaket(frame.getPaket().getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime jadwal = LocalDateTime.parse(frame.getJadwal().getText(), formatter);
        data.setJadwal(jadwal);
        data.setJumlah_kucing(Integer.valueOf(frame.getJumlah_kucing().getText()));
        data.setId_layanan(Integer.valueOf(frame.getId_layanan().getText()));
        impDAO.edit(data);
    }

    public void delete(){
        int id_layanan = Integer.parseInt(frame.getId_layanan().getText());
        impDAO.delete(id_layanan);
    }

    public void search(String column, String keyword) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("id_layanan");
    model.addColumn("nama_pelanggan");
    model.addColumn("paket");
    model.addColumn("jadwal");
    model.addColumn("harga");
    model.addColumn("jumlah_kucing");
    model.addColumn("harga_total");

    try {
        Connection conn = connector.connection();
        String sql = "SELECT layanan.*, datapaket.harga " +
                     "FROM layanan JOIN datapaket ON layanan.paket = datapaket.paket " +
                     "WHERE layanan." + column + " LIKE ? " +
                     "ORDER BY jadwal ASC";  // <-- disini sorting-nya, bisa ganti ASC/DESC

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_layanan"),
                rs.getString("nama_pelanggan"),
                rs.getString("paket"),
                rs.getString("jadwal"),
                rs.getFloat("harga"),
                rs.getInt("jumlah_kucing"),
                rs.getInt("harga_total")
            });
        }
        frame.getTableData().setModel(model);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
    }
}

        
    public void clear() {
        frame.getId_layanan().setText("");
        frame.getNama_pelanggan().setText("");
        frame.getJadwal().setText("");
        frame.getPaket().setText("");
        frame.getJumlah_kucing().setText("");
    }
    
    
}

