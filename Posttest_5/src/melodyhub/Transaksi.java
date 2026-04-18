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

    void tampil() {
        System.out.println("User    : " + namaUser);
        System.out.println("Paket   : " + paket);
        System.out.printf("Total   : Rp %.0f%n", totalBayar);
    }

    void tampil(boolean tampilTanggal) {
        tampil();
        if (tampilTanggal) {
            System.out.println("Tanggal : " + tanggal);
        }
    }

    void tampil(String catatan) {
        tampil();
        System.out.println("Tanggal : " + tanggal);
        System.out.println("Catatan : " + catatan);
    }
}