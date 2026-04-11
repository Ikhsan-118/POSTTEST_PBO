# 🎵 MelodyHub — Posttest 4
**Pemrograman Berorientasi Objek | Informatika UNMUL**

> Kelanjutan dari Posttest 3 dengan penerapan konsep **Polymorphism** sesuai Modul 5.

---

## 📁 Struktur Project

```
Posttest_4/
└── src/
    └── melodyhub/
        ├── Main.java           ← Entry point & menu utama
        ├── Paket.java          ← Parent class + Overloading hitungTotal()
        ├── PaketReguler.java   ← Subclass + Override tampilkanInfo() & hitungTotal()
        ├── PaketPremium.java   ← Subclass + Override tampilkanInfo() & hitungTotal()
        ├── PaketFamily.java    ← Subclass + Override tampilkanInfo() & hitungTotal()
        ├── Transaksi.java      ← Overloading tampil()
        └── User.java           ← Data user
```

---

## ✅ Kesesuaian dengan Modul 5 — Polymorphism

### Pengertian (dari Modul)
> *"Polimorfisme dalam OOP merupakan sebuah konsep OOP di mana class memiliki banyak 'bentuk' method yang berbeda, meskipun namanya sama."*

Konsep ini **sudah diterapkan sepenuhnya** dalam project ini melalui dua jenis polimorfisme:

---

## 🔁 1. METHOD OVERRIDING — Dynamic Polymorphism

> *"Polimorfisme dinamis menggunakan method overriding."* — Modul 5

### ✔️ Aturan yang dipenuhi (sesuai Modul):
| Aturan | Status |
|---|---|
| Nama method sama dengan method yang di-overwrite | ✅ |
| Parameter harus sama | ✅ |
| Return type harus sama | ✅ |
| Bukan method final | ✅ |
| Access modifier subclass tidak lebih ketat dari superclass | ✅ |
| Menggunakan kata kunci `@Override` | ✅ |

---

### 🔹 Override #1 — `tampilkanInfo()`

Didefinisikan di `Paket` (parent), lalu di-override di **setiap subclass**:

```java
// Paket.java (parent)
public void tampilkanInfo() {
    System.out.println("Nama Paket : " + namaPaket);
    System.out.println("Harga      : Rp " + harga);
    System.out.println("Durasi     : " + durasiBulan + " Bulan");
}

// PaketReguler.java — menambahkan info deskripsi
@Override
public void tampilkanInfo() {
    super.tampilkanInfo();
    System.out.println("Keterangan : " + deskripsi);
}

// PaketPremium.java — menambahkan fitur eksklusif
@Override
public void tampilkanInfo() {
    super.tampilkanInfo();
    System.out.println("Fitur Eksklusif: " + fiturUnggulan);
}

// PaketFamily.java — menambahkan jumlah akun
@Override
public void tampilkanInfo() {
    super.tampilkanInfo();
    System.out.println("Max Anggota: " + jumlahAnggota + " Akun");
}
```

**Logika:** Setiap jenis paket memiliki informasi tambahan yang berbeda-beda — ini wajar dan realistis karena detail tiap produk berbeda.

---

### 🔹 Override #2 — `hitungTotal(int bulan)`

Tiap tipe paket punya **aturan harga yang berbeda**, sehingga override di sini sangat logis:

```java
// PaketReguler — diskon 5% otomatis jika berlangganan >= 6 bulan
@Override
public double hitungTotal(int bulan) {
    double total = harga * bulan;
    if (bulan >= 6) total = total * 0.95;
    return total;
}

// PaketPremium — ada biaya aktivasi Rp25.000, gratis 1 bulan jika >= 3 bulan
@Override
public double hitungTotal(int bulan) {
    double total = harga * bulan + 25000;
    if (bulan >= 3) total = total - harga;
    return total;
}

// PaketFamily — diskon 10% per anggota di atas 2, maksimal 40%
@Override
public double hitungTotal(int bulan) {
    double total = harga * bulan;
    if (jumlahAnggota > 2) {
        double diskon = 0.10 * (jumlahAnggota - 2);
        if (diskon > 0.40) diskon = 0.40;
        total = total * (1 - diskon);
    }
    return total;
}
```

