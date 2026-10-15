# Minpro-4-PBO-ManajemenRelawanBencana

## Sistem Manajemen Relawan Bencana
Aplikasi console Java dengan koneksi database MySQL (JDBC, Prepared Statement, dan pola DAO)

| | |
|---|---|
| Nama | Meilanie |
| NIM | 2509116109 |
| Kelas | C |
| Mata Kuliah | Pemrograman Berorientasi Objek |
| Tugas | Mini Project 4 (lanjutan Mini Project 3) |

---

## Daftar Isi
1. [Deskripsi Singkat Program](#1-deskripsi-singkat-program)
2. [Penjelasan Struktur Package](#2-penjelasan-struktur-package)
3. [Penjelasan Alur Program](#3-penjelasan-alur-program)
4. [Penerapan Encapsulation dan Inheritance](#4-penerapan-encapsulation-dan-inheritance)
5. [Penerapan Polymorphism dan Abstraction](#5-penerapan-polymorphism-dan-abstraction)
6. [Penerapan JDBC](#6-penerapan-jdbc)
7. [Letak Penerapan Nilai Tambah](#7-letak-penerapan-nilai-tambah)
8. [Cara Menjalankan Program](#8-cara-menjalankan-program)

---

## 1. Deskripsi Singkat Program

**Sistem Manajemen Relawan Bencana** adalah aplikasi berbasis console untuk mengelola data relawan, data bencana, dan penempatan relawan pada suatu bencana. Relawan dibedakan menjadi tiga jenis, yaitu **Relawan Umum**, **Relawan Medis**, dan **Relawan Logistik**.

Pada **Mini Project 3**, seluruh data disimpan sementara di `ArrayList` sehingga hilang setiap program ditutup. Pada **Mini Project 4** ini, program dikembangkan agar:

- terhubung ke database **MySQL** menggunakan **JDBC** dan **Prepared Statement**,
- memakai pola **DAO (Data Access Object)** untuk memisahkan logika program dari akses database,
- memakai **data awal (dummy data) yang diisi lewat file `.sql`**, bukan lewat kode Java, sehingga fitur *Read* langsung menampilkan data tanpa input dari awal.

### Fitur Program

| No | Menu | Jenis Operasi |
|----|------|---------------|
| 1 | Tampilkan Relawan | Read |
| 2 | Tampilkan Bencana | Read |
| 3 | Tambah Relawan | Create |
| 4 | Tambah Bencana | Create |
| 5 | Ubah Relawan | Update |
| 6 | Ubah Bencana | Update |
| 7 | Hapus Relawan | Delete |
| 8 | Hapus Bencana | Delete |
| 9 | Tambah Penempatan | Create |
| 10 | Tampilkan Penempatan | Read |
| 11 | Keluar | - |

### Validasi Input
- Pilihan menu harus angka 1 sampai 11, selain itu tampil pesan *"Input salah, silakan input ulang sesuai ketentuan (1-11)"*.
- ID relawan harus berformat `R` + 3 angka (contoh `R004`), ID bencana berformat `B` + 3 angka (contoh `B003`).
- Nomor HP harus berupa angka 10 sampai 13 digit.
- Nama, alamat, keahlian, dan data bencana tidak boleh kosong.
- ID tidak boleh sama dengan ID yang sudah ada di database.
- Satu relawan tidak boleh ditempatkan dua kali pada bencana yang sama.

### Data Awal di Database
Data awal berada di file `database/db_relawan_bencana.sql`:

| Tabel | Data Awal |
|-------|-----------|
| `relawan` | R001 Andi (Umum), R002 dr. Siti Aminah (Medis), R003 Andi Pratama (Logistik) |
| `bencana` | B001 Banjir Samarinda, B002 Longsor Balikpapan |
| `penempatan` | R001 di B001, R002 di B001 |

---

## 2. Penjelasan Struktur Package

```
Minpro-4-PBO-ManajemenRelawanBencana
├── database
│   └── db_relawan_bencana.sql
├── pom.xml
├── README.md
└── src/main/java/com/mycompany/minpro/pbo/manajemenrelawanbencana
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
    │   └── PenempatanDAO.java
    ├── dao.impl
    │   ├── RelawanDAOImpl.java
    │   ├── BencanaDAOImpl.java
    │   └── PenempatanDAOImpl.java
    ├── controller
    │   └── ManajemenRelawan.java
    └── view
        └── Menu.java
```

### Fungsi Tiap Package

| Package | Isi | Fungsi |
|---------|-----|--------|
| *(utama)* | `Main` | Titik awal program, membaca input pengguna, dan memanggil controller |
| `config` | `DatabaseConnection` | Membuat koneksi JDBC ke database MySQL |
| `interfaces` | `DapatDitugaskan` | Interface yang mewajibkan method `getTugasUtama()` |
| `model` | `Relawan`, `RelawanUmum`, `RelawanMedis`, `RelawanLogistik`, `Bencana`, `Penempatan` | Class entitas / data |
| `dao` | `RelawanDAO`, `BencanaDAO`, `PenempatanDAO` | **Interface** DAO yang berisi daftar operasi data (kontrak) |
| `dao.impl` | `RelawanDAOImpl`, `BencanaDAOImpl`, `PenempatanDAOImpl` | **Implementasi** DAO yang berisi kode JDBC dan Prepared Statement |
| `controller` | `ManajemenRelawan` | Logika program (cek ID ganda, cek penempatan ganda, pesan hasil) |
| `view` | `Menu` | Tampilan menu |

### Struktur Tabel Database

```
relawan                         bencana                       penempatan
-----------------------        ---------------------------   --------------------------
id (PK)                        id_bencana (PK)               id_penempatan (PK, auto)
nama                           nama_bencana                  id_relawan (FK -> relawan.id)
alamat                         lokasi                        id_bencana (FK -> bencana.id_bencana)
no_hp                          jenis_bencana                 UNIQUE (id_relawan, id_bencana)
keahlian                       status
jenis (UMUM/MEDIS/LOGISTIK)
spesialisasi (khusus medis)
jenis_logistik (khusus logistik)
```

Ketiga jenis relawan disimpan dalam **satu tabel `relawan`**. Kolom `jenis` menentukan class mana yang dibuat saat data dibaca. Tabel `penempatan` memakai *foreign key* dengan `ON DELETE CASCADE`, sehingga ketika relawan atau bencana dihapus, data penempatannya ikut terhapus otomatis.

---

## 3. Penjelasan Alur Program

1. Program dijalankan dari `Main.main()`. Objek `ManajemenRelawan` dibuat dan di dalamnya dibuat objek `RelawanDAOImpl`, `BencanaDAOImpl`, dan `PenempatanDAOImpl`. **Tidak ada data yang ditulis di kode**, semua berasal dari database.
2. `Menu.tampilkanMenu()` menampilkan 11 pilihan menu.
3. `bacaPilihan()` membaca input. Jika bukan angka, nilainya dianggap tidak valid dan menu menampilkan pesan kesalahan.
4. `prosesMenu()` memilih tindakan berdasarkan angka yang dimasukkan (`switch-case`).
5. Untuk menu tambah dan ubah, `Main` membaca dan memvalidasi input, membuat objek model (`RelawanUmum`, `RelawanMedis`, `RelawanLogistik`, atau `Bencana`), lalu mengirimkannya ke controller.
6. `ManajemenRelawan` (controller) menjalankan aturan program, misalnya mengecek apakah ID sudah ada, kemudian memanggil method DAO.
7. Class DAO Impl membuka koneksi lewat `DatabaseConnection`, menyiapkan `PreparedStatement`, mengeksekusinya, lalu mengubah `ResultSet` menjadi objek model.
8. Hasil ditampilkan di console. Jika terjadi kesalahan database (`SQLException`), pesan kesalahan ditangkap di controller dan ditampilkan tanpa menghentikan program.
9. Perulangan `do-while` berjalan sampai pengguna memilih menu **11 (Keluar)**.

### Diagram Alur Lapisan

```
Pengguna
   |
   v
Main  (input & validasi)
   |
   v
ManajemenRelawan  (controller / aturan program)
   |
   v
RelawanDAO / BencanaDAO / PenempatanDAO   (interface)
   |
   v
RelawanDAOImpl / BencanaDAOImpl / PenempatanDAOImpl  (JDBC + PreparedStatement)
   |
   v
DatabaseConnection  -->  MySQL (db_relawan_bencana)
```

### Contoh Alur: Menu 1 (Tampilkan Relawan)
`Main.prosesMenu(1)` -> `ManajemenRelawan.tampilkanRelawan()` -> `RelawanDAO.findAll()` -> `RelawanDAOImpl` menjalankan `SELECT * FROM relawan ORDER BY id` -> tiap baris diubah menjadi objek `RelawanUmum`/`RelawanMedis`/`RelawanLogistik` -> controller memanggil `tampilkanInfo()` pada tiap objek.

### Contoh Alur: Menu 9 (Tambah Penempatan)
`Main.tambahPenempatan()` membaca ID relawan dan ID bencana -> `ManajemenRelawan.tambahPenempatan()` mengecek relawan ada, bencana ada, dan penempatan belum pernah dibuat -> `PenempatanDAO.insert()` menjalankan `INSERT INTO penempatan (id_relawan, id_bencana) VALUES (?, ?)`.

---

## 4. Penerapan Encapsulation dan Inheritance

### Encapsulation
Encapsulation diterapkan dengan menjadikan atribut bersifat **`private`** dan mengaksesnya melalui **getter dan setter** yang `public`.

**Letak penerapan:**
- `model/Relawan.java`: atribut `id`, `nama`, `alamat`, `noHp`, `keahlian`
- `model/RelawanMedis.java`: atribut `spesialisasi`
- `model/RelawanLogistik.java`: atribut `jenisLogistik`
- `model/Bencana.java`: atribut `idBencana`, `namaBencana`, `lokasi`, `jenisBencana`, `status`
- `model/Penempatan.java`: atribut `relawan`, `bencana`
- `controller/ManajemenRelawan.java`: atribut `relawanDAO`, `bencanaDAO`, `penempatanDAO` juga `private`

```java
public abstract class Relawan implements DapatDitugaskan {
    private String id;
    private String nama;
    // ...
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
}
```

**Manfaatnya:** data tidak dapat diubah sembarangan dari luar class. Pada fitur ubah data, `Main` mengubah data lewat setter, misalnya `relawan.setNama(nama)`, bukan mengakses atribut langsung.

### Inheritance
Inheritance diterapkan pada class relawan. `Relawan` adalah **superclass**, sedangkan `RelawanUmum`, `RelawanMedis`, dan `RelawanLogistik` adalah **subclass** yang memakai kata kunci `extends`.

```
              Relawan (abstract)
            /         |          \
   RelawanUmum   RelawanMedis   RelawanLogistik
```

```java
public class RelawanMedis extends Relawan {
    private String spesialisasi;

    public RelawanMedis(String id, String nama, String alamat,
                        String noHp, String keahlian, String spesialisasi) {
        super(id, nama, alamat, noHp, keahlian);
        this.spesialisasi = spesialisasi;
    }
}
```

**Penjelasan:**
- Atribut umum (`id`, `nama`, `alamat`, `noHp`, `keahlian`) dan method `tampilkanInfo()` ditulis **sekali** di `Relawan`, lalu diwariskan ke semua subclass.
- Subclass hanya menambah atribut khusus: `spesialisasi` pada `RelawanMedis` dan `jenisLogistik` pada `RelawanLogistik`.
- Konstruktor subclass memanggil `super(...)` untuk mengisi atribut milik superclass.

---

## 5. Penerapan Polymorphism dan Abstraction

### Abstraction
Abstraction menyembunyikan detail dan hanya menampilkan apa yang perlu diketahui. Diterapkan di empat tempat:

**1. Abstract class `Relawan`** (`model/Relawan.java`)
`Relawan` dideklarasikan `abstract` sehingga tidak bisa dibuat objeknya secara langsung. Class ini punya abstract method `getJenis()` yang **wajib** diisi oleh setiap subclass.

```java
public abstract String getJenis();
```

**2. Interface `DapatDitugaskan`** (`interfaces/DapatDitugaskan.java`)
Interface ini berisi method `getTugasUtama()`. `Relawan` mengimplementasikannya, sehingga setiap subclass wajib menentukan tugas utamanya sendiri.

```java
public interface DapatDitugaskan {
    String getTugasUtama();
}
```

**3. Interface DAO** (`dao/RelawanDAO`, `BencanaDAO`, `PenempatanDAO`)
Interface hanya mendeklarasikan *apa* yang bisa dilakukan (`insert`, `findAll`, `findById`, `update`, `delete`) tanpa menjelaskan *bagaimana*. Controller hanya mengenal interface ini dan tidak perlu tahu bahwa di baliknya dipakai JDBC.

**4. Pemisahan lapisan**
`Main` tidak pernah menulis SQL. Semua detail database tersembunyi di dalam class DAO Impl.

### Polymorphism

**1. Method Overriding**
Setiap subclass menulis ulang method dari superclass atau interface dengan isi yang berbeda.

| Method | RelawanUmum | RelawanMedis | RelawanLogistik |
|--------|-------------|--------------|-----------------|
| `getJenis()` | "Relawan Umum" | "Relawan Medis" | "Relawan Logistik" |
| `getTugasUtama()` | Evakuasi dan pendataan warga | Pertolongan pertama dan perawatan korban | Distribusi bantuan dan pengelolaan gudang |
| `tampilkanInfo()` | memakai milik `Relawan` | menambah spesialisasi | menambah jenis logistik |

**2. Method Overloading**
Di `Relawan` terdapat dua method bernama sama dengan parameter berbeda:

```java
public void tampilkanInfo()                    // tampil lengkap
public void tampilkanInfo(boolean ringkas)     // tampil ringkas atau lengkap
```

**3. Dynamic Binding / Upcasting**
`RelawanDAOImpl` membaca kolom `jenis` dari database, lalu membuat objek subclass yang sesuai dan mengembalikannya sebagai tipe `Relawan`:

```java
switch (rs.getString("jenis")) {
    case "MEDIS":    return new RelawanMedis(...);
    case "LOGISTIK": return new RelawanLogistik(...);
    default:         return new RelawanUmum(...);
}
```

Pada `ManajemenRelawan.tampilkanRelawan()`, semua objek diperlakukan sebagai `Relawan` dan dipanggil dengan satu perintah yang sama:

```java
for (Relawan relawan : daftar) {
    relawan.tampilkanInfo();
}
```

Walaupun perintahnya sama, hasil yang tampil berbeda sesuai jenis objek sebenarnya (Medis menampilkan spesialisasi, Logistik menampilkan jenis logistik).

**4. Polymorphism pada DAO**
Variabel bertipe interface diisi objek implementasinya:

```java
private RelawanDAO relawanDAO = new RelawanDAOImpl();
```

Jika suatu saat database diganti, cukup membuat implementasi baru tanpa mengubah controller.

---

## 6. Penerapan JDBC

**JDBC (Java Database Connectivity)** adalah API Java untuk menghubungkan program dengan database. Program ini memakai driver **MySQL Connector/J** yang ditambahkan lewat `pom.xml`.

### 6.1 Dependency Driver (`pom.xml`)

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.4.0</version>
</dependency>
```

### 6.2 Koneksi Database (`config/DatabaseConnection.java`)

```java
private static final String URL = "jdbc:mysql://localhost:3306/db_relawan_bencana";
private static final String USER = "root";
private static final String PASSWORD = "";

public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
}
```

Class ini dipanggil oleh seluruh class DAO Impl setiap kali butuh koneksi.

### 6.3 Prepared Statement

Seluruh query memakai **`PreparedStatement`** dengan tanda tanya (`?`) sebagai placeholder, lalu nilainya diisi dengan `setString()`. Cara ini mencegah **SQL Injection** karena input pengguna tidak digabung langsung ke dalam teks query.

Contoh pada `BencanaDAOImpl.insert()`:

```java
String sql = "INSERT INTO bencana (id_bencana, nama_bencana, lokasi, jenis_bencana, status) "
           + "VALUES (?, ?, ?, ?, ?)";
try (Connection c = DatabaseConnection.getConnection();
     PreparedStatement ps = c.prepareStatement(sql)) {
    ps.setString(1, b.getIdBencana());
    ps.setString(2, b.getNamaBencana());
    ps.setString(3, b.getLokasi());
    ps.setString(4, b.getJenisBencana());
    ps.setString(5, b.getStatus());
    ps.executeUpdate();
}
```

### 6.4 Pola DAO dan Operasi CRUD

Setiap entitas memiliki **interface DAO** (package `dao`) dan **class implementasi** (package `dao.impl`).

| Operasi | Method | Query SQL | Dipanggil dari menu |
|---------|--------|-----------|---------------------|
| Create | `insert()` | `INSERT INTO ... VALUES (?, ...)` | 3, 4, 9 |
| Read (semua) | `findAll()` | `SELECT * FROM ... ORDER BY ...` | 1, 2, 10 |
| Read (satu) | `findById()` | `SELECT * FROM ... WHERE ... = ?` | dipakai untuk pengecekan, ubah, hapus |
| Update | `update()` | `UPDATE ... SET ... WHERE ... = ?` | 5, 6 |
| Delete | `delete()` | `DELETE FROM ... WHERE ... = ?` | 7, 8 |
| Cek penempatan | `exists()` | `SELECT 1 FROM penempatan WHERE id_relawan = ? AND id_bencana = ?` | 9 |

Pengubahan baris tabel menjadi objek dilakukan oleh method `mapRow()` di class DAO Impl, yang membaca `ResultSet` dengan `rs.getString("nama_kolom")`.

### 6.5 Penutupan Resource Otomatis
Seluruh `Connection`, `PreparedStatement`, dan `ResultSet` dibuka dengan **try-with-resources**, sehingga otomatis ditutup walaupun terjadi kesalahan.

### 6.6 Penanganan Kesalahan
Method DAO melempar `SQLException`. Controller menangkapnya dengan `try-catch` dan menampilkan pesan, misalnya *"Gagal membaca data relawan: ..."*, sehingga program tidak berhenti mendadak.

### 6.7 Data Awal Lewat File SQL
Data awal **tidak ditulis di kode Java**. Konstruktor `ManajemenRelawan` tidak lagi berisi `daftarRelawan.add(...)` seperti Mini Project 3. Data awal diisi lewat file `database/db_relawan_bencana.sql`:

```sql
INSERT INTO relawan (id, nama, alamat, no_hp, keahlian, jenis, spesialisasi, jenis_logistik) VALUES
('R001', 'Andi', 'Samarinda', '081234567890', 'Evakuasi', 'UMUM', NULL, NULL),
...

INSERT INTO bencana (id_bencana, nama_bencana, lokasi, jenis_bencana, status) VALUES
('B001', 'Banjir Samarinda', 'Samarinda', 'Banjir', 'Aktif'),
...
```

Setelah file di-import, menu **Tampilkan Relawan**, **Tampilkan Bencana**, dan **Tampilkan Penempatan** langsung menampilkan data tanpa input dari awal.

---

## 7. Letak Penerapan Nilai Tambah

Nilai tambah pada tugas ini adalah **implementasi framework ORM**. Pada project ini, **framework ORM tidak diterapkan**. Akses data dikerjakan dengan **JDBC murni (Prepared Statement) dan pola DAO** sesuai ketentuan utama tugas.

---

## 8. Cara Menjalankan Program

### Kebutuhan
- JDK 17 atau lebih baru
- NetBeans (dengan Maven)
- XAMPP (MySQL/MariaDB) atau MySQL Server

### Langkah
1. Jalankan **MySQL** (Start pada XAMPP).
2. Buka `http://localhost/phpmyadmin`, pilih menu **Import**, pilih file `database/db_relawan_bencana.sql`, lalu klik **Go**. Database `db_relawan_bencana` beserta tabel dan data awal akan terbuat.
3. Buka project di NetBeans, lalu klik kanan project dan pilih **Build with Dependencies** agar driver MySQL terunduh.
4. Sesuaikan `USER` dan `PASSWORD` di `config/DatabaseConnection.java` jika berbeda dengan pengaturan MySQL di komputer.
5. Jalankan `Main.java`.
6. Pilih menu **1** atau **2**. Data awal dari database langsung tampil.

### Teknologi
Java, Maven, MySQL, JDBC (MySQL Connector/J), NetBeans, Git, GitHub.
