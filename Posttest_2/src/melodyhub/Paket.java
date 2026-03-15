package melodyhub;

public class Paket {

    // === PRIVATE ===
    private String kodePaket;
    private String namaPaket;
    private double harga;
    private int durasiHari;
    private String deskripsi;

    // === DEFAULT (package-private) ===
    static int totalPaket = 0;

    public Paket(String kodePaket, String namaPaket, double harga, int durasiHari, String deskripsi) {
        setKodePaket(kodePaket);
        setNamaPaket(namaPaket);
        setHarga(harga);
        setDurasiHari(durasiHari);
        setDeskripsi(deskripsi);
        totalPaket++;
    }

    public String getKodePaket() {
        return kodePaket;
    }

    public void setKodePaket(String kodePaket) {
        if (kodePaket == null || kodePaket.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] Kode paket tidak boleh kosong.");
        }
        this.kodePaket = kodePaket.trim().toUpperCase();
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        if (namaPaket == null || namaPaket.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] Nama paket tidak boleh kosong.");
        }
        this.namaPaket = namaPaket.trim();
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (harga < 0) {
            throw new IllegalArgumentException("[ERROR] Harga paket tidak boleh negatif.");
        }
        this.harga = harga;
    }

    public int getDurasiHari() {
        return durasiHari;
    }

    public void setDurasiHari(int durasiHari) {
        if (durasiHari <= 0) {
            throw new IllegalArgumentException("[ERROR] Durasi paket harus lebih dari 0 hari.");
        }
        this.durasiHari = durasiHari;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = (deskripsi == null) ? "-" : deskripsi.trim();
    }

    public void tampilInfo() {
        System.out.println("------------------------------------");
        System.out.println("  Kode     : " + kodePaket);
        System.out.println("  Nama     : " + namaPaket);
        System.out.printf("  Harga    : Rp %.0f%n", harga);
        System.out.println("  Durasi   : " + durasiHari + " hari");
        System.out.println("  Deskripsi: " + deskripsi);
        System.out.println("------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-20s | Rp %-8.0f | %d hari", kodePaket, namaPaket, harga, durasiHari);
    }
}
