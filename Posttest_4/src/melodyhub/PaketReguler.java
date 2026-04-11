package melodyhub;

public class PaketReguler extends Paket {
    private String deskripsi;

    public PaketReguler(String namaPaket, double harga, int durasiBulan, String deskripsi) {
        super(namaPaket, harga, durasiBulan);
        this.deskripsi = deskripsi;
    }

    // OVERRIDE #1 - Dynamic Polymorphism
    // Menampilkan info paket + keterangan deskripsi

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Keterangan : " + deskripsi);
    }

    // OVERRIDE #2 - Dynamic Polymorphism
    // Reguler: total biasa tanpa bonus, tapi ada
    // diskon otomatis 5% jika durasi >= 6 bulan

    @Override
    public double hitungTotal(int bulan) {
        double total = harga * bulan;
        if (bulan >= 6) {
            total = total * 0.95; // diskon 5% langganan panjang
        }
        return total;
    }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
}