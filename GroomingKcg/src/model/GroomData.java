package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Snndita
 */
public class GroomData extends AbstractTableModel {
    List<modelData> md;
    public GroomData(List<modelData>dp) {
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
            case 3 -> "durasi";
            case 4 -> "jadwal";
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return md.get(row).getId_layanan();
            case 1:
                return md.get(row).getNama_pelanggan();
            case 2:
                return md.get(row).getPaket();
            case 3:
                return md.get(row).getDurasi();
            case 4:
                return md.get(row).getJadwal();
            default:
                return null;
        }
    }
}
