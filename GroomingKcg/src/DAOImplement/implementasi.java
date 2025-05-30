package DAOImplement;

import model.*;
import java.util.List;

/**
 *
 * @author Snndita
 */
public interface implementasi {
    public void insert(ModelData d);
    public void edit(ModelData d);
    public void delete(int id_layanan);
    public List<ModelData> getAll();

}