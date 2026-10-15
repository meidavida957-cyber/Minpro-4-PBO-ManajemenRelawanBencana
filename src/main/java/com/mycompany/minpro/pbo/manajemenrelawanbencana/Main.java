package com.mycompany.minpro.pbo.manajemenrelawanbencana;

import com.mycompany.minpro.pbo.manajemenrelawanbencana.controller.ManajemenRelawan;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Bencana;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Relawan;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.RelawanLogistik;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.RelawanMedis;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.RelawanUmum;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.view.Menu;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final ManajemenRelawan manajemen = new ManajemenRelawan();
    private static final Menu menu = new Menu();

    public static void main(String[] args) {
        int pilihan;
        do {
            menu.tampilkanMenu();
            pilihan = bacaPilihan();
            prosesMenu(pilihan);
        } while (pilihan != 11);
        input.close();
    }

    private static int bacaPilihan() {
        System.out.print("Pilih menu: ");
        try {
            return Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static void prosesMenu(int pilihan) {
        switch (pilihan) {
            case 1:
                manajemen.tampilkanRelawan();
                break;
            case 2:
                manajemen.tampilkanBencana();
                break;
            case 3:
                tambahRelawan();
                break;
            case 4:
                tambahBencana();
                break;
            case 5:
                ubahRelawan();
                break;
            case 6:
                ubahBencana();
                break;
            case 7:
                System.out.print("Masukkan ID relawan yang ingin dihapus: ");
                manajemen.hapusRelawan(input.nextLine().trim());
                break;
            case 8:
                System.out.print("Masukkan ID bencana yang ingin dihapus: ");
                manajemen.hapusBencana(input.nextLine().trim());
                break;
            case 9:
                tambahPenempatan();
                break;
            case 10:
                manajemen.tampilkanPenempatan();
                break;
            case 11:
                System.out.println("\nProgram selesai.");
                break;
            default:
                System.out.println("Input salah, silakan input ulang sesuai ketentuan (1-11).");
        }
    }

    private static void tambahRelawan() {
        System.out.println("\n===== TAMBAH RELAWAN =====");
        System.out.println("1. Relawan Umum");
        System.out.println("2. Relawan Medis");
        System.out.println("3. Relawan Logistik");
        System.out.print("Pilih jenis relawan: ");

        int jenisRelawan;
        try {
            jenisRelawan = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka.");
            return;
        }
        if (jenisRelawan < 1 || jenisRelawan > 3) {
            System.out.println("Jenis relawan tidak tersedia.");
            return;
        }

        System.out.println("Contoh ID: R004");
        System.out.print("ID Relawan: ");
        String id = input.nextLine().trim();
        if (!id.matches("R\\d{3}")) {
            System.out.println("Format ID tidak valid. Gunakan format R001.");
            return;
        }

        System.out.print("Nama: ");
        String nama = input.nextLine().trim();
        System.out.print("Alamat: ");
        String alamat = input.nextLine().trim();
        System.out.print("No. HP: ");
        String noHp = input.nextLine().trim();
        System.out.print("Keahlian: ");
        String keahlian = input.nextLine().trim();

        if (nama.isEmpty() || alamat.isEmpty() || keahlian.isEmpty()) {
            System.out.println("Nama, alamat, dan keahlian tidak boleh kosong.");
            return;
        }
        if (!noHp.matches("\\d{10,13}")) {
            System.out.println("No. HP harus berupa angka 10-13 digit.");
            return;
        }

        Relawan relawan;
        if (jenisRelawan == 1) {
            relawan = new RelawanUmum(id, nama, alamat, noHp, keahlian);
        } else if (jenisRelawan == 2) {
            System.out.print("Spesialisasi: ");
            String spesialisasi = input.nextLine().trim();
            if (spesialisasi.isEmpty()) {
                System.out.println("Spesialisasi tidak boleh kosong.");
                return;
            }
            relawan = new RelawanMedis(id, nama, alamat, noHp, keahlian, spesialisasi);
        } else {
            System.out.print("Jenis Logistik: ");
            String jenisLogistik = input.nextLine().trim();
            if (jenisLogistik.isEmpty()) {
                System.out.println("Jenis logistik tidak boleh kosong.");
                return;
            }
            relawan = new RelawanLogistik(id, nama, alamat, noHp, keahlian, jenisLogistik);
        }
        manajemen.tambahRelawan(relawan);
    }

    private static void tambahBencana() {
        System.out.println("\n===== TAMBAH BENCANA =====");
        System.out.println("Contoh ID: B003");
        System.out.print("ID Bencana: ");
        String idBencana = input.nextLine().trim();
        if (!idBencana.matches("B\\d{3}")) {
            System.out.println("Format ID tidak valid. Gunakan format B001.");
            return;
        }

        System.out.print("Nama Bencana: ");
        String namaBencana = input.nextLine().trim();
        System.out.print("Lokasi: ");
        String lokasi = input.nextLine().trim();
        System.out.print("Jenis Bencana: ");
        String jenis = input.nextLine().trim();
        System.out.print("Status: ");
        String status = input.nextLine().trim();

        if (namaBencana.isEmpty() || lokasi.isEmpty() || jenis.isEmpty() || status.isEmpty()) {
            System.out.println("Nama, lokasi, jenis, dan status tidak boleh kosong.");
            return;
        }
        manajemen.tambahBencana(new Bencana(idBencana, namaBencana, lokasi, jenis, status));
    }

    private static void ubahRelawan() {
        System.out.println("\n===== UBAH RELAWAN =====");
        System.out.print("Masukkan ID relawan yang ingin diubah: ");
        Relawan relawan = manajemen.cariRelawan(input.nextLine().trim());
        if (relawan == null) {
            System.out.println("Relawan tidak ditemukan.");
            return;
        }
        System.out.println("(Kosongkan isian jika tidak ingin mengubah)");

        System.out.print("Nama [" + relawan.getNama() + "]: ");
        String nama = input.nextLine().trim();
        System.out.print("Alamat [" + relawan.getAlamat() + "]: ");
        String alamat = input.nextLine().trim();
        System.out.print("No. HP [" + relawan.getNoHp() + "]: ");
        String noHp = input.nextLine().trim();
        System.out.print("Keahlian [" + relawan.getKeahlian() + "]: ");
        String keahlian = input.nextLine().trim();

        if (!noHp.isEmpty() && !noHp.matches("\\d{10,13}")) {
            System.out.println("No. HP harus berupa angka 10-13 digit.");
            return;
        }
        if (!nama.isEmpty()) relawan.setNama(nama);
        if (!alamat.isEmpty()) relawan.setAlamat(alamat);
        if (!noHp.isEmpty()) relawan.setNoHp(noHp);
        if (!keahlian.isEmpty()) relawan.setKeahlian(keahlian);

        if (relawan instanceof RelawanMedis) {
            RelawanMedis medis = (RelawanMedis) relawan;
            System.out.print("Spesialisasi [" + medis.getSpesialisasi() + "]: ");
            String s = input.nextLine().trim();
            if (!s.isEmpty()) medis.setSpesialisasi(s);
        } else if (relawan instanceof RelawanLogistik) {
            RelawanLogistik logistik = (RelawanLogistik) relawan;
            System.out.print("Jenis Logistik [" + logistik.getJenisLogistik() + "]: ");
            String s = input.nextLine().trim();
            if (!s.isEmpty()) logistik.setJenisLogistik(s);
        }
        manajemen.ubahRelawan(relawan);
    }

    private static void ubahBencana() {
        System.out.println("\n===== UBAH BENCANA =====");
        System.out.print("Masukkan ID bencana yang ingin diubah: ");
        Bencana bencana = manajemen.cariBencana(input.nextLine().trim());
        if (bencana == null) {
            System.out.println("Bencana tidak ditemukan.");
            return;
        }
        System.out.println("(Kosongkan isian jika tidak ingin mengubah)");

        System.out.print("Nama Bencana [" + bencana.getNamaBencana() + "]: ");
        String nama = input.nextLine().trim();
        System.out.print("Lokasi [" + bencana.getLokasi() + "]: ");
        String lokasi = input.nextLine().trim();
        System.out.print("Jenis [" + bencana.getJenisBencana() + "]: ");
        String jenis = input.nextLine().trim();
        System.out.print("Status [" + bencana.getStatus() + "]: ");
        String status = input.nextLine().trim();

        if (!nama.isEmpty()) bencana.setNamaBencana(nama);
        if (!lokasi.isEmpty()) bencana.setLokasi(lokasi);
        if (!jenis.isEmpty()) bencana.setJenisBencana(jenis);
        if (!status.isEmpty()) bencana.setStatus(status);
        manajemen.ubahBencana(bencana);
    }

    private static void tambahPenempatan() {
        System.out.println("\n===== TAMBAH PENEMPATAN =====");
        System.out.print("Masukkan ID Relawan (contoh R001): ");
        String idRelawan = input.nextLine().trim();
        System.out.print("Masukkan ID Bencana (contoh B001): ");
        String idBencana = input.nextLine().trim();
        manajemen.tambahPenempatan(idRelawan, idBencana);
    }
}