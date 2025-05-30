package model;

import java.time.LocalDateTime;
/**
 *
 * @author Snndita
 */
public class ModelData {
    //tabel layanan
    private Integer id_layanan;
    private String nama_pelanggan;
    private LocalDateTime jadwal;
    
    //tabel paket
    private String paket;
    private Double harga;
    private Integer durasi;

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

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Integer getDurasi() {
        return durasi;
    }

    public void setDurasi(Integer durasi) {
        this.durasi = durasi;
    }
    
    
}