**Logika:** Sama seperti layanan streaming nyata — harga final berbeda tergantung jenis paket, durasi, dan jumlah pengguna.

---

## 🔃 2. METHOD OVERLOADING — Static Polymorphism

> *"Polimorfisme statis menggunakan method overloading."* — Modul 5

### ✔️ Aturan yang dipenuhi (sesuai Modul):
| Aturan | Status |
|---|---|
| Nama method sama | ✅ |
| Setiap method memiliki parameter berbeda | ✅ |
| Urutan/tipe data parameter berbeda tiap method | ✅ |
| Return type sama | ✅ |

---

### 🔹 Overloading #1 — `hitungTotal()` di `Paket.java`

Tiga versi `hitungTotal` dengan parameter berbeda:

```java
// Tanpa parameter — pakai durasi default paket
public double hitungTotal() {
    return harga * durasiBulan;
}

// Satu parameter — durasi custom (int)
public double hitungTotal(int bulan) {
    return harga * bulan;
}

// Dua parameter — durasi custom + diskon persen (int, double)
public double hitungTotal(int bulan, double diskonPersen) {
    double total = harga * bulan;
    double potongan = total * (diskonPersen / 100);
    return total - potongan;
}
```

**Logika:** Menghitung tagihan bisa dilakukan dengan beberapa skenario — pakai durasi default, durasi custom, atau dengan diskon promo. Ini sangat umum terjadi di sistem billing nyata.

---

### 🔹 Overloading #2 — `tampil()` di `Transaksi.java`

Tiga versi `tampil()` untuk kebutuhan tampilan berbeda:

```java
// Tampil ringkas
void tampil() { ... }

// Tampil dengan tanggal transaksi
void tampil(boolean tampilTanggal) { ... }

// Tampil dengan catatan/keterangan tambahan
void tampil(String catatan) { ... }
```

**Logika:** Bukti transaksi bisa ditampilkan dalam format berbeda — ringkasan singkat, dengan tanggal, atau dengan keterangan status. Ini wajar untuk sistem yang punya kebutuhan output berbeda di tiap konteks.

---

## 🆕 Fitur Baru di Posttest 4

| # | Fitur | Keterangan |
|---|---|---|
| Menu 5 | **Beli Paket** | Simulasi transaksi, kalkulasi total via `hitungTotal()` yang di-override |
| Menu 6 | **Riwayat Transaksi** | Tampil transaksi dengan overloading `tampil()` |
| Menu 7 | **Estimasi Biaya** | Perbandingan harga normal vs diskon via overloading `hitungTotal()` |
| Update | **Update Paket** | Diperbarui agar mempertahankan tipe subclass saat update |

---

## 🗂️ Ringkasan Polymorphism

| Jenis | Method | Class | Keterangan |
|---|---|---|---|
| **Override** | `tampilkanInfo()` | PaketReguler, PaketPremium, PaketFamily | Tampilan info berbeda per tipe paket |
| **Override** | `hitungTotal(int)` | PaketReguler, PaketPremium, PaketFamily | Kalkulasi harga berbeda per tipe paket |
| **Overload** | `hitungTotal()` | Paket | 3 versi: tanpa param, 1 param, 2 param |
| **Overload** | `tampil()` | Transaksi | 3 versi: ringkas, +tanggal, +catatan |

Total: **2 method Override × 3 subclass = 6 override** | **3 + 3 = 6 overload**

---

## ▶️ Cara Menjalankan

```bash
# Compile
javac -d out src/melodyhub/*.java

# Jalankan
java -cp out melodyhub.Main
```

---

*Posttest 4 — Pemrograman Berorientasi Objek | Informatika UNMUL*