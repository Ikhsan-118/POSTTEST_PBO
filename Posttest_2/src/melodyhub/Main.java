package melodyhub;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * MelodyHub - Aplikasi Manajemen Langganan Musik
 * Posttest 2 | Encapsulation & Access Modifier
 *
 * Fitur:
 *  - Manajemen User (Tambah, Lihat, Update, Hapus)
 *  - Manajemen Paket (Tambah, Lihat, Update, Hapus)
 *  - Transaksi Pembelian Paket
 *  - Top-up Saldo
 *  - Riwayat Transaksi
 */
public class Main {

    // -------------------------------------------------------
    // Data storage (package-private, dalam satu package)
    // -------------------------------------------------------
    static List<User>       daftarUser       = new ArrayList<>();
    static List<Paket>      daftarPaket      = new ArrayList<>();
    static List<Transaksi>  daftarTransaksi  = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);
    static int     counterTransaksi = 1;

    // -------------------------------------------------------
    // MAIN
    // -------------------------------------------------------
    public static void main(String[] args) {
        seedData();

        boolean running = true;
        while (running) {
            tampilMenuUtama();
            int pilihan = readInt("Pilih menu: ");
            switch (pilihan) {
                case 1 -> menuUser();
                case 2 -> menuPaket();
                case 3 -> menuTransaksi();
                case 0 -> {
                    System.out.println("\n[INFO] Terima kasih telah menggunakan MelodyHub. Sampai jumpa!");
                    running = false;
                }
                default -> System.out.println("[WARN] Pilihan tidak tersedia. Silakan coba lagi.");
            }
        }
        scanner.close();
    }

    // ============================================================
    // SEED DATA
    // ============================================================
    static void seedData() {
        daftarUser.add(new User("U001", "Alice", "alice@mail.com", "1234"));
        daftarUser.add(new User("U002", "Bob",   "bob@mail.com",   "5678"));

        daftarPaket.add(new Paket("P001", "Basic",    15000, 30,  "Streaming musik standar"));
        daftarPaket.add(new Paket("P002", "Standard", 30000, 30,  "Streaming + download offline"));
        daftarPaket.add(new Paket("P003", "Premium",  50000, 30,  "Semua fitur + kualitas lossless"));
        daftarPaket.add(new Paket("P004", "Family",   75000, 30,  "Hingga 6 akun Premium"));

        // Top-up awal
        daftarUser.get(0).topUpSaldo(100000);
        daftarUser.get(1).topUpSaldo(50000);
    }

    // ============================================================
    // MENU UTAMA
    // ============================================================
    static void tampilMenuUtama() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       🎵  MELODYHUB 🎵        ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  1. Manajemen User            ║");
        System.out.println("║  2. Manajemen Paket           ║");
        System.out.println("║  3. Transaksi                 ║");
        System.out.println("║  0. Keluar                    ║");
        System.out.println("╚══════════════════════════════╝");
    }

    // ============================================================
    // MENU USER
    // ============================================================
    static void menuUser() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- MANAJEMEN USER ---");
            System.out.println("1. Lihat Semua User");
            System.out.println("2. Tambah User");
            System.out.println("3. Update User");
            System.out.println("4. Hapus User");
            System.out.println("5. Top-up Saldo");
            System.out.println("0. Kembali");
            int pilihan = readInt("Pilih: ");
            switch (pilihan) {
                case 1 -> lihatSemuaUser();
                case 2 -> tambahUser();
                case 3 -> updateUser();
                case 4 -> hapusUser();
                case 5 -> topUpSaldo();
                case 0 -> back = true;
                default -> System.out.println("[WARN] Pilihan tidak valid.");
            }
        }
    }

    static void lihatSemuaUser() {
        if (daftarUser.isEmpty()) {
            System.out.println("[INFO] Belum ada data user.");
            return;
        }
        System.out.println("\n===== DAFTAR USER =====");
        System.out.printf("%-5s %-15s %-25s %s%n", "ID", "Nama", "Email", "Saldo");
        System.out.println("-".repeat(60));
        for (User u : daftarUser) {
            System.out.printf("%-5s %-15s %-25s Rp %.0f%n",
                    u.getId(), u.getNama(), u.getEmail(), u.getSaldo());
        }
        System.out.printf("[INFO] Total user terdaftar: %d%n", User.totalUser);
    }

    static void tambahUser() {
        System.out.println("\n--- Tambah User Baru ---");
        try {
            String id = readString("ID User     : ");
            if (findUser(id) != null) {
                System.out.println("[ERROR] ID user sudah digunakan.");
                return;
            }
            String nama     = readString("Nama        : ");
            String email    = readString("Email       : ");
            String password = readString("Password    : ");

            User user = new User(id, nama, email, password);
            daftarUser.add(user);
            System.out.println("[SUKSES] User '" + nama + "' berhasil ditambahkan.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static void updateUser() {
        System.out.println("\n--- Update User ---");
        lihatSemuaUser();
        String id = readString("\nMasukkan ID user yang ingin diupdate: ");
        User user = findUser(id);
        if (user == null) {
            System.out.println("[ERROR] User dengan ID '" + id + "' tidak ditemukan.");
            return;
        }
        try {
            System.out.println("[INFO] Kosongkan input untuk tidak mengubah nilai.");
            String namaBaru  = readString("Nama baru  [" + user.getNama()  + "]: ");
            String emailBaru = readString("Email baru [" + user.getEmail() + "]: ");

            if (!namaBaru.isEmpty())  user.setNama(namaBaru);
            if (!emailBaru.isEmpty()) user.setEmail(emailBaru);

            System.out.println("[SUKSES] Data user berhasil diupdate.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static void hapusUser() {
        System.out.println("\n--- Hapus User ---");
        lihatSemuaUser();
        String id = readString("\nMasukkan ID user yang ingin dihapus: ");
        User user = findUser(id);
        if (user == null) {
            System.out.println("[ERROR] User dengan ID '" + id + "' tidak ditemukan.");
            return;
        }
        daftarUser.remove(user);
        User.totalUser--;
        System.out.println("[SUKSES] User '" + user.getNama() + "' berhasil dihapus.");
    }

    static void topUpSaldo() {
        System.out.println("\n--- Top-up Saldo ---");
        lihatSemuaUser();
        String id = readString("\nMasukkan ID user: ");
        User user = findUser(id);
        if (user == null) {
            System.out.println("[ERROR] User tidak ditemukan.");
            return;
        }
        try {
            double jumlah = readDouble("Jumlah top-up (Rp): ");
            user.topUpSaldo(jumlah);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // ============================================================
    // MENU PAKET
    // ============================================================
    static void menuPaket() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- MANAJEMEN PAKET ---");
            System.out.println("1. Lihat Semua Paket");
            System.out.println("2. Tambah Paket");
            System.out.println("3. Update Paket");
            System.out.println("4. Hapus Paket");
            System.out.println("0. Kembali");
            int pilihan = readInt("Pilih: ");
            switch (pilihan) {
                case 1 -> lihatSemuaPaket();
                case 2 -> tambahPaket();
                case 3 -> updatePaket();
                case 4 -> hapusPaket();
                case 0 -> back = true;
                default -> System.out.println("[WARN] Pilihan tidak valid.");
            }
        }
    }

    static void lihatSemuaPaket() {
        if (daftarPaket.isEmpty()) {
            System.out.println("[INFO] Belum ada data paket.");
            return;
        }
        System.out.println("\n===== DAFTAR PAKET =====");
        System.out.printf("%-6s %-20s %-12s %-8s %s%n", "Kode", "Nama", "Harga", "Durasi", "Deskripsi");
        System.out.println("-".repeat(75));
        for (Paket p : daftarPaket) {
            System.out.printf("%-6s %-20s Rp %-9.0f %-8s %s%n",
                    p.getKodePaket(), p.getNamaPaket(), p.getHarga(),
                    p.getDurasiHari() + " hr", p.getDeskripsi());
        }
        System.out.printf("[INFO] Total paket tersedia: %d%n", Paket.totalPaket);
    }

    static void tambahPaket() {
        System.out.println("\n--- Tambah Paket Baru ---");
        try {
            String kode = readString("Kode Paket  : ");
            if (findPaket(kode) != null) {
                System.out.println("[ERROR] Kode paket sudah digunakan.");
                return;
            }
            String nama   = readString("Nama Paket  : ");
            double harga  = readDouble("Harga (Rp)  : ");
            int durasi    = readInt("Durasi (hr) : ");
            String desk   = readString("Deskripsi   : ");

            Paket paket = new Paket(kode, nama, harga, durasi, desk);
            daftarPaket.add(paket);
            System.out.println("[SUKSES] Paket '" + nama + "' berhasil ditambahkan.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static void updatePaket() {
        System.out.println("\n--- Update Paket ---");
        lihatSemuaPaket();
        String kode = readString("\nMasukkan kode paket yang ingin diupdate: ");
        Paket paket = findPaket(kode);
        if (paket == null) {
            System.out.println("[ERROR] Paket dengan kode '" + kode + "' tidak ditemukan.");
            return;
        }
        try {
            System.out.println("[INFO] Kosongkan input untuk tidak mengubah nilai.");
            String namaBaru  = readString("Nama baru  [" + paket.getNamaPaket() + "]: ");
            String hargaStr  = readString("Harga baru [" + (int) paket.getHarga() + "]: ");
            String deskBaru  = readString("Deskripsi  [" + paket.getDeskripsi() + "]: ");

            if (!namaBaru.isEmpty()) paket.setNamaPaket(namaBaru);
            if (!hargaStr.isEmpty()) {
                try {
                    paket.setHarga(Double.parseDouble(hargaStr));
                } catch (NumberFormatException e) {
                    System.out.println("[WARN] Input harga tidak valid, harga tidak diubah.");
                }
            }
            if (!deskBaru.isEmpty()) paket.setDeskripsi(deskBaru);

            System.out.println("[SUKSES] Paket berhasil diupdate.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static void hapusPaket() {
        System.out.println("\n--- Hapus Paket ---");
        lihatSemuaPaket();
        String kode = readString("\nMasukkan kode paket yang ingin dihapus: ");
        Paket paket = findPaket(kode);
        if (paket == null) {
            System.out.println("[ERROR] Paket tidak ditemukan.");
            return;
        }
        daftarPaket.remove(paket);
        Paket.totalPaket--;
        System.out.println("[SUKSES] Paket '" + paket.getNamaPaket() + "' berhasil dihapus.");
    }

    // ============================================================
    // MENU TRANSAKSI
    // ============================================================
    static void menuTransaksi() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- TRANSAKSI ---");
            System.out.println("1. Beli Paket");
            System.out.println("2. Riwayat Transaksi");
            System.out.println("0. Kembali");
            int pilihan = readInt("Pilih: ");
            switch (pilihan) {
                case 1 -> beliPaket();
                case 2 -> riwayatTransaksi();
                case 0 -> back = true;
                default -> System.out.println("[WARN] Pilihan tidak valid.");
            }
        }
    }

    static void beliPaket() {
        System.out.println("\n--- Beli Paket ---");

        if (daftarUser.isEmpty()) {
            System.out.println("[ERROR] Belum ada user terdaftar.");
            return;
        }
        if (daftarPaket.isEmpty()) {
            System.out.println("[ERROR] Belum ada paket tersedia.");
            return;
        }

        lihatSemuaUser();
        String idUser = readString("\nMasukkan ID user: ");
        User user = findUser(idUser);
        if (user == null) {
            System.out.println("[ERROR] User tidak ditemukan.");
            return;
        }

        lihatSemuaPaket();
        String kodePaket = readString("\nMasukkan kode paket: ");
        Paket paket = findPaket(kodePaket);
        if (paket == null) {
            System.out.println("[ERROR] Paket tidak ditemukan.");
            return;
        }

        System.out.printf("%n[INFO] Konfirmasi pembelian paket '%s' seharga Rp %.0f%n",
                paket.getNamaPaket(), paket.getHarga());
        System.out.printf("[INFO] Saldo Anda: Rp %.0f%n", user.getSaldo());
        String konfirmasi = readString("Lanjutkan? (y/n): ");

        if (!konfirmasi.equalsIgnoreCase("y")) {
            System.out.println("[INFO] Transaksi dibatalkan.");
            return;
        }

        String idTrx = String.format("TRX%03d", counterTransaksi++);
        Transaksi trx = new Transaksi(idTrx, user.getId(), paket.getKodePaket(), paket.getHarga());

        try {
            user.kurangiSaldo(paket.getHarga());
            daftarTransaksi.add(trx);
            System.out.println("\n[SUKSES] Transaksi berhasil!");
            trx.tampilInfo();
        } catch (IllegalStateException e) {
            trx.status = Transaksi.STATUS_GAGAL;
            daftarTransaksi.add(trx);
            System.out.println(e.getMessage());
            System.out.println("[INFO] Transaksi dicatat sebagai GAGAL.");
        }
    }

    static void riwayatTransaksi() {
        if (daftarTransaksi.isEmpty()) {
            System.out.println("[INFO] Belum ada riwayat transaksi.");
            return;
        }
        System.out.println("\n===== RIWAYAT TRANSAKSI =====");
        System.out.printf("%-12s %-6s %-6s %-12s %-8s %s%n",
                "ID Transaksi", "User", "Paket", "Total", "Status", "Waktu");
        System.out.println("-".repeat(80));
        for (Transaksi t : daftarTransaksi) {
            System.out.println(t);
        }
        System.out.printf("[INFO] Total transaksi: %d%n", Transaksi.totalTransaksi);
    }

    // ============================================================
    // HELPER: FIND
    // ============================================================
    static User findUser(String id) {
        for (User u : daftarUser) {
            if (u.getId().equalsIgnoreCase(id)) return u;
        }
        return null;
    }

    static Paket findPaket(String kode) {
        for (Paket p : daftarPaket) {
            if (p.getKodePaket().equalsIgnoreCase(kode)) return p;
        }
        return null;
    }

    // ============================================================
    // HELPER: INPUT dengan error handling
    // ============================================================
    static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                // Gunakan Apache Commons Lang untuk cek blank
                if (StringUtils.isBlank(input)) {
                    System.out.println("[WARN] Input tidak boleh kosong.");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[WARN] Masukkan angka yang valid.");
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                if (StringUtils.isBlank(input)) {
                    System.out.println("[WARN] Input tidak boleh kosong.");
                    continue;
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("[WARN] Masukkan angka desimal yang valid.");
            }
        }
    }
}
