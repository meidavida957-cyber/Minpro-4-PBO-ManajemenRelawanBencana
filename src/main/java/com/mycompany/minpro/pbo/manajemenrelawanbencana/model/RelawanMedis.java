package com.mycompany.minpro.pbo.manajemenrelawanbencana.model;

public class RelawanMedis extends Relawan {

    private String spesialisasi;

    public RelawanMedis(String id, String nama, String alamat,
                        String noHp, String keahlian,
                        String spesialisasi) {

        super(id, nama, alamat, noHp, keahlian);
        this.spesialisasi = spesialisasi;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis Relawan : Medis");
        System.out.println("Spesialisasi  : " + spesialisasi);
    }
    
    @Override
    public String getJenis() {
        return "Relawan Medis";
    }

    @Override
    public String getTugasUtama() {
        return "Pertolongan pertama dan perawatan korban";
    }
}