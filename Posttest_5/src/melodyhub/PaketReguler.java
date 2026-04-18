package melodyhub;

public class PaketReguler extends Paket implements IPembayaran {
    private String deskripsi;

    public PaketReguler(String namaPaket, double harga, int durasiBulan, String deskripsi) {
        super(namaPaket, harga, durasiBulan);
        this.deskripsi = deskripsi;
    }

    @Override
    public String getKategori() {
        return "Reguler";
    }

    @Override
    public String getInfoTambahan() {
        return deskripsi;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
    }

    // Overriding hitungTotal - diskon 5% jika >= 6 bulan
    @Override
    public double hitungTotal(int bulan) {
        double total = harga * bulan;
        if (bulan >= 6) {
            total = total * 0.95;
        }
        return total;
    }

    // Implementasi interface IPembayaran
    @Override
    public boolean validasiPembayaran(int durasiDibeli) {
        if (durasiDibeli < 1) {
            System.out.println("[GAGAL] Durasi minimal berlangganan adalah 1 bulan.");
            return false;
        }
        System.out.println("[OK] Pembayaran Paket Reguler valid.");
        return true;
    }

    @Override
    public void cetakStruk(String namaUser, int durasiDibeli, double totalBayar) {
        System.out.println("========================================");
        System.out.println("           STRUK PEMBELIAN              ");
        System.out.println("========================================");
        System.out.println("Tipe Paket : Reguler");
        System.out.println("Paket      : " + namaPaket);
        System.out.println("Pelanggan  : " + namaUser);
        System.out.println("Durasi     : " + durasiDibeli + " Bulan");
        if (durasiDibeli >= 6) {
            System.out.println("Diskon     : 5% (langganan panjang)");
        }
        System.out.printf("Total Bayar: Rp %.0f%n", totalBayar);
        System.out.println("========================================");
    }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
}