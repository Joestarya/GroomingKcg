package connect;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class connector {
    static Connection con;
    
    public static Connection connection() {
        if(con==null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("groomingkcg");
            data.setUser("root");
            data.setPassword("");
            try{
                con = data.getConnection();
                System.out.println("Koneksi sukses");
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Koneksi gagal");
            }
            
        }
    return con;
    }
}
