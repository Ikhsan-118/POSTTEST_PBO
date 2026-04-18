package melodyhub;

public abstract class Paket {
    protected String namaPaket;
    protected double harga;
    protected int durasiBulan;

    public Paket(String namaPaket, double harga, int durasiBulan) {
        this.namaPaket = namaPaket;
        this.harga = harga;
        this.durasiBulan = durasiBulan;
    }

    // Concrete method - diwariskan ke semua subclass
    public void tampilkanInfo() {
        System.out.println("Nama Paket : " + namaPaket);
        System.out.println("Harga      : Rp " + (int) harga);
        System.out.println("Durasi     : " + durasiBulan + " Bulan");
        System.out.println("Kategori   : " + getKategori());
        System.out.println("Info       : " + getInfoTambahan());
    }

    // Abstract method #1 - wajib diimplementasi tiap subclass
    public abstract String getKategori();

    // Abstract method #2 - wajib diimplementasi tiap subclass
    public abstract String getInfoTambahan();

    // Abstract method #3 - kalkulasi tiap paket berbeda
    public abstract double hitungTotal(int bulan);

    // Overloading hitungTotal (2 param) - concrete
    public double hitungTotal(int bulan, double diskonPersen) {
        double total = hitungTotal(bulan);
        return total - (total * diskonPersen / 100);
    }

    // Overloading hitungTotal (0 param) - concrete
    public double hitungTotal() {
        return hitungTotal(durasiBulan);
    }
}