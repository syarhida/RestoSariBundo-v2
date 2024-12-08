package saribundo;

import java.io.*;
import java.util.*;

public class Pesanan {
    private ArrayList<MenuItem> itemPesanan;
    private static int nomorPesananBerikutnya = 1;
    private int nomorPesanan;
    private Date tanggalPesanan;

    public Pesanan() {
        itemPesanan = new ArrayList<>();
        nomorPesanan = nomorPesananBerikutnya++;
        tanggalPesanan = new Date();
    }

    // Metode tambahan untuk mendapatkan daftar item pesanan
    public ArrayList<MenuItem> getItemPesanan() {
        return itemPesanan;
    }

    public void tambahItem(MenuItem item) {
        itemPesanan.add(item);
    }

    public double hitungTotalBiaya() {
        double total = 0;
        for (MenuItem item : itemPesanan) {
            if (item instanceof Diskon) {
                total += ((Diskon) item).hitungHargaSetelahDiskon();
            } else {
                total += item.getHarga();
            }
        }
        return total;
    }

    public void tampilkanStruk() {
        System.out.println("------------------------------------");
        System.out.println("----------- STRUK BELANJA ----------");
        System.out.println("--------- RESTO SARI BUNDO ---------");
        System.out.println("------------------------------------");
        System.out.println();

        for (MenuItem item : itemPesanan) {
            System.out.printf("%-25s Rp%,.0f%n", item.getNama(), item.getHarga());
        }

        System.out.println();
        System.out.println("------------------------------------");
        System.out.printf("%-25s Rp%,.0f%n", "Total", hitungTotalBiaya());
        System.out.println("------------------------------------");
        System.out.println("--------- TERIMAKASIH SUDAH --------");
        System.out.println("------------ BERBELANJA ------------");
        System.out.println("------------------------------------");
    }

    public void simpanStrukKeFile(String namaFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(namaFile))) {
            writer.println("------------------------------------");
            writer.println("----------- STRUK BELANJA ----------");
            writer.println("--------- RESTO SARI BUNDO ---------");
            writer.println("------------------------------------");
            writer.println();

            for (MenuItem item : itemPesanan) {
                writer.printf("%-25s Rp%,.0f%n", item.getNama(), item.getHarga());
            }

            writer.println();
            writer.println("------------------------------------");
            writer.printf("%-25s Rp%,.0f%n", "Total", hitungTotalBiaya());
            writer.println("------------------------------------");
            writer.println("--------- TERIMAKASIH SUDAH --------");
            writer.println("------------ BERBELANJA ------------");
            writer.println("------------------------------------");

            System.out.println("Struk pesanan berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk pesanan: " + e.getMessage());
        }
    }

}