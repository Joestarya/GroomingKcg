package model;

import java.time.LocalDateTime;
/**
 *
 * @author Snndita
 */
public class modelData {
    private Integer id_layanan;
    private String nama_pelanggan;
    private LocalDateTime jadwal;
    private Integer id_paket;

    public Integer getId_layanan() {
        return id_layanan;
    }

    public void setId_layanan(Integer id_layanan) {
        this.id_layanan = id_layanan;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }
    
    public LocalDateTime getJadwal() {
        return jadwal;
    }

    public void setJadwal(LocalDateTime jadwal) {
        this.jadwal = jadwal;
    }

    public Integer getId_paket() {
        return id_paket;
    }

    public void setId_paket(Integer id_paket) {
        this.id_paket = id_paket;
    }
    
    
}
