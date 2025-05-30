package control;
import java.util.List;
import DAOgroom.catGroomDAO;
import DAOImplement.implementasi;
import model.*;
import view.MainView;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import connect.connector;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class controller {
    MainView frame;
    implementasi impDAO;
    List<ModelData> md;

    public controller(MainView frame) {
        this.frame = frame;
        impDAO = new catGroomDAO();
        md = impDAO.getAll();
    }

    public void isitabel(){
        md = impDAO.getAll();
        ModelTableData m = new ModelTableData(md);
        //frame.getTabelData().setModel(m);
    }

    public void insert(){
        ModelData data = new ModelData();
        data.setNama_pelanggan(frame.getNama_pelanggan().getText());
        data.setPaket(frame.getPaket().getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime jadwal = LocalDateTime.parse(frame.getJadwal().getText(), formatter);
        data.setJadwal(jadwal);
        impDAO.insert(data);
    }

    public void update(){
        ModelData data = new ModelData();
        data.setNama_pelanggan(frame.getNama_pelanggan().getText());
        data.setPaket(frame.getPaket().getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime jadwal = LocalDateTime.parse(frame.getJadwal().getText(), formatter);
        data.setJadwal(jadwal);
        data.setId_layanan(Integer.parseInt(frame.getId_layanan().getText()));
        impDAO.update(data);
    }

    public void delete(){
        int id_layanan = Integer.parseInt(frame.getId_layanan().getText());
        impDAO.delete(id_layanan);
    }

    public void search(String column, String keyword){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id_layanan");
        model.addColumn("nama_pelanggan");
        model.addColumn("paket");
        model.addColumn("jadwal");

        try {
            Connection conn = connector.connection();
            String sql = "SELECT * FROM grooming WHERE " + column + " LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                model.addRow(new Object[]{
                    rs.getInt("id_layanan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("paket"),
                    rs.getString("jadwal"),
                });
            }
            //frame.getTabelData().setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
    }
}
