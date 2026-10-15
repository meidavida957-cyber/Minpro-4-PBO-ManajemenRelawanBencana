# Minpro-4-PBO-ManajemenRelawanBencana

## Sistem Manajemen Relawan Bencana
**Java + OOP + JDBC + DAO + MySQL**

**Identitas Mahasiswa**
- Nama: Meilanie
- NIM: 2509116109
- Kelas: C
- Mata Kuliah: Pemrograman Berorientasi Objek
- Mini Project: 4

## 1. Deskripsi Program

Sistem Manajemen Relawan Bencana merupakan program berbasis console yang dibuat menggunakan bahasa Java. Program ini digunakan untuk mengelola data relawan, data bencana, dan penempatan relawan pada suatu bencana.

Program memiliki tiga jenis relawan, yaitu Relawan Umum, Relawan Medis, dan Relawan Logistik. Setiap jenis relawan memiliki tugas dan karakteristik yang berbeda.

Pada Mini Project 4, penyimpanan data menggunakan database MySQL yang dihubungkan melalui JDBC. Program juga menggunakan pola DAO (Data Access Object) agar proses akses database terpisah dari logika utama program.

## 2. Tujuan Program

Tujuan pembuatan program ini adalah:
- Mengelola data relawan dan bencana.
- Menyimpan data secara permanen menggunakan MySQL.
- Menerapkan konsep Object-Oriented Programming (OOP).
- Menghubungkan aplikasi Java dengan database menggunakan JDBC.
- Memisahkan logika program dan akses database menggunakan DAO.

## 3. Fitur Program

Program menyediakan 11 pilihan menu:

| No. | Menu | Fungsi |
|---|---|---|
| 1 | Tampilkan Relawan | Menampilkan seluruh data relawan |
| 2 | Tampilkan Bencana | Menampilkan seluruh data bencana |
| 3 | Tambah Relawan | Menambahkan data relawan |
| 4 | Tambah Bencana | Menambahkan data bencana |
| 5 | Ubah Relawan | Mengubah data relawan |
| 6 | Ubah Bencana | Mengubah data bencana |
| 7 | Hapus Relawan | Menghapus data relawan |
| 8 | Hapus Bencana | Menghapus data bencana |
| 9 | Tambah Penempatan | Menempatkan relawan pada bencana |
| 10 | Tampilkan Penempatan | Menampilkan data penempatan relawan |
| 11 | Keluar | Mengakhiri program |

## 4. Struktur Package

Struktur project disusun berdasarkan fungsi masing-masing class.

```text
Minpro-4-PBO-ManajemenRelawanBencana
├── database
│   └── db_relawan_bencana.sql
├── pom.xml
└── src/main/java
    └── com.mycompany.minpro.pbo.manajemenrelawanbencana
        ├── Main.java
        ├── config
        │   └── DatabaseConnection.java
        ├── interfaces
        │   └── DapatDitugaskan.java
        ├── model
        │   ├── Relawan.java
        │   ├── RelawanUmum.java
        │   ├── RelawanMedis.java
        │   ├── RelawanLogistik.java
        │   ├── Bencana.java
        │   └── Penempatan.java
        ├── dao
        │   ├── RelawanDAO.java
        │   ├── BencanaDAO.java
        │   ├── PenempatanDAO.java
        │   └── impl
        │       ├── RelawanDAOImpl.java
        │       ├── BencanaDAOImpl.java
        │       └── PenempatanDAOImpl.java
        ├── controller
        │   └── ManajemenRelawan.java
        └── view
            └── Menu.java
```

### Fungsi Setiap Package

- **config:** mengatur koneksi Java dengan database MySQL.
- **interfaces:** berisi kontrak fungsi yang harus diterapkan oleh class terkait.
- **model:** berisi class yang mewakili data relawan, bencana, dan penempatan.
- **dao:** berisi interface untuk menentukan operasi akses data.
- **dao.impl:** berisi implementasi operasi database menggunakan JDBC.
- **controller:** mengatur proses dan logika pengelolaan data.
- **view:** menampilkan menu program kepada pengguna.
- **Main.java:** menjadi titik awal program dijalankan.

## 5. Dokumentasi Project

### Struktur Project di NetBeans

Screenshot berikut menunjukkan struktur project, class Java, DAO, model, dan menu yang digunakan dalam program.

![Struktur project Minpro 4 di NetBeans](docs/screenshots/struktur-project.png)

*Gambar 1. Struktur project Minpro 4 di NetBeans.*

Catatan: simpan screenshot NetBeans pada folder `docs/screenshots/` dengan nama `struktur-project.png` agar gambar tampil di README GitHub.

### Hasil Program

Program dijalankan melalui console dan menampilkan menu untuk mengelola data relawan, bencana, serta penempatan.

Untuk melengkapi dokumentasi, tambahkan screenshot hasil program berjalan pada bagian ini.

![Hasil program](docs/screenshots/hasil-program.png)

*Gambar 2. Tampilan menu program saat dijalankan.*

Screenshot hasil program perlu disimpan dengan nama `hasil-program.png` di folder yang sama.

## 6. Penerapan Konsep OOP

### a. Encapsulation

Encapsulation diterapkan dengan menggunakan atribut bersifat `private` dan menyediakan getter serta setter untuk mengakses atau mengubah nilainya.

Contohnya, atribut pada class Relawan tidak diakses secara langsung dari luar class.

### b. Inheritance

Inheritance diterapkan melalui class RelawanUmum, RelawanMedis, dan RelawanLogistik yang mewarisi class Relawan.

