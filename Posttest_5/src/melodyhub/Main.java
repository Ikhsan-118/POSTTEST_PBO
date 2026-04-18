package melodyhub;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main(String[] ignoredArgs) {
        ArrayList<Paket> daftarPaket = new ArrayList<>();
        ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== MENU MELODYHUB MANAGEMENT ===");
            System.out.println("1. Tambah Paket");
            System.out.println("2. Lihat Semua Paket");
            System.out.println("3. Update Paket");
            System.out.println("4. Hapus Paket");
            System.out.println("5. Beli Paket (Simulasi Transaksi)");
            System.out.println("6. Lihat Riwayat Transaksi");
            System.out.println("7. Hitung Estimasi Biaya");
            System.out.println("8. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- Pilih Tipe Paket ---");
                    System.out.println("1. Reguler\n2. Premium\n3. Family");
                    System.out.print("Tipe: ");
                    int tipe = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nama Paket  : "); String nama = scanner.nextLine();
                    System.out.print("Harga       : "); double harga = scanner.nextDouble();
                    System.out.print("Durasi (bln): "); int durasi = scanner.nextInt();
                    scanner.nextLine();

                    if (tipe == 1) {
                        System.out.print("Deskripsi   : "); String desc = scanner.nextLine();
                        daftarPaket.add(new PaketReguler(nama, harga, durasi, desc));
                    } else if (tipe == 2) {
                        System.out.print("Fitur Utama : "); String fitur = scanner.nextLine();
                        daftarPaket.add(new PaketPremium(nama, harga, durasi, fitur));
                    } else if (tipe == 3) {
                        System.out.print("Jumlah Akun : "); int jml = scanner.nextInt();
                        daftarPaket.add(new PaketFamily(nama, harga, durasi, jml));
                    }
                    System.out.println("Paket berhasil ditambahkan!");
                    break;

                case 2:
                    System.out.println("\n--- Daftar Paket MelodyHub ---");
                    if (daftarPaket.isEmpty()) System.out.println("Belum ada data.");
                    for (int i = 0; i < daftarPaket.size(); i++) {
                        System.out.println("\nID: " + (i + 1));
                        // OVERRIDING tampilkanInfo() - setiap class memanggil versi miliknya
                        daftarPaket.get(i).tampilkanInfo();

                        // OVERLOADING hitungTotal() - tanpa parameter (pakai durasi default)
                        System.out.printf("Total Default  : Rp %.0f%n", daftarPaket.get(i).hitungTotal());
                    }
                    break;

                case 3:
                    System.out.print("Masukkan ID Paket yang akan diupdate: ");
                    int idUp = scanner.nextInt() - 1;
                    if (idUp >= 0 && idUp < daftarPaket.size()) {
                        scanner.nextLine();
                        Paket p = daftarPaket.get(idUp);

                        System.out.print("Nama Baru   : "); String nBaru = scanner.nextLine();
                        System.out.print("Harga Baru  : "); double hBaru = scanner.nextDouble();
                        System.out.print("Durasi Baru : "); int dBaru = scanner.nextInt();
                        scanner.nextLine();

                        // Pertahankan tipe subclass saat update
                        if (!(p instanceof PaketReguler)) {
                            if (p instanceof PaketPremium) {
                                System.out.print("Fitur Baru: "); String fitur = scanner.nextLine();
                                daftarPaket.set(idUp, new PaketPremium(nBaru, hBaru, dBaru, fitur));
                            } else if (p instanceof PaketFamily) {
                                System.out.print("Jumlah Akun: "); int jml = scanner.nextInt();
                                daftarPaket.set(idUp, new PaketFamily(nBaru, hBaru, dBaru, jml));
                            } else {
                                daftarPaket.set(idUp, new Paket(nBaru, hBaru, dBaru) {
                                    @Override
                                    public String getKategori() {
                                        return "";
                                    }

                                    @Override
                                    public String getInfoTambahan() {
                                        return "";
                                    }

                                    @Override
                                    public double hitungTotal(int bulan) {
                                        return 0;
                                    }
                                });
                            }
                        } else {
                            System.out.print("Deskripsi Baru: "); String desc = scanner.nextLine();
                            daftarPaket.set(idUp, new PaketReguler(nBaru, hBaru, dBaru, desc));
                        }
                        System.out.println("Data berhasil diupdate!");
                    } else {
                        System.out.println("ID tidak ditemukan.");
                    }
                    break;

                case 4:
                    System.out.print("Masukkan ID Paket yang akan dihapus: ");
                    int idHapus = scanner.nextInt() - 1;
                    if (idHapus >= 0 && idHapus < daftarPaket.size()) {
                        daftarPaket.remove(idHapus);
                        System.out.println("Paket berhasil dihapus!");
                    } else {
                        System.out.println("ID tidak ditemukan.");
                    }
                    break;

                case 5:
                    // Beli Paket - hitung total sesuai subclass (OVERRIDING hitungTotal)
                    if (daftarPaket.isEmpty()) {
                        System.out.println("Belum ada paket tersedia.");
                        break;
                    }
                    System.out.print("Nama User   : "); String namaUser = scanner.nextLine();
                    System.out.print("Email User  : "); String emailUser = scanner.nextLine();
                    User user = new User(namaUser, emailUser);

                    System.out.println("\n--- Pilih Paket ---");
                    for (int i = 0; i < daftarPaket.size(); i++) {
                        System.out.printf("[%d] %s - Rp %.0f/%d bln%n",
                                i + 1, daftarPaket.get(i).namaPaket,
                                daftarPaket.get(i).harga, daftarPaket.get(i).durasiBulan);
                    }
                    System.out.print("Pilih ID Paket: ");
                    int idPaket = scanner.nextInt() - 1;
                    System.out.print("Durasi berlangganan (bulan): ");
                    int durasiLangganan = scanner.nextInt();
                    scanner.nextLine();

                    if (idPaket >= 0 && idPaket < daftarPaket.size()) {
                        Paket paketDipilih = daftarPaket.get(idPaket);

                        // OVERRIDING hitungTotal(int) - tiap subclass punya kalkulasi berbeda
                        double totalBayar = paketDipilih.hitungTotal(durasiLangganan);

                        String tgl = LocalDate.now().toString();
                        Transaksi trx = new Transaksi(namaUser, paketDipilih.namaPaket, totalBayar, tgl);
                        daftarTransaksi.add(trx);

                        System.out.println("\n--- Bukti Transaksi ---");
                        user.tampil();
                        // OVERLOADING tampil(boolean) - tampil dengan tanggal
                        trx.tampil(true);
                        System.out.println("Transaksi berhasil!");
                    } else {
                        System.out.println("ID paket tidak ditemukan.");
                    }
                    break;

                case 6:
                    // Lihat Riwayat Transaksi
                    System.out.println("\n--- Riwayat Transaksi ---");
                    if (daftarTransaksi.isEmpty()) {
                        System.out.println("Belum ada transaksi.");
                        break;
                    }
                    System.out.println("Tampilkan catatan? (1=Ya, 0=Tidak)");
                    int pilihanCatatan = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < daftarTransaksi.size(); i++) {
                        System.out.println("\nTransaksi #" + (i + 1));
                        if (pilihanCatatan == 1) {
                            // OVERLOADING tampil(String) - tampil dengan catatan
                            daftarTransaksi.get(i).tampil("Pembayaran berhasil diverifikasi");
                        } else {
                            // OVERLOADING tampil(boolean) - tampil dengan tanggal
                            daftarTransaksi.get(i).tampil(true);
                        }
                        System.out.println("----------------------");
                    }
                    break;

                case 7:
                    // Estimasi biaya dengan diskon - OVERLOADING hitungTotal(int, double)
                    if (daftarPaket.isEmpty()) {
                        System.out.println("Belum ada paket tersedia.");
                        break;
                    }
                    System.out.println("\n--- Estimasi Biaya ---");
                    for (int i = 0; i < daftarPaket.size(); i++) {
                        System.out.printf("[%d] %s%n", i + 1, daftarPaket.get(i).namaPaket);
                    }
                    System.out.print("Pilih ID Paket: ");
                    int idEst = scanner.nextInt() - 1;
                    System.out.print("Durasi (bulan): ");
                    int bulanEst = scanner.nextInt();
                    System.out.print("Diskon (%) [0 jika tidak ada]: ");
                    double diskonEst = scanner.nextDouble();
                    scanner.nextLine();

                    if (idEst >= 0 && idEst < daftarPaket.size()) {
                        Paket paketEst = daftarPaket.get(idEst);
                        // OVERLOADING hitungTotal(int) - hitung tanpa diskon eksternal
                        double totalNormal = paketEst.hitungTotal(bulanEst);
                        // OVERLOADING hitungTotal(int, double) - hitung dengan diskon eksternal
                        double totalDiskon = paketEst.hitungTotal(bulanEst, diskonEst);

                        System.out.println("\n=== Estimasi Biaya ===");
                        System.out.println("Paket    : " + paketEst.namaPaket);
                        System.out.printf("Normal   : Rp %.0f%n", totalNormal);
                        if (diskonEst > 0) {
                            System.out.printf("Diskon   : %.0f%%%n", diskonEst);
                            System.out.printf("Hemat    : Rp %.0f%n", totalNormal - totalDiskon);
                            System.out.printf("Bayar    : Rp %.0f%n", totalDiskon);
                        }
                    } else {
                        System.out.println("ID tidak ditemukan.");
                    }
                    break;

                case 8:
                    System.out.println("Terima kasih telah menggunakan MelodyHub!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 8);
    }
}