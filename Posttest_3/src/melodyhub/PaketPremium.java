package melodyhub;

public class PaketPremium extends Paket {
    private String fiturUnggulan;

    public PaketPremium(String namaPaket, double harga, int durasiBulan, String fiturUnggulan) {
        super(namaPaket, harga, durasiBulan);
        this.fiturUnggulan = fiturUnggulan;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Fitur Eksklusif: " + fiturUnggulan);
    }
}