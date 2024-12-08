package saribundo;

import java.io.*;
import java.util.*;

public class Menu {
    private ArrayList<MenuItem> daftarMenu;

    public Menu() {
        daftarMenu = new ArrayList<>();
    }

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
    }

    public void tampilkanMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu kosong.");
            return;
        }
        for (MenuItem item : daftarMenu) {
            item.tampilMenu();
        }
    }

    public MenuItem cariItem(String nama) throws ItemTidakDitemukanException {
        for (MenuItem item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        throw new ItemTidakDitemukanException("Item menu tidak ditemukan: " + nama);
    }

    public void simpanKeFile(String namaFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(namaFile))) {
            for (MenuItem item : daftarMenu) {
                if (item instanceof Makanan) {
                    Makanan makanan = (Makanan) item;
                    writer.printf("Makanan,%s,%.2f,%s,%s%n",
                            makanan.getNama(), makanan.getHarga(),
                            makanan.getKategori(), makanan.getJenisMakanan());
                } else if (item instanceof Minuman) {
                    Minuman minuman = (Minuman) item;
                    writer.printf("Minuman,%s,%.2f,%s,%s%n",
                            minuman.getNama(), minuman.getHarga(),
                            minuman.getKategori(), minuman.getJenisMinuman());
                } else if (item instanceof Diskon) {
                    Diskon diskon = (Diskon) item;
                    writer.printf("Diskon,%s,%.2f,%s,%.2f%n",
                            diskon.getNama(), diskon.getHarga(),
                            diskon.getKategori(), diskon.getPersentaseDiskon());
                }
            }
            System.out.println("Menu berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan menu: " + e.getMessage());
        }
    }

    public void muatDariFile(String namaFile) {
        daftarMenu.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                String[] bagian = baris.split(",");
                switch (bagian[0]) {
                    case "Makanan":
                        tambahItem(new Makanan(bagian[1], Double.parseDouble(bagian[2]),
                                bagian[3], bagian[4]));
                        break;
                    case "Minuman":
                        tambahItem(new Minuman(bagian[1], Double.parseDouble(bagian[2]),
                                bagian[3], bagian[4]));
                        break;
                    case "Diskon":
                        tambahItem(new Diskon(bagian[1], Double.parseDouble(bagian[2]),
                                bagian[3], Double.parseDouble(bagian[4])));
                        break;
                }
            }
            System.out.println("Menu berhasil dimuat dari file.");
        } catch (IOException e) {
            System.out.println("Gagal memuat menu: " + e.getMessage());
        }
    }
}