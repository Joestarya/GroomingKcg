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

public class controller {
    MainView frame;
    implementasi impDAO;
    List<modelData> md;

    public controller(MainView frame) {
        this.frame = frame;
        impDAO = new catGroomDAO();
        md = impDAO.getAll();
    }

    public void isitabel(){
        md = impDAO.getAll();
        GroomData m = new GroomData(md);
        //frame.getTabelData().setModel(m);
    }

    public void insert(){
        modelData data = new modelData();
        data.setNama_pelanggan(frame.getNama_pelanggan().getText());
        data.setPaket(frame.getPaket().getText());
        data.setDurasi(frame.getDurasi().getText());
        data.setJadwal(frame.getJadwal().getText());
        impDAO.insert(data);
    }

    public void update(){
        modelData data = new modelData();
        data.setNama_pelanggan(frame.getNama_pelanggan().getText());
        data.setPaket(frame.getPaket().getText());
        data.setDurasi(frame.getDurasi().getText());
        data.setJadwal(frame.getJadwal().getText());
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
        model.addColumn("durasi");
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
                    rs.getString("durasi"),
                    rs.getString("jadwal"),
                });
            }
            //frame.getTabelData().setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
    }
}
