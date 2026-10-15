package com.mycompany.minpro.pbo.manajemenrelawanbencana.model;

public class Penempatan {

    private Relawan relawan;
    private Bencana bencana;

    public Penempatan(Relawan relawan, Bencana bencana) {
        this.relawan = relawan;
        this.bencana = bencana;
    }

    public Relawan getRelawan() {
        return relawan;
    }

    public void setRelawan(Relawan relawan) {
        this.relawan = relawan;
    }

    public Bencana getBencana() {
        return bencana;
    }

    public void setBencana(Bencana bencana) {
        this.bencana = bencana;
    }

    public void tampilkanInfo() {
        System.out.println("ID Relawan   : " + relawan.getId());
        System.out.println("Nama Relawan : " + relawan.getNama());
        System.out.println("ID Bencana   : " + bencana.getIdBencana());
        System.out.println("Bencana      : " + bencana.getNamaBencana());
        System.out.println("Lokasi       : " + bencana.getLokasi());
    }
}
