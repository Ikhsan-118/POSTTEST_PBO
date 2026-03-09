package melodyhub;

public class Paket {
    String namaPaket;
    int harga;

    Paket(String namaPaket, int harga) {
        this.namaPaket = namaPaket;
        this.harga = harga;
    }

    void tampil() {
        System.out.println("Paket : " + namaPaket);
        System.out.println("Harga : " + harga);
    }
}