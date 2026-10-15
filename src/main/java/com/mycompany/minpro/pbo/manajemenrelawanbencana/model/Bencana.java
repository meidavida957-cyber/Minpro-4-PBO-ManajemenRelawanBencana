package com.mycompany.minpro.pbo.manajemenrelawanbencana.model;

public class Bencana {

    private String idBencana;
    private String namaBencana;
    private String lokasi;
    private String jenisBencana;
    private String status;

    public Bencana(String idBencana, String namaBencana,
                   String lokasi, String jenisBencana,
                   String status) {

        this.idBencana = idBencana;
        this.namaBencana = namaBencana;
        this.lokasi = lokasi;
        this.jenisBencana = jenisBencana;
        this.status = status;
    }

    public String getIdBencana() {
        return idBencana;
    }

    public void setIdBencana(String idBencana) {
        this.idBencana = idBencana;
    }

    public String getNamaBencana() {
        return namaBencana;
    }

    public void setNamaBencana(String namaBencana) {
        this.namaBencana = namaBencana;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getJenisBencana() {
        return jenisBencana;
    }

    public void setJenisBencana(String jenisBencana) {
        this.jenisBencana = jenisBencana;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void tampilkanInfo() {
        System.out.println("ID Bencana   : " + idBencana);
        System.out.println("Nama Bencana : " + namaBencana);
        System.out.println("Lokasi       : " + lokasi);
        System.out.println("Jenis        : " + jenisBencana);
        System.out.println("Status       : " + status);
    }
}
