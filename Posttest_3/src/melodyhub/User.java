package melodyhub;

public class User {
    String nama;
    String email;

    User(String nama, String email) {
        this.nama = nama;
        this.email = email;
    }

    void tampil() {
        System.out.println("Nama  : " + nama);
        System.out.println("Email : " + email);
    }
}