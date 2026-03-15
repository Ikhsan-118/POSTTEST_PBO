```markdown
# 🎵 MelodyHub - Posttest 2

Aplikasi manajemen langganan musik berbasis Java (Console Application) yang dikembangkan dari Posttest 1.

---

## 📌 Konsep yang Diterapkan

### 1. Encapsulation
Semua atribut di setiap kelas dibuat **private** dan hanya dapat diakses melalui metode **getter** dan **setter** yang telah divalidasi. Hal ini bertujuan untuk melindungi data dari akses atau modifikasi yang tidak sah dari luar kelas.

### 2. Access Modifier (4 Jenis)

| Modifier | Digunakan Pada | Contoh |
|---|---|---|
| `private` | Atribut sensitif yang tidak boleh diakses langsung | `id`, `password`, `saldo` di `User.java` |
| `protected` | Atribut yang dapat diwarisi subclass dalam package | `nama`, `email` di `User.java`; `status` di `Transaksi.java` |
| `public` | Metode akses umum & konstanta global | Semua getter/setter, `totalUser`, `STATUS_SUKSES` |
| `default` (package-private) | Konstanta & method yang hanya dipakai antar kelas satu package | `ROLE_DEFAULT`, `totalPaket`, `kurangiSaldo()` |

### 3. Getter & Setter
Setiap atribut `private` dilengkapi getter dan setter dengan validasi input:
- Setter melempar `IllegalArgumentException` jika input tidak valid
- Atribut `idTransaksi` hanya memiliki getter karena bersifat **immutable** (tidak boleh diubah setelah dibuat)
- `password` tidak memiliki getter demi keamanan, diganti metode `verifikasiPassword()`

### 4. External Library via Maven (Poin Plus)
Menggunakan **Apache Commons Lang 3** (`org.apache.commons:commons-lang3:3.14.0`) untuk `StringUtils.isBlank()` dalam validasi input pengguna di `Main.java`.

---

## 🗂 Struktur Project

```
Posttest2/
├── pom.xml                          ← Konfigurasi build Maven + dependency
├── README.md
└── src/
    └── main/
        └── java/
            └── melodyhub/
                ├── Main.java        ← Entry point + semua menu & logika UI
                ├── User.java        ← Kelas User (private, protected, public, default)
                ├── Paket.java       ← Kelas Paket (private, public, default)
                └── Transaksi.java   ← Kelas Transaksi (private, protected, public, default)
```

---

## ⚙️ Cara Menjalankan

### Prasyarat
- Java JDK 17+
- Apache Maven 3.6+

### Langkah

```bash
# 1. Clone repository
git clone https://github.com/<username>/<repo>.git
cd <repo>/Posttest2

# 2. Build dengan Maven (otomatis download dependency)
mvn clean package

# 3. Jalankan aplikasi
java -jar target/Posttest2-1.0-SNAPSHOT.jar
```

---

## 🔧 Fitur Aplikasi

### 👤 Manajemen User
- Lihat semua user beserta saldo
- Tambah user baru (validasi ID duplikat, format email, panjang password)
- Update nama & email user
- Hapus user
- Top-up saldo

### 📦 Manajemen Paket
- Lihat semua paket langganan
- Tambah paket baru
- Update detail paket
- Hapus paket

### 💳 Transaksi
- Beli paket (saldo terpotong otomatis)
- Konfirmasi sebelum pembayaran
- Transaksi dicatat meskipun gagal (saldo tidak cukup)
- Lihat riwayat semua transaksi

---

## 🛡️ Error Handling

| Skenario | Penanganan |
|---|---|
| Input angka tidak valid | Loop ulang dengan pesan `[WARN]` |
| Input kosong | Ditolak dengan pesan `[WARN]` menggunakan `StringUtils.isBlank()` |
| ID / kode duplikat | Ditolak dengan pesan `[ERROR]` |
| Format email salah | `IllegalArgumentException` dari setter `setEmail()` |
| Password terlalu pendek (< 4 karakter) | `IllegalArgumentException` dari setter `setPassword()` |
| Saldo tidak cukup saat beli paket | `IllegalStateException`, transaksi dicatat dengan status `GAGAL` |
| Harga atau durasi bernilai negatif | `IllegalArgumentException` dari setter terkait |

---

## 🎯 Pemenuhan Kriteria Penilaian

| Kriteria | Status |
|---|---|
| Folder `Posttest2` baru berisi file proyek | ✅ |
| Copy & kembangkan dari Posttest 1 (MelodyHub) | ✅ |
| Encapsulation diterapkan | ✅ Semua atribut `private` dengan getter/setter |
| Minimal 2 dari 4 jenis Access Modifier | ✅ Semua 4 jenis diterapkan |
| Setter dan Getter | ✅ Lengkap disertai validasi |
| README | ✅ |
| **[POIN PLUS]** Semua 4 jenis access modifier | ✅ `private`, `protected`, `public`, `default` |
| **[POIN PLUS]** External library via Maven | ✅ Apache Commons Lang 3 |
```