package melodyhub;

public class PaketReguler extends Paket {
    private String deskripsi;

    public PaketReguler(String namaPaket, double harga, int durasiBulan, String deskripsi) {
        super(namaPaket, harga, durasiBulan);
        this.deskripsi = deskripsi;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Keterangan : " + deskripsi);
    }
}