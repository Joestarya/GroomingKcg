package DAOImplement;

import model.*;
import java.util.List;

/**
 *
 * @author Snndita
 */
public interface implementasi {
    public void insert(modelData d);
    public void edit(modelData d);
    public void delete(int id_layanan);
    public List<modelData> getAll();
}