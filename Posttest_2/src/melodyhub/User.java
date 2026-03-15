package melodyhub;

public class User {

    // === PRIVATE: hanya bisa diakses dalam kelas ini ===
    private String id;
    private String password;
    private double saldo;

    // === PROTECTED: bisa diakses subclass & package ===
    protected String nama;
    protected String email;

    // === DEFAULT (package-private): bisa diakses sesama package ===
    static final String ROLE_DEFAULT = "USER";

    // === PUBLIC: bisa diakses dari mana saja ===
    public static int totalUser = 0;

    public User(String id, String nama, String email, String password) {
        setId(id);
        setNama(nama);
        setEmail(email);
        setPassword(password);
        this.saldo = 0.0;
        totalUser++;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] ID user tidak boleh kosong.");
        }
        this.id = id.trim();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] Nama user tidak boleh kosong.");
        }
        this.nama = nama.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("[ERROR] Format email tidak valid.");
        }
        this.email = email.trim();
    }

    public boolean verifikasiPassword(String inputPassword) {
        if (inputPassword == null) return false;
        return this.password.equals(inputPassword);
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("[ERROR] Password minimal 4 karakter.");
        }
        this.password = password;
    }

    public double getSaldo() {
        return saldo;
    }

    public void topUpSaldo(double jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("[ERROR] Jumlah top-up harus lebih dari 0.");
        }
        this.saldo += jumlah;
        System.out.printf("[INFO] Top-up berhasil. Saldo %s sekarang: Rp %.0f%n", nama, saldo);
    }

    /**
     * Mengurangi saldo (dipakai secara internal / package)
     * @throws IllegalStateException jika saldo tidak cukup
     */
    void kurangiSaldo(double jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("[ERROR] Jumlah pembayaran tidak valid.");
        }
        if (this.saldo < jumlah) {
            throw new IllegalStateException("[ERROR] Saldo tidak cukup. Saldo Anda: Rp " + (int) saldo);
        }
        this.saldo -= jumlah;
    }

    public void tampilInfo() {
        System.out.println("------------------------------------");
        System.out.println("  ID    : " + id);
        System.out.println("  Nama  : " + nama);
        System.out.println("  Email : " + email);
        System.out.printf("  Saldo : Rp %.0f%n", saldo);
        System.out.println("------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, nama=%s, email=%s, saldo=%.0f]", id, nama, email, saldo);
    }
}
