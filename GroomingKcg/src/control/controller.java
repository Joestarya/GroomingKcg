package control;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import connect.connector;
import model.*;
import DAOImplement.implementasi;
import DAOgroom.catGroomDAO;
import view.*;

/**
 *
 * @author Snndita
 */
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
        //frame.framekamuuini().setModel(m);
    }
    
    public void insert(){
        
    }
    
    public void edit(){
        
    }
    
    public void edelete(){
        
    }
}
