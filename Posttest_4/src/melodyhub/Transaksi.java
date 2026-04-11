package melodyhub;

public class Transaksi {
    String namaUser;
    String paket;
    double totalBayar;
    String tanggal;

    Transaksi(String namaUser, String paket, double totalBayar, String tanggal) {
        this.namaUser = namaUser;
        this.paket = paket;
        this.totalBayar = totalBayar;
        this.tanggal = tanggal;
    }

    // OVERLOADING - Static Polymorphism
    // tampil() tanpa parameter: ringkasan singkat

    void tampil() {
        System.out.println("User    : " + namaUser);
        System.out.println("Paket   : " + paket);
        System.out.printf("Total   : Rp %.0f%n", totalBayar);
    }

    // tampil(boolean) dengan tanggal transaksi
    void tampil(boolean tampilTanggal) {
        tampil(); // panggil versi dasar
        if (tampilTanggal) {
            System.out.println("Tanggal : " + tanggal);
        }
    }

    // tampil(String) dengan keterangan/catatan tambahan
    void tampil(String catatan) {
        tampil(); // panggil versi dasar
        System.out.println("Tanggal : " + tanggal);
        System.out.println("Catatan : " + catatan);
    }
}