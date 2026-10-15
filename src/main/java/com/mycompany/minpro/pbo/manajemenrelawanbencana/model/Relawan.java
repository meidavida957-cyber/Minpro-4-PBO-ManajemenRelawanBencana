package com.mycompany.minpro.pbo.manajemenrelawanbencana.model;

import com.mycompany.minpro.pbo.manajemenrelawanbencana.interfaces.DapatDitugaskan;

public abstract class Relawan implements DapatDitugaskan {

    private String id;
    private String nama;
    private String alamat;
    private String noHp;
    private String keahlian;

    public Relawan(String id, String nama, String alamat, String noHp, String keahlian) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.noHp = noHp;
        this.keahlian = keahlian;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }

    public void tampilkanInfo() {
        System.out.println("ID       : " + id);
        System.out.println("Nama     : " + nama);
        System.out.println("Alamat   : " + alamat);
        System.out.println("No. HP   : " + noHp);
        System.out.println("Keahlian : " + keahlian);
        System.out.println("Tugas Utama : " + getTugasUtama());
    }
    
    // ABSTRACT METHOD
    public abstract String getJenis();

    // OVERLOADING
    public void tampilkanInfo(boolean ringkas) {
        if (ringkas) {
            System.out.println(id + " | " + nama + " | " + getJenis());
        } else {
            tampilkanInfo();
        }
    }
}
