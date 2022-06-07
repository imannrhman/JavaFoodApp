package javafoodapp.model;

public class BahanBaku {
    private  int id;
    private  String kodeBarang;
    private  String namaBahan;
    private  double harga;
    private  String kategoriBarang;
    private  int stok;


    public BahanBaku() {
    }

    public BahanBaku(int id, String kodeBarang, String namaBahan, double harga, String kategoriBarang, int stok) {
        this.id = id;
        this.namaBahan = namaBahan;
        this.kodeBarang = kodeBarang;
        this.harga = harga;
        this.kategoriBarang = kategoriBarang;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaBahan() {
        return namaBahan;
    }

    public void setNamaBahan(String namaBahan) {
        this.namaBahan = namaBahan;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getKategoriBarang() {
        return kategoriBarang;
    }

    public void setKategoriBarang(String kategoriBarang) {
        this.kategoriBarang = kategoriBarang;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }
}
