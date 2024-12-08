package saribundo;

public class Diskon extends MenuItem {
    private double persentaseDiskon;

    public Diskon(String nama, double harga, String kategori, double persentaseDiskon) {
        super(nama, harga, kategori);
        this.persentaseDiskon = persentaseDiskon;
    }

    public double getPersentaseDiskon() {
        return persentaseDiskon;
    }

    public void setPersentaseDiskon(double persentaseDiskon) {
        this.persentaseDiskon = persentaseDiskon;
    }

    @Override
    public void tampilMenu() {
        System.out.printf("Diskon: %s - Harga Asli: Rp %.2f - Diskon: %.2f%% - Kategori: %s%n",
                getNama(), getHarga(), persentaseDiskon, getKategori());
    }

    public double hitungHargaSetelahDiskon() {
        return getHarga() * (1 - persentaseDiskon / 100);
    }
}