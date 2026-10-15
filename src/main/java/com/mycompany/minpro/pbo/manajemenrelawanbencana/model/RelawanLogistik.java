package com.mycompany.minpro.pbo.manajemenrelawanbencana.model;

public class RelawanLogistik extends Relawan {

    private String jenisLogistik;

    public RelawanLogistik(String id, String nama, String alamat,
                           String noHp, String keahlian,
                           String jenisLogistik) {

        super(id, nama, alamat, noHp, keahlian);
        this.jenisLogistik = jenisLogistik;
    }

    public String getJenisLogistik() {
        return jenisLogistik;
    }

    public void setJenisLogistik(String jenisLogistik) {
        this.jenisLogistik = jenisLogistik;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis Relawan : Logistik");
        System.out.println("Jenis Logistik: " + jenisLogistik);
    }
    
    @Override
    public String getJenis() {
        return "Relawan Logistik";
    }

    @Override
    public String getTugasUtama() {
        return "Distribusi bantuan dan pengelolaan gudang";
    }
}
