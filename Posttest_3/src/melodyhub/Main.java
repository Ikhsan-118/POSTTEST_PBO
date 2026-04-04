package melodyhub;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main(String[] ignoredArgs) {
        ArrayList<Paket> daftarPaket = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== MENU MELODYHUB MANAGEMENT ===");
            System.out.println("1. Tambah Paket");
            System.out.println("2. Lihat Semua Paket");
            System.out.println("3. Update Paket");
            System.out.println("4. Hapus Paket");
            System.out.println("5. Keluar");
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
                        daftarPaket.get(i).tampilkanInfo();
                    }
                    break;

                case 3:
                    System.out.print("Masukkan ID Paket yang akan diupdate: ");
                    int idUp = scanner.nextInt() - 1;
                    if (idUp >= 0 && idUp < daftarPaket.size()) {
                        scanner.nextLine();
                        System.out.print("Nama Baru: "); String nBaru = scanner.nextLine();
                        System.out.print("Harga Baru: "); double hBaru = scanner.nextDouble();
                        // Untuk mempermudah update di level inheritance
                        daftarPaket.set(idUp, new Paket(nBaru, hBaru, daftarPaket.get(idUp).durasiBulan));
                        System.out.println("Data berhasil diupdate!");
                    }
                    break;

                case 4:
                    System.out.print("Masukkan ID Paket yang akan dihapus: ");
                    int idHapus = scanner.nextInt() - 1;
                    if (idHapus >= 0 && idHapus < daftarPaket.size()) {
                        daftarPaket.remove(idHapus);
                        System.out.println("Paket berhasil dihapus!");
                    }
                    break;

                case 5:
                    System.out.println("Terima kasih!");
                    break;
            }
        } while (pilihan != 5);
    }
}