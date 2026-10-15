package com.mycompany.minpro.pbo.manajemenrelawanbencana.controller;

import com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.BencanaDAO;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.PenempatanDAO;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.RelawanDAO;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.impl.BencanaDAOImpl;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.impl.PenempatanDAOImpl;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.impl.RelawanDAOImpl;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Bencana;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Penempatan;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Relawan;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.RelawanLogistik;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.RelawanMedis;
import java.sql.SQLException;
import java.util.List;

public class ManajemenRelawan {

    // Controller hanya mengenal interface DAO, bukan detail JDBC-nya
    private RelawanDAO relawanDAO;
    private BencanaDAO bencanaDAO;
    private PenempatanDAO penempatanDAO;

    public ManajemenRelawan() {
        relawanDAO = new RelawanDAOImpl();
        bencanaDAO = new BencanaDAOImpl();
        penempatanDAO = new PenempatanDAOImpl();
        // Tidak ada dummy data di sini. Data awal berasal dari file .sql
    }

    // =========================
    // RELAWAN
    // =========================

    public void tambahRelawan(Relawan relawan) {
        try {
            if (relawanDAO.findById(relawan.getId()) != null) {
                System.out.println("ID relawan sudah digunakan.");
                return;
            }
            relawanDAO.insert(relawan);
            System.out.println("Relawan berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambah relawan: " + e.getMessage());
        }
    }

    public void tampilkanRelawan() {
        try {
            List<Relawan> daftar = relawanDAO.findAll();
            if (daftar.isEmpty()) {
                System.out.println("Belum ada data relawan.");
                return;
            }
            System.out.println("\n===== DATA RELAWAN =====");
            for (Relawan relawan : daftar) {
                System.out.println("-------------------------");
                relawan.tampilkanInfo();   // polymorphism
            }
        } catch (SQLException e) {
            System.out.println("Gagal membaca data relawan: " + e.getMessage());
        }
    }

    public Relawan cariRelawan(String id) {
        try {
            return relawanDAO.findById(id);
        } catch (SQLException e) {
            System.out.println("Gagal mencari relawan: " + e.getMessage());
            return null;
        }
    }

    public void ubahRelawan(Relawan relawan) {
        try {
            relawanDAO.update(relawan);
            System.out.println("Data relawan berhasil diubah.");
        } catch (SQLException e) {
            System.out.println("Gagal mengubah relawan: " + e.getMessage());
        }
    }

    public void hapusRelawan(String id) {
        try {
            if (relawanDAO.findById(id) == null) {
                System.out.println("Relawan tidak ditemukan.");
                return;
            }
            relawanDAO.delete(id);
            System.out.println("Relawan berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus relawan: " + e.getMessage());
        }
    }

    // =========================
    // BENCANA
    // =========================

    public void tambahBencana(Bencana bencana) {
        try {
            if (bencanaDAO.findById(bencana.getIdBencana()) != null) {
                System.out.println("ID bencana sudah digunakan.");
                return;
            }
            bencanaDAO.insert(bencana);
            System.out.println("Bencana berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambah bencana: " + e.getMessage());
        }
    }

    public void tampilkanBencana() {
        try {
            List<Bencana> daftar = bencanaDAO.findAll();
            if (daftar.isEmpty()) {
                System.out.println("Belum ada data bencana.");
                return;
            }
            System.out.println("\n===== DATA BENCANA =====");
            for (Bencana bencana : daftar) {
                System.out.println("-------------------------");
                bencana.tampilkanInfo();
            }
        } catch (SQLException e) {
            System.out.println("Gagal membaca data bencana: " + e.getMessage());
        }
    }

    public Bencana cariBencana(String id) {
        try {
            return bencanaDAO.findById(id);
        } catch (SQLException e) {
            System.out.println("Gagal mencari bencana: " + e.getMessage());
            return null;
        }
    }

    public void ubahBencana(Bencana bencana) {
        try {
            bencanaDAO.update(bencana);
            System.out.println("Data bencana berhasil diubah.");
        } catch (SQLException e) {
            System.out.println("Gagal mengubah bencana: " + e.getMessage());
        }
    }

    public void hapusBencana(String id) {
        try {
            if (bencanaDAO.findById(id) == null) {
                System.out.println("Bencana tidak ditemukan.");
                return;
            }
            bencanaDAO.delete(id);
            System.out.println("Bencana berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus bencana: " + e.getMessage());
        }
    }

    // =========================
    // PENEMPATAN
    // =========================

    public void tambahPenempatan(String idRelawan, String idBencana) {
        try {
            if (relawanDAO.findById(idRelawan) == null) {
                System.out.println("Relawan tidak ditemukan.");
                return;
            }
            if (bencanaDAO.findById(idBencana) == null) {
                System.out.println("Bencana tidak ditemukan.");
                return;
            }
            if (penempatanDAO.exists(idRelawan, idBencana)) {
                System.out.println("Relawan tersebut sudah ditugaskan di bencana yang sama.");
                return;
            }
            penempatanDAO.insert(idRelawan, idBencana);
            System.out.println("Relawan berhasil ditempatkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambah penempatan: " + e.getMessage());
        }
    }

    public void tampilkanPenempatan() {
        try {
            List<Penempatan> daftar = penempatanDAO.findAll();
            if (daftar.isEmpty()) {
                System.out.println("Belum ada penempatan relawan.");
                return;
            }
            System.out.println("\n===== PENEMPATAN RELAWAN =====");
            for (Penempatan penempatan : daftar) {
                System.out.println("-------------------------");
                penempatan.tampilkanInfo();
            }
        } catch (SQLException e) {
            System.out.println("Gagal membaca data penempatan: " + e.getMessage());
        }
    }
}