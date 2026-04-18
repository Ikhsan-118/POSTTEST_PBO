package melodyhub;

public interface IPembayaran {
    boolean validasiPembayaran(int durasiDibeli);
    void cetakStruk(String namaUser, int durasiDibeli, double totalBayar);
}