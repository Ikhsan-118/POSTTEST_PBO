package melodyhub;

public class PaketPremium extends Paket {
    private String fiturUnggulan;

    public PaketPremium(String namaPaket, double harga, int durasiBulan, String fiturUnggulan) {
        super(namaPaket, harga, durasiBulan);
        this.fiturUnggulan = fiturUnggulan;
    }

    // OVERRIDE #1 - Dynamic Polymorphism
    // Menampilkan info paket + fitur eksklusif

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Fitur Eksklusif: " + fiturUnggulan);
    }

    // OVERRIDE #2 - Dynamic Polymorphism
    // Premium: ada biaya aktivasi Rp 25.000 di awal,
    // tetapi gratis bulan pertama jika berlangganan >= 3 bulan

    @Override
    public double hitungTotal(int bulan) {
        double biayaAktivasi = 25000;
        double total = harga * bulan + biayaAktivasi;
        if (bulan >= 3) {
            total = total - harga; // gratis 1 bulan pertama
        }
        return total;
    }

    public String getFiturUnggulan() { return fiturUnggulan; }
    public void setFiturUnggulan(String fiturUnggulan) { this.fiturUnggulan = fiturUnggulan; }
}