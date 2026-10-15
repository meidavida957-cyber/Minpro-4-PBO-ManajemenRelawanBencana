package com.mycompany.minpro.pbo.manajemenrelawanbencana.model;

public class RelawanUmum extends Relawan {

    public RelawanUmum(String id, String nama, String alamat,
                       String noHp, String keahlian) {
        super(id, nama, alamat, noHp, keahlian);
    }

    @Override
    public String getJenis() {
        return "Relawan Umum";
    }

    @Override
    public String getTugasUtama() {
        return "Evakuasi dan pendataan warga";
    }
}