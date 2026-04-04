package melodyhub;

public class PaketFamily extends Paket {
    private int jumlahAnggota;

    public PaketFamily(String namaPaket, double harga, int durasiBulan, int jumlahAnggota) {
        super(namaPaket, harga, durasiBulan);
        this.jumlahAnggota = jumlahAnggota;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Max Anggota: " + jumlahAnggota + " Akun");
    }
}