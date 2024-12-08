package saribundo;

import java.util.Scanner;

public class ManajemenRestoran {
    private static Scanner scanner = new Scanner(System.in);
    private static Menu menu = new Menu();

    public static void main(String[] args) {
        boolean keluar = false;

        while (!keluar) {
            tampilkanMenuUtama();
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline

            switch (pilihan) {
                case 1:
                    tambahItemMenu();
                    break;
                case 2:
                    menu.tampilkanMenu();
                    break;
                case 3:
                    buatPesanan();
                    break;
                case 4:
                    menu.simpanKeFile("daftar_menu.txt");
                    break;
                case 5:
                    menu.muatDariFile("daftar_menu.txt");
                    break;
                case 6:
                    keluar = true;
                    System.out.println("Terima kasih. Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n------------------------------------");
        System.out.println("---------- SELAMAT DATANG ----------");
        System.out.println("--------- RESTO SARI BUNDO ---------");
        System.out.println("------------------------------------\n");
        System.out.println("1. Tambah Item Menu");
        System.out.println("2. Tampilkan Menu");
        System.out.println("3. Buat Pesanan");
        System.out.println("4. Simpan Menu ke File");
        System.out.println("5. Muat Menu dari File");
        System.out.println("6. Keluar");
        System.out.println("\n------------------------------------\n");
        System.out.print("Masukkan pilihan Anda: ");
    }

    private static void tambahItemMenu() {
        System.out.println("\n--------- MENU TAMBAH ITEM ---------");
        System.out.println("1. Tambah Makanan");
        System.out.println("2. Tambah Minuman");
        System.out.println("3. Tambah Diskon");
        System.out.print("Pilih jenis item: ");
        int jenis = scanner.nextInt();
        scanner.nextLine(); // Membersihkan newline

        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine(); // Membersihkan newline
        System.out.print("Masukkan kategori: ");
        String kategori = scanner.nextLine();

        switch (jenis) {
            case 1:
                System.out.print("Masukkan jenis makanan: ");
                String jenisMakanan = scanner.nextLine();
                menu.tambahItem(new Makanan(nama, harga, kategori, jenisMakanan));
                break;
            case 2:
                System.out.print("Masukkan jenis minuman: ");
                String jenisMinuman = scanner.nextLine();
                menu.tambahItem(new Minuman(nama, harga, kategori, jenisMinuman));
                break;
            case 3:
                System.out.print("Masukkan persentase diskon: ");
                double persentaseDiskon = scanner.nextDouble();
                scanner.nextLine(); // Membersihkan newline
                menu.tambahItem(new Diskon(nama, harga, kategori, persentaseDiskon));
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
        System.out.println("Item berhasil ditambahkan ke menu.");
    }

    private static void buatPesanan() {
        Pesanan pesanan = new Pesanan();
        boolean selesaiPesan = false;

        while (!selesaiPesan) {
            menu.tampilkanMenu();
            System.out.print("\nMasukkan Pesanan Anda: ");
            String nama = scanner.nextLine();

            if (nama.equalsIgnoreCase("selesai")) {
                selesaiPesan = true;
                continue;
            }

            try {
                MenuItem item = menu.cariItem(nama);
                pesanan.tambahItem(item);
                System.out.println("Item berhasil ditambahkan ke pesanan.");
            } catch (ItemTidakDitemukanException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!pesanan.getItemPesanan().isEmpty()) {
            pesanan.tampilkanStruk();

            System.out.print("Apakah Anda ingin menyimpan struk pesanan? (y/t): ");
            String simpanStruk = scanner.nextLine();

            if (simpanStruk.equalsIgnoreCase("y")) {
                System.out.print("Masukkan nama file untuk struk pesanan: ");
                String namaFile = scanner.nextLine();
                pesanan.simpanStrukKeFile(namaFile);
            }
        } else {
            System.out.println("Pesanan kosong.");
        }
    }
}