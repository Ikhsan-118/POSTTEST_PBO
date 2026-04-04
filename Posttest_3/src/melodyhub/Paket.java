package melodyhub;

public class Paket {
    protected String namaPaket;
    protected double harga;
    protected int durasiBulan;

    public Paket(String namaPaket, double harga, int durasiBulan) {
        this.namaPaket = namaPaket;
        this.harga = harga;
        this.durasiBulan = durasiBulan;
    }

    public void tampilkanInfo() {
        System.out.println("Nama Paket : " + namaPaket);
        System.out.println("Harga      : Rp " + harga);
        System.out.println("Durasi     : " + durasiBulan + " Bulan");
    }
}