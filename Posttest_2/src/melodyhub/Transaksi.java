package melodyhub;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaksi {

    // === PRIVATE: data inti tidak boleh diubah setelah transaksi ===
    private final String idTransaksi;
    private final String idUser;
    private final String kodePaket;
    private final double totalBayar;
    private final String waktuTransaksi;

    protected String status; // SUKSES / GAGAL

    // === DEFAULT ===
    static int totalTransaksi = 0;

    // === PUBLIC ===
    public static final String STATUS_SUKSES = "SUKSES";
    public static final String STATUS_GAGAL  = "GAGAL";

    public Transaksi(String idTransaksi, String idUser, String kodePaket, double totalBayar) {
        if (idTransaksi == null || idTransaksi.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] ID transaksi tidak boleh kosong.");
        }
        if (idUser == null || idUser.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] ID user tidak boleh kosong.");
        }
        if (kodePaket == null || kodePaket.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] Kode paket tidak boleh kosong.");
        }
        if (totalBayar < 0) {
            throw new IllegalArgumentException("[ERROR] Total bayar tidak boleh negatif.");
        }

        this.idTransaksi    = idTransaksi.trim();
        this.idUser         = idUser.trim();
        this.kodePaket      = kodePaket.trim().toUpperCase();
        this.totalBayar     = totalBayar;
        this.status         = STATUS_SUKSES;
        this.waktuTransaksi = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        totalTransaksi++;
    }


    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getKodePaket() {
        return kodePaket;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public String getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public String getStatus() {
        return status;
    }

    public void tampilInfo() {
        System.out.println("------------------------------------");
        System.out.println("  ID Transaksi : " + idTransaksi);
        System.out.println("  ID User      : " + idUser);
        System.out.println("  Kode Paket   : " + kodePaket);
        System.out.printf("  Total Bayar  : Rp %.0f%n", totalBayar);
        System.out.println("  Waktu        : " + waktuTransaksi);
        System.out.println("  Status       : " + status);
        System.out.println("------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-6s | %-6s | Rp %-8.0f | %-8s | %s",
                idTransaksi, idUser, kodePaket, totalBayar, status, waktuTransaksi);
    }
}
