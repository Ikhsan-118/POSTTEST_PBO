package melodyhub;

public class Transaksi {
    String namaUser;
    String paket;

    Transaksi(String namaUser, String paket) {
        this.namaUser = namaUser;
        this.paket = paket;
    }

    void tampil() {
        System.out.println("User  : " + namaUser);
        System.out.println("Paket : " + paket);
    }
}