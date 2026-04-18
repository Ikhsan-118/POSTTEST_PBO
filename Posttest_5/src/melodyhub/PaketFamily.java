package melodyhub;

public class PaketFamily extends Paket implements IPembayaran {
    private int jumlahAnggota;

    public PaketFamily(String namaPaket, double harga, int durasiBulan, int jumlahAnggota) {
        super(namaPaket, harga, durasiBulan);
        this.jumlahAnggota = jumlahAnggota;
    }

    @Override
    public String getKategori() {
        return "Family";
    }

    @Override
    public String getInfoTambahan() {
        return "Maks. " + jumlahAnggota + " akun, hemat s/d 40%";
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
    }

    // Overriding hitungTotal - diskon 10% per anggota di atas 2, maks 40%
    @Override
    public double hitungTotal(int bulan) {
        double total = harga * bulan;
        if (jumlahAnggota > 2) {
            double diskon = 0.10 * (jumlahAnggota - 2);
            if (diskon > 0.40) diskon = 0.40;
            total = total * (1 - diskon);
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
        if (jumlahAnggota < 2) {
            System.out.println("[GAGAL] Paket Family membutuhkan minimal 2 anggota.");
            return false;
        }
        int diskonPersen = Math.min((jumlahAnggota - 2) * 10, 40);
        if (diskonPersen > 0) {
            System.out.println("[DISKON] Kamu mendapat diskon " + diskonPersen + "% untuk " + jumlahAnggota + " anggota!");
        }
        System.out.println("[OK] Pembayaran Paket Family valid.");
        return true;
    }

    @Override
    public void cetakStruk(String namaUser, int durasiDibeli, double totalBayar) {
        int diskonPersen = Math.min((jumlahAnggota - 2) * 10, 40);
        System.out.println("========================================");
        System.out.println("           STRUK PEMBELIAN              ");
        System.out.println("========================================");
        System.out.println("Tipe Paket : Family");
        System.out.println("Paket      : " + namaPaket);
        System.out.println("Pelanggan  : " + namaUser);
        System.out.println("Jml Akun   : " + jumlahAnggota + " Akun");
        System.out.println("Durasi     : " + durasiDibeli + " Bulan");
        if (diskonPersen > 0) {
            System.out.println("Diskon     : " + diskonPersen + "% (multi-akun)");
        }
        System.out.printf("Total Bayar: Rp %.0f%n", totalBayar);
        System.out.println("========================================");
    }

    public int getJumlahAnggota() { return jumlahAnggota; }
    public void setJumlahAnggota(int jumlahAnggota) { this.jumlahAnggota = jumlahAnggota; }
}