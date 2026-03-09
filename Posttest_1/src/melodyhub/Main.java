package melodyhub;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<User> dataUser = new ArrayList<>();
    static ArrayList<Paket> dataPaket = new ArrayList<>();
    static ArrayList<Transaksi> dataTransaksi = new ArrayList<>();

    static Scanner input = new Scanner(System.in);

    static void header() {
        System.out.println("====================================");
        System.out.println("        MELODY HUB PREMIUM          ");
        System.out.println("====================================");
    }

    static void menu() {
        System.out.println("\nMENU");
        System.out.println("1. Tambah User");
        System.out.println("2. Lihat User");
        System.out.println("3. Update User");
        System.out.println("4. Hapus User");
        System.out.println("5. Tambah Paket");
        System.out.println("6. Beli Paket");
        System.out.println("7. Lihat Transaksi");
        System.out.println("0. Exit");
        System.out.print("Pilih : ");
    }

    static void tambahUser() {
        System.out.print("Nama : ");
        String nama = input.nextLine();

        System.out.print("Email : ");
        String email = input.nextLine();

        dataUser.add(new User(nama,email));
        System.out.println("User berhasil ditambah");
    }

    static void lihatUser() {
        if(dataUser.isEmpty()){
            System.out.println("Data kosong");
        } else {
            for(int i=0;i<dataUser.size();i++){
                System.out.println("\nData ke-"+i);
                dataUser.get(i).tampil();
            }
        }
    }

    static void updateUser() {
        lihatUser();
        System.out.print("Index yang diubah : ");
        int i = input.nextInt();
        input.nextLine();

        System.out.print("Nama baru : ");
        String nama = input.nextLine();

        System.out.print("Email baru : ");
        String email = input.nextLine();

        dataUser.set(i,new User(nama,email));
        System.out.println("Data diupdate");
    }

    static void hapusUser() {
        lihatUser();
        System.out.print("Index yang dihapus : ");
        int i = input.nextInt();
        input.nextLine();

        dataUser.remove(i);
        System.out.println("Data dihapus");
    }

    static void tambahPaket() {
        System.out.print("Nama paket : ");
        String nama = input.nextLine();

        System.out.print("Harga : ");
        int harga = input.nextInt();
        input.nextLine();

        dataPaket.add(new Paket(nama,harga));
        System.out.println("Paket ditambahkan");
    }

    static void beliPaket() {
        lihatUser();

        System.out.print("Nama user : ");
        String user = input.nextLine();

        System.out.print("Nama paket : ");
        String paket = input.nextLine();

        dataTransaksi.add(new Transaksi(user,paket));

        System.out.println("Pembelian berhasil");
    }

    static void lihatTransaksi() {
        for(Transaksi t : dataTransaksi){
            t.tampil();
            System.out.println("----------------");
        }
    }

    static void main() {

        int pilih;

        do{
            header();
            menu();

            pilih = input.nextInt();
            input.nextLine();

            switch(pilih){

                case 1:
                    tambahUser();
                    break;

                case 2:
                    lihatUser();
                    break;

                case 3:
                    updateUser();
                    break;

                case 4:
                    hapusUser();
                    break;

                case 5:
                    tambahPaket();
                    break;

                case 6:
                    beliPaket();
                    break;

                case 7:
                    lihatTransaksi();
                    break;

                case 0:
                    System.out.println("Program selesai");
                    break;

                default:
                    System.out.println("Menu tidak ada");
            }

        }while(pilih != 0);

    }
}