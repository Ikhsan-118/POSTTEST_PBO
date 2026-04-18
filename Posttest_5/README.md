# MelodyHub — Posttest 5
Pemrograman Berorientasi Objek | Informatika UNMUL

---

## Struktur File

```
src/melodyhub/
├── IPembayaran.java    Interface (baru)
├── Paket.java          Abstract Class (diubah dari Posttest 4)
├── PaketReguler.java   Subclass + implements IPembayaran
├── PaketPremium.java   Subclass + implements IPembayaran
├── PaketFamily.java    Subclass + implements IPembayaran
├── Transaksi.java      Tidak berubah
├── User.java           Tidak berubah
└── Main.java           Entry point
```

---

## Konsep yang Diterapkan (Modul 6 — Abstraction)

### Abstract Class — `Paket.java`

`Paket` diubah menjadi abstract class sehingga tidak bisa dibuat objeknya secara langsung. Subclass wajib mengimplementasi tiga abstract method berikut:

| Abstract Method | Keterangan |
|---|---|
| `getKategori()` | Mengembalikan jenis paket (Reguler / Premium / Family) |
| `getInfoTambahan()` | Mengembalikan detail spesifik tiap paket |
| `hitungTotal(int bulan)` | Kalkulasi harga berbeda di tiap subclass |

### Interface — `IPembayaran.java`

Berisi dua method yang wajib diimplementasi oleh semua subclass paket:

| Method | Keterangan |
|---|---|
| `validasiPembayaran(int durasiDibeli)` | Memvalidasi syarat sebelum transaksi diproses |
| `cetakStruk(String, int, double)` | Mencetak bukti pembelian sesuai tipe paket |

---

## Konsep dari Posttest Sebelumnya (tetap ada)

- **Encapsulation** — atribut `private` + getter/setter
- **Inheritance** — semua paket extends `Paket`
- **Polymorphism** — override `tampilkanInfo()` dan `hitungTotal()`, overload `tampil()` di `Transaksi`

---

## Cara Menjalankan

```bash
javac -d out src/melodyhub/*.java
java -cp out melodyhub.Main
```

---

*Posttest 5 — Informatika UNMUL*