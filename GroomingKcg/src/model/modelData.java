package model;

import java.time.LocalDateTime;
/**
 *
 * @author Snndita
 */
public class modelData {
    private Integer id_layanan;
    private String nama_pelanggan;
    private String paket;
    private String durasi;
    private LocalDateTime jadwal;

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

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public LocalDateTime getJadwal() {
        return jadwal;
    }

    public void setJadwal(LocalDateTime jadwal) {
        this.jadwal = jadwal;
    }
    
    
}
