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

    // OVERLOADING - Static Polymorphism
    // hitungTotal dengan 1 parameter (hanya durasi custom)

    public double hitungTotal(int bulan) {
        return harga * bulan;
    }

    // hitungTotal dengan 2 parameter (durasi + diskon persen)
    public double hitungTotal(int bulan, double diskonPersen) {
        double total = harga * bulan;
        double potongan = total * (diskonPersen / 100);
        return total - potongan;
    }

    // hitungTotal tanpa parameter (pakai durasi default paket)
    public double hitungTotal() {
        return harga * durasiBulan;
    }
}