package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Snndita
 */
public class ModelTableData extends AbstractTableModel {
    List<ModelData> md;
    public ModelTableData(List<ModelData>md) {
        this.md = md;
    }

    @Override
    public int getRowCount() {
        return md.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "id_layanan";
            case 1 -> "nama_pelanggan";
            case 2 -> "paket";
            case 3 -> "jadwal";
            case 4 -> "harga";
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int row, int column) {
        return switch (column) {
            case 0 -> md.get(row).getId_layanan();
            case 1 -> md.get(row).getNama_pelanggan();
            case 2 -> md.get(row).getPaket();
            case 3 -> md.get(row).getJadwal();
            case 4 -> md.get(row).getHarga();
            default -> null;
        };
    }
}
