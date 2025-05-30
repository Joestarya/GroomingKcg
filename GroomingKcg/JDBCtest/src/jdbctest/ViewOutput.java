package jdbctest;
import java.sql.*;
import javax.swing.*;

public class ViewOutput {
    Connector connector = new Connector();
    
    String data[][] = new String[500][2];
    
    JFrame window = new JFrame("JDBC");
    JTable tabel;
    JScrollPane scrollPane;
    
    //membuat kolom
    Object namaKolom[] = {"JUDUL","GENRE"};
    
    public ViewOutput(){
        window.setLayout(null);
        window.setSize(550,600);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        try{
            int jmlData = 0;
            String query = "Select * from data_film";
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultset = connector.statement.executeQuery(query);
            
            while(resultset.next()){
                data[jmlData][0] = resultset.getString("judul");
                data[jmlData][1] = resultset.getString("genre");
                
                jmlData++;
            }
            connector.statement.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
        
        tabel = new JTable(data, namaKolom);
        scrollPane = new JScrollPane(tabel);
        window.add(scrollPane);
        
        scrollPane.setBounds(20, 35, 500, 300);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }
}