Dengan inheritance, atribut dan perilaku umum relawan dapat digunakan kembali oleh class turunannya.

### c. Polymorphism

Polymorphism diterapkan melalui overriding method seperti `getJenis()` dan `getTugasUtama()`. Setiap jenis relawan dapat memberikan implementasi sesuai karakteristiknya.

Program juga menggunakan overloading pada method `tampilkanInfo()` dengan parameter yang berbeda.

### d. Abstraction

Abstraction diterapkan melalui abstract class Relawan dan interface DapatDitugaskan. Konsep ini membantu menyembunyikan detail implementasi serta menetapkan fungsi yang perlu dimiliki oleh class terkait.

## 7. Penerapan JDBC

JDBC (Java Database Connectivity) digunakan untuk menghubungkan program Java dengan database MySQL.

Koneksi database diatur melalui class DatabaseConnection.java menggunakan DriverManager.

Program menggunakan PreparedStatement untuk menjalankan query SQL dengan parameter. Cara ini membantu mengurangi risiko SQL Injection ketika memproses input pengguna.

Koneksi dan statement dikelola menggunakan try-with-resources agar resource ditutup secara otomatis setelah digunakan.

## 8. Penerapan DAO

DAO (Data Access Object) digunakan untuk memisahkan proses akses database dari logika program.

Setiap entitas memiliki interface DAO dan class implementasi masing-masing.

| Operasi | Perintah SQL | Kegunaan |
|---|---|---|
| Create | INSERT | Menambahkan data |
| Read | SELECT | Membaca data |
| Update | UPDATE | Mengubah data |
| Delete | DELETE | Menghapus data |

Controller memanggil interface DAO, sedangkan implementasi DAO menjalankan query SQL melalui JDBC.

Pemisahan ini membuat kode program lebih terstruktur dan lebih mudah dikembangkan.

## 9. Struktur Database

Database yang digunakan bernama `db_relawan_bencana` dan memiliki tiga tabel utama.

| Tabel | Fungsi |
|---|---|
| relawan | Menyimpan identitas dan jenis relawan |
| bencana | Menyimpan informasi bencana |
| penempatan | Menyimpan penugasan relawan pada bencana |

Tabel penempatan menggunakan foreign key yang menghubungkan data relawan dan bencana. Kombinasi relawan dan bencana juga dibatasi agar tidak terjadi penempatan ganda untuk pasangan data yang sama.

Data awal dimasukkan melalui file `database/db_relawan_bencana.sql`, yang berisi perintah pembuatan tabel dan penambahan data awal.

Data awal terdiri dari 3 relawan, 2 bencana, dan 2 penempatan.

## 10. Alur Kerja Program

Alur kerja program dimulai ketika pengguna menjalankan Main.java.

1. Program menampilkan menu melalui Menu.java.
2. Pengguna memilih menu yang tersedia.
3. Main membaca input dan melakukan validasi.
4. Controller memproses permintaan pengguna.
5. DAO meneruskan operasi akses data ke implementasinya.
6. Implementasi DAO menjalankan query melalui JDBC.
7. Database MySQL menyimpan atau mengembalikan data.
8. Hasil operasi ditampilkan kepada pengguna.

Alur singkat:

```text
Main
  ↓
Controller
  ↓
DAO Interface
  ↓
DAO Implementation
  ↓
JDBC
  ↓
MySQL Database
```

## 11. Nilai Tambah Program

Program ini menggunakan JDBC murni dan pola DAO tanpa framework ORM.

Nilai tambah yang diterapkan meliputi:
- Pemisahan fungsi setiap class dan package.
- Penggunaan PreparedStatement untuk query database.
- Penggunaan foreign key untuk menjaga hubungan antar tabel.
- Validasi data melalui controller.
- Penggunaan try-with-resources untuk pengelolaan resource database.
- Penyimpanan data permanen pada MySQL.

## 12. Teknologi yang Digunakan

- Java
- Object-Oriented Programming (OOP)
- JDBC
- MySQL
- Maven
- NetBeans IDE
- Git dan GitHub

## 13. Cara Menjalankan Program

1. Pastikan Java JDK dan NetBeans sudah terpasang.
2. Jalankan MySQL melalui XAMPP atau layanan MySQL yang digunakan.
3. Buka phpMyAdmin.
4. Import file `database/db_relawan_bencana.sql`.
5. Pastikan database `db_relawan_bencana` berhasil dibuat.
6. Periksa URL, username, dan password pada DatabaseConnection.java.
7. Buka project melalui NetBeans.
8. Tunggu Maven menyelesaikan pengunduhan dependensi.
9. Jalankan Main.java.
10. Pilih menu yang ingin digunakan.

Jika koneksi gagal, periksa status MySQL, nama database, port, username, password, dan dependensi MySQL Connector/J.

## 14. Kesimpulan

Mini Project 4 Sistem Manajemen Relawan Bencana merupakan pengembangan aplikasi Java berbasis console yang menerapkan konsep OOP dan integrasi database MySQL menggunakan JDBC.

Program menyediakan fitur untuk mengelola data relawan, bencana, dan penempatan. Penerapan encapsulation, inheritance, polymorphism, abstraction, serta pola DAO membantu membuat program lebih terstruktur.

Dengan database MySQL, data dapat disimpan secara permanen sehingga dapat diakses kembali saat program dijalankan.

---

**Mini Project 4 – Pemrograman Berorientasi Objek**  
Meilanie | 2509116109 | Kelas C