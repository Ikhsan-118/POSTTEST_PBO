package melodyhub;

public class PaketFamily extends Paket {
    private int jumlahAnggota;

    public PaketFamily(String namaPaket, double harga, int durasiBulan, int jumlahAnggota) {
        super(namaPaket, harga, durasiBulan);
        this.jumlahAnggota = jumlahAnggota;
    }

    // OVERRIDE #1 - Dynamic Polymorphism
    // Menampilkan info paket + jumlah max anggota

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Max Anggota: " + jumlahAnggota + " Akun");
    }

    // OVERRIDE #2 - Dynamic Polymorphism
    // Family: harga dibagi per anggota, semakin banyak
    // anggota semakin hemat (diskon 10% per anggota di atas 2)

    @Override
    public double hitungTotal(int bulan) {
        double total = harga * bulan;
        if (jumlahAnggota > 2) {
            double diskon = 0.10 * (jumlahAnggota - 2);
            if (diskon > 0.40) diskon = 0.40; // maks diskon 40%
            total = total * (1 - diskon);
        }
        return total;
    }

    public int getJumlahAnggota() { return jumlahAnggota; }
    public void setJumlahAnggota(int jumlahAnggota) { this.jumlahAnggota = jumlahAnggota; }
}