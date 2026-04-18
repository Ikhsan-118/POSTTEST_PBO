package melodyhub;

public class PaketPremium extends Paket implements IPembayaran {
    private String fiturUnggulan;

    public PaketPremium(String namaPaket, double harga, int durasiBulan, String fiturUnggulan) {
        super(namaPaket, harga, durasiBulan);
        this.fiturUnggulan = fiturUnggulan;
    }

    @Override
    public String getKategori() {
        return "Premium";
    }

    @Override
    public String getInfoTambahan() {
        return "Fitur Eksklusif: " + fiturUnggulan;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
    }

    // Overriding hitungTotal - biaya aktivasi Rp25.000, gratis 1 bln jika >= 3 bln
    @Override
    public double hitungTotal(int bulan) {
        double biayaAktivasi = 25000;
        double total = harga * bulan + biayaAktivasi;
        if (bulan >= 3) {
            total = total - harga;
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
        System.out.println("[INFO] Paket Premium dikenakan biaya aktivasi Rp 25.000.");
        if (durasiDibeli >= 3) {
            System.out.println("[BONUS] Berlangganan >= 3 bulan: gratis 1 bulan pertama!");
        }
        System.out.println("[OK] Pembayaran Paket Premium valid.");
        return true;
    }

    @Override
    public void cetakStruk(String namaUser, int durasiDibeli, double totalBayar) {
        System.out.println("========================================");
        System.out.println("           STRUK PEMBELIAN              ");
        System.out.println("========================================");
        System.out.println("Tipe Paket : Premium");
        System.out.println("Paket      : " + namaPaket);
        System.out.println("Pelanggan  : " + namaUser);
        System.out.println("Fitur      : " + fiturUnggulan);
        System.out.println("Durasi     : " + durasiDibeli + " Bulan");
        System.out.println("Biaya Akt. : Rp 25.000");
        if (durasiDibeli >= 3) {
            System.out.printf("Gratis     : 1 Bulan (- Rp %.0f)%n", harga);
        }
        System.out.printf("Total Bayar: Rp %.0f%n", totalBayar);
        System.out.println("========================================");
    }

    public String getFiturUnggulan() { return fiturUnggulan; }
    public void setFiturUnggulan(String fiturUnggulan) { this.fiturUnggulan = fiturUnggulan; }
}