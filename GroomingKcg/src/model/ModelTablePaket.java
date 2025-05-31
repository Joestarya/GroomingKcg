package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.TableDataPaket;

/**
 *
 * @author Snndita
 */
public class ModelTablePaket extends AbstractTableModel {
    List<TableDataPaket> tp;
    public ModelTablePaket(List<TableDataPaket>tp) {
        this.tp = tp;
    }

    @Override
    public int getRowCount() {
        return tp.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Paket";
            case 1 -> "Harga";
            case 2 -> "Durasi";
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int row, int column) {
        return switch (column) {
            case 0 -> tp.get(row).getPaket();
            case 1 -> tp.get(row).getHarga();
            case 2 -> tp.get(row).getDurasi();
            default -> null;
        };
    }
}
